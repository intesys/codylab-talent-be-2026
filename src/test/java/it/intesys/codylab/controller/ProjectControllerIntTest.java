package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes  = BeanTestConfiguration.class)
@ActiveProfiles("test")
class ProjectControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturnOk() throws Exception {
        mvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("CRM Aziendale"))
                .andExpect(jsonPath("$[0].status").value("CREATED"))
                .andExpect(jsonPath("$[4].id").value(5))
                .andExpect(jsonPath("$[4].title").value("Dashboard Vendite"))
                .andExpect(jsonPath("$[4].status").value("CLOSED"));
    }

    @Test
    void findAllShouldReturnOkWithProjectDetail() throws Exception {
        mvc.perform(get("/projects/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createDate").value("2026-06-01"))
                .andExpect(jsonPath("$.description").value("Dashboard analitica per monitoraggio KPI commerciali"))
                .andExpect(jsonPath("$.endDate").value("2026-09-01"))
                .andExpect(jsonPath("$.estimatedHours").value(180))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.startDate").value("2026-07-01"))
                .andExpect(jsonPath("$.status").value("CLOSED"))
                .andExpect(jsonPath("$.title").value("Dashboard Vendite"))
                .andExpect(jsonPath("$.updateDate").doesNotExist());
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Progetto Y per post codylab"))
                .andExpect(jsonPath("$.endDate").value("2026-11-01"))
                .andExpect(jsonPath("$.estimatedHours").value(360))
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.startDate").value("2026-08-01"))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.title").value("Progetto CodyLab Y"))
                .andExpect(jsonPath("$.updateDate").doesNotExist());

    }

    @Test
    void shouldDeleteOk() throws Exception {
        String projectId = "6";

        mvc.perform(get("/projects/" + projectId))
                .andExpect(status().isOk());

        mvc.perform(delete("/projects/" + projectId))
                .andExpect(status().isOk());

        mvc.perform(get("/projects/" + projectId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateProject() throws Exception {
        String projectId = "7";

        mvc.perform(get("/projects/" + projectId))
                .andExpect(status().isOk());

        mvc.perform(put("/projects/" + projectId)
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
                .andExpect(status().isOk());

        mvc.perform(get("/projects/" + projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Progetto Y per post codylab"))
                .andExpect(jsonPath("$.endDate").value("2026-11-01"))
                .andExpect(jsonPath("$.estimatedHours").value(360))
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.startDate").value("2026-08-01"))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.title").value("Progetto CodyLab Y"))
        ;
    }
}