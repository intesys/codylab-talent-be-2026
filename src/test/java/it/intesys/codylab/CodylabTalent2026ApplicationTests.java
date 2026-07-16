package it.intesys.codylab;

import it.intesys.codylab.config.BeanTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes  = BeanTestConfiguration.class)
@ActiveProfiles("test")
class CodylabTalent2026ApplicationTests {

	@Test
	void contextLoads() {
	}

}
