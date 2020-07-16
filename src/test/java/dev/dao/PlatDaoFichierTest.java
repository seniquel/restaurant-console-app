package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoFichier.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {

	@Autowired
	private PlatDaoFichier dao;

	@Test
	void ajouterPlatSauvegarde() {
		dao.ajouterPlat("plat du jour", 1500);
		List<Plat> resultats = dao.listerPlats();
		assertThat(resultats).extracting(Plat::getNom).containsExactly("plat du jour");
		assertThat(resultats).extracting(Plat::getPrixEnCentimesEuros).containsExactly(1500);
		assertThat(resultats).hasSize(1);
	}

	@Test
	void ajouterPlatSauvegarde2() {
		dao.ajouterPlat("plat du jour 2", 2000);
		List<Plat> resultats = dao.listerPlats();
		assertThat(resultats).extracting(Plat::getNom).containsExactly("plat du jour 2");
		assertThat(resultats).extracting(Plat::getPrixEnCentimesEuros).containsExactly(2000);
		assertThat(resultats).hasSize(1);	
	}


}
