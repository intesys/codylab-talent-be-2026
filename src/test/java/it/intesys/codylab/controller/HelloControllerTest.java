package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HelloControllerTest extends BaseControllerIntTest {

    @Autowired
    MockMvc mvc;
    @Test
    void shouldReturnHelloWorld() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void shouldReturnHelloStudentName() throws Exception {
        String nome = "Studente";
        mvc.perform(post("/hello").content(nome))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello " + nome));
    }

}