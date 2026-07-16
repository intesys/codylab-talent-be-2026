package it.intesys.codylab.controller;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes  = BeanTestConfiguration.class)
@ActiveProfiles("test")
public abstract class BaseControllerIntTest {
}
