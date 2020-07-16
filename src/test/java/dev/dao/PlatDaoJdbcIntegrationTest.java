package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoJdbc.class, JdbcTestConfig.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJdbcIntegrationTest {
	
	@Autowired
	private PlatDaoJdbc dao;
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> resultats = dao.listerPlats();
		assertThat(resultats).isNotEmpty();
	}

}
