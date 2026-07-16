package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActivityControllerIntTest extends BaseControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturnOk() throws Exception {
        mvc.perform(get("/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }

    @Test
    void findByIdShouldReturnOkWithDetail() throws Exception {
        mvc.perform(get("/activities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void findByIdShouldReturn404NotFound() throws Exception {
        mvc.perform(get("/activities/5000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn201Created() throws Exception {
        MvcResult result = mvc.perform(post("/activities")
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "Nuova Attivita Test",
                                    "estimatedHours": 12,
                                    "projectId": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        String activityId = result.getResponse().getContentAsString();

        mvc.perform(get("/activities/" + activityId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(activityId))
                .andExpect(jsonPath("$.name").value("Nuova Attivita Test"))
                .andExpect(jsonPath("$.estimatedHours").value(12));

    }

    @Test
    void shouldUpdateActivity() throws Exception {
        String activityId = "2";

        mvc.perform(put("/activities/" + activityId)
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "Attivita Aggiornata",
                                    "estimatedHours": 40,
                                    "projectId": 1
                                }
                                """))
                .andExpect(status().isOk());

        mvc.perform(get("/activities/" + activityId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Attivita Aggiornata"))
                .andExpect(jsonPath("$.estimatedHours").value(40));
    }

    @Test
    void shouldDeleteOk() throws Exception {
        String activityId = "3";

        mvc.perform(get("/activities/" + activityId))
                .andExpect(status().isOk());

        mvc.perform(delete("/activities/" + activityId))
                .andExpect(status().isOk());

        mvc.perform(get("/activities/" + activityId))
                .andExpect(status().isNotFound());
    }
}