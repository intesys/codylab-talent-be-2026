package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerIntTest extends BaseControllerIntTest {

    @Autowired
    MockMvc mvc;

    @Test
    void registerShouldReturn201() throws Exception {
        mvc.perform(post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "Mario",
                                    "surname": "Rossi",
                                    "username": "mario.rossi",
                                    "password": "Password1!",
                                    "confirmPassword": "Password1!"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.username").value("mario.rossi"))
                .andExpect(jsonPath("$.accessToken").value(notNullValue()));
    }

    @Test
    void registerShouldReturn400WhenPasswordsDontMatch() throws Exception {
        mvc.perform(post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content("""
                                {
                                    "username": "mario.rossi2",
                                    "password": "Password1!",
                                    "confirmPassword": "DifferentPass1!"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerShouldReturn400WhenUsernameMissing() throws Exception {
        mvc.perform(post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content("""
                                {
                                    "password": "Password1!",
                                    "confirmPassword": "Password1!"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerShouldReturn400WhenPasswordTooWeak() throws Exception {
        mvc.perform(post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content("""
                                {
                                    "username": "mario.rossi3",
                                    "password": "weak",
                                    "confirmPassword": "weak"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginShouldReturn200WithToken() throws Exception {
        registerUser("loginuser", "Password1!");

        mvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                    "username": "loginuser",
                                    "password": "Password1!"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.username").value("loginuser"))
                .andExpect(jsonPath("$.accessToken").value(notNullValue()));
    }

    @Test
    void loginShouldReturn401WhenWrongPassword() throws Exception {
        registerUser("loginuser2", "Password1!");

        mvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                    "username": "loginuser2",
                                    "password": "WrongPass1!"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginShouldReturn401WhenUserNotFound() throws Exception {
        mvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content("""
                                {
                                    "username": "nonexistent",
                                    "password": "Password1!"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpointShouldReturn401WithoutToken() throws Exception {
        mvc.perform(get("/api/v1/projects"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpointShouldReturn401WithInvalidToken() throws Exception {
        mvc.perform(get("/api/v1/projects")
                        .header("Authorization", "Bearer invalidtoken"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpointShouldReturn200WithValidToken() throws Exception {
        String token = registerAndLogin("tokenuser", "Password1!");

        mvc.perform(get("/api/v1/projects")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private void registerUser(String username, String password) throws Exception {
        mvc.perform(post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content(String.format("""
                                {
                                    "name": "Test",
                                    "surname": "User",
                                    "username": "%s",
                                    "password": "%s",
                                    "confirmPassword": "%s"
                                }
                                """, username, password, password)))
                .andExpect(status().isCreated());
    }

    private String registerAndLogin(String username, String password) throws Exception {
        registerUser(username, password);

        var result = mvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(String.format("""
                                {"username": "%s", "password": "%s"}
                                """, username, password)))
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString()
                .replaceAll(".*\"accessToken\"\\s*:\\s*\"([^\"]+)\".*", "$1");
    }
}
