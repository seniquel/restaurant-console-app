package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

public class PlatDaoMemoireTest {
	
	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
	this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {
	List<Plat> resultat = platDaoMemoire.listerPlats();
	assertThat(resultat).isEmpty();
	}

	@Test
	void ajouterPlatCasPassants() {
	
	List<Plat> listePlats = new ArrayList<>();
	listePlats.add(new Plat("plat0",1));
	listePlats.add(new Plat("plat1", 0));
	listePlats.add(new Plat("plat2", -1));
	listePlats.forEach(p -> platDaoMemoire.ajouterPlat(p.getNom(), p.getPrixEnCentimesEuros()));
	assertThat(platDaoMemoire.listerPlats()).containsAll(listePlats);
	}
}
