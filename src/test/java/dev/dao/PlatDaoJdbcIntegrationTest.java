package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JdbcTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoJdbc.class, DataSourceH2TestConfig.class, JdbcTestConfig.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJdbcIntegrationTest {

	@Autowired
	private PlatDaoJdbc dao;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> resultats = dao.listerPlats();
		assertThat(resultats).isNotEmpty();
	}
	
	@Test
	void ajouterPlatValide() {
		dao.ajouterPlat("Gloubiboulga", 1500);
		List<Plat> resultat = jdbcTemplate.query("select * from PLAT where NOM='Gloubiboulga' and PRIX=1500", new PlatMapper());
		assertThat(resultat).isNotEmpty();
	}

}
