package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturnOk() throws Exception {
        mvc.perform(get("/projects"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllShouldReturnOkWithProjectDetail() throws Exception {
        mvc.perform(get("/projects/5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"createDate\": \"2026-06-01\",\n" +
                        "    \"description\": \"Dashboard analitica per monitoraggio KPI commerciali\",\n" +
                        "    \"endDate\": \"2026-09-01\",\n" +
                        "    \"estimatedHours\": 180,\n" +
                        "    \"id\": 5,\n" +
                        "    \"startDate\": \"2026-07-01\",\n" +
                        "    \"status\": \"CLOSED\",\n" +
                        "    \"title\": \"Dashboard Vendite\",\n" +
                        "    \"updateDate\": null\n" +
                        "}"));
    }

    @Test
    void findAllShouldReturn404NotFound() throws Exception {
        mvc.perform(get("/projects/5000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400BadRequest() throws Exception {
        mvc.perform(post("/projects")
                .contentType("application/json")
                .content("""
{
    "description": "Progetto x per post codylab",
    "endDate": "2026-11-01",
    "estimatedHours": 360,
    "startDate": "2026-08-01",
    "createDate": "2026-07-09",
    "status": "OPEN",
    "title": "Progetto CodyLab x"
}                        
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn201Created() throws Exception {
        MvcResult result = mvc.perform(post("/projects")
                        .contentType("application/json")
                        .content("""
{
    "description": "Progetto Y per post codylab",
    "endDate": "2026-11-01",
    "estimatedHours": 360,
    "startDate": "2026-08-01",
    "createDate": "2026-07-09",
    "status": "CREATED",
    "title": "Progetto CodyLab Y"
}                        
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location")).andReturn();

        String projectId = result.getResponse().getContentAsString();
        mvc.perform(get("/projects/" + projectId))
                .andExpect(status().isOk());
    }
}