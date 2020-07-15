package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

//@SpringJUnitConfig(classes = {PlatServiceVersion2.class, PlatDaoMemoire.class})
@ContextConfiguration(classes = {PlatServiceVersion2.class, PlatDaoMemoire.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"memoire","V2"})
public class PlatServiceVersion2Test {
	
	@Autowired
	private PlatServiceVersion2 service;
	
	@Test
	void ajouterPlatValide() {
		service.ajouterPlat("cuisse de Rossi", 2000);
		List<Plat> resultats = service.listerPlats();
		assertThat(resultats).extracting(Plat::getNom).containsExactly("cuisse de Rossi");
		assertThat(resultats).extracting(Plat::getPrixEnCentimesEuros).containsExactly(2000);
	}
	
	@Test
	void ajouterPlatInvalide() {
		assertThatThrownBy(() -> service.ajouterPlat("nem", 50))
			.isInstanceOf(PlatException.class)
			.hasMessage("un plat doit avoir un nom de plus de 5 caractères");
	}
}
