package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerIntTest extends BaseControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturnOk() throws Exception {
        mvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdShouldReturnNotFound() throws Exception {
        mvc.perform(get("/customers/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn201Created() throws Exception {
        mvc.perform(post("/customers")
                        .contentType("application/json")
                        .content("{\"name\": \"Gamma Tech\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn400BadRequest() throws Exception {
        mvc.perform(post("/customers")
                        .contentType("application/json")
                        .content("{\"name\": }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findByIdShouldReturnOk() throws Exception {
        String response = mvc.perform(post("/customers")
                        .contentType("application/json")
                        .content("{\"name\": \"Customer Test\"}"))
                .andReturn().getResponse().getContentAsString();

        String id = response.split("\"id\":")[1].split(",")[0].trim();

        mvc.perform(get("/customers/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        String response = mvc.perform(post("/customers")
                        .contentType("application/json")
                        .content("{\"name\": \"Customer Old\"}"))
                .andReturn().getResponse().getContentAsString();

        String id = response.split("\"id\":")[1].split(",")[0].trim();

        mvc.perform(put("/customers/" + id)
                        .contentType("application/json")
                        .content("{\"name\": \"Customer Updated\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteOk() throws Exception {
        String response = mvc.perform(post("/customers")
                        .contentType("application/json")
                        .content("{\"name\": \"Customer To Delete\"}"))
                .andReturn().getResponse().getContentAsString();

        String id = response.split("\"id\":")[1].split(",")[0].trim();

        mvc.perform(delete("/customers/" + id))
                .andExpect(status().isOk());
    }
}