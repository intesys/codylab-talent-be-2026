package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TrackingControllerIntTest extends BaseControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    // ⚠️ Adatta questi id ai dati presenti nel tuo DB:
    // serve un'activity esistente il cui progetto NON sia CLOSED/COMPLETED
    private static final long EXISTING_ACTIVITY_ID = 1L;
    private static final long EXISTING_USER_ID = 1L;

    private String trackingJson(String description) {
        return """
                {
                  "description": "%s",
                  "durationMinutes": 60,
                  "activityId": %d,
                  "userId": %d
                }
                """.formatted(description, EXISTING_ACTIVITY_ID, EXISTING_USER_ID);
    }

    // Helper: crea un tracking via POST e ritorna l'id generato
    private long createTracking() throws Exception {
        String response = mvc.perform(post("/trackings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(trackingJson("Test tracking")))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void findAllShouldReturnOk() throws Exception {
        mvc.perform(get("/trackings"))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdShouldReturnExistingTracking() throws Exception {
        long id = createTracking();

        mvc.perform(get("/trackings/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.description").value("Test tracking"));

        // cleanup
        mvc.perform(delete("/trackings/" + id));
    }

    @Test
    void findByIdShouldReturnNotFound() throws Exception {
        mvc.perform(get("/trackings/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertShouldReturnCreated() throws Exception {
        long id = createTracking(); // dentro l'helper c'è già l'assert su 201

        // cleanup
        mvc.perform(delete("/trackings/" + id));
    }

    @Test
    void updateShouldReturnOkForExistingTracking() throws Exception {
        long id = createTracking();

        mvc.perform(put("/trackings/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(trackingJson("Updated description")))
                .andExpect(status().isOk());

        mvc.perform(get("/trackings/" + id))
                .andExpect(jsonPath("$.description").value("Updated description"));

        // cleanup
        mvc.perform(delete("/trackings/" + id));
    }

    @Test
    void deleteShouldRemoveExistingTracking() throws Exception {
        long id = createTracking();

        mvc.perform(delete("/trackings/" + id))
                .andExpect(status().isNoContent());

        mvc.perform(get("/trackings/" + id))
                .andExpect(status().isNotFound());
    }
}