package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import dev.entite.Plat;

// EMF + Gestion Tx + DataSource
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class IPlatDaoIntegrationTest {

	@Autowired
	private IPlatDao dao;

	//@Autowired
	//DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbc;
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> resultats = dao.listerPlats();
		assertThat(resultats).isNotEmpty();
	}
	
	@Test
	void ajouterPlatValide() {
		dao.ajouterPlat("Gloubiboulga", 1500);

		List<Plat> resultat = jdbc.query(
				"select * from PLAT where NOM=? and PRIX=?",
				new Object[]{"Gloubiboulga", 1500},
				new PlatMapper());

		assertThat(resultat).isNotEmpty();
	}

}
