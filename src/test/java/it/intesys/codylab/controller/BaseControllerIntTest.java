package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = BeanTestConfiguration.class)
@ActiveProfiles("test")
public abstract class BaseControllerIntTest {

    @Autowired
    MockMvc mvc;

    private static String authToken;

    @BeforeEach
    void setUpAuth() throws Exception {
        if (authToken == null) {
            String username = "testuser_" + System.currentTimeMillis();
            mvc.perform(post("/api/v1/auth/register")
                            .contentType("application/json")
                            .content(String.format("""
                                    {
                                        "name": "Test",
                                        "surname": "User",
                                        "username": "%s",
                                        "password": "Password1!",
                                        "confirmPassword": "Password1!"
                                    }
                                    """, username)))
                    .andExpect(status().isCreated());

            var result = mvc.perform(post("/api/v1/auth/login")
                            .contentType("application/json")
                            .content(String.format("""
                                    {"username": "%s", "password": "Password1!"}
                                    """, username)))
                    .andExpect(status().isOk())
                    .andReturn();

            authToken = result.getResponse().getContentAsString()
                    .replaceAll(".*\"accessToken\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        }
    }

    protected String authToken() {
        return authToken;
    }
}
