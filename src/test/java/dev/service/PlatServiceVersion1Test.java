package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

public class PlatServiceVersion1Test {

	private PlatServiceVersion1 platService;
	IPlatDao dao;
	
	@BeforeEach
	void setUp() {
		dao = mock(IPlatDao.class);
		platService = new PlatServiceVersion1(dao);
	}
	
	@Test
	void ajouterPlatNomInvalide() {
		Throwable thrown = null;
		try {
			platService.ajouterPlat("blé", 600);
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown).isInstanceOf(PlatException.class);
	}
	
	@Test
	void ajouterPlatPrixInvalide() {
		Throwable thrown = null;
		try {
			platService.ajouterPlat("grain de riz", 20);
		} catch (Exception e) {
			thrown = e;
		}
		assertThat(thrown).isInstanceOf(PlatException.class);	
	}
	
	@Test
	void ajouterPlatValide() {
		platService.ajouterPlat("chaussette", 4500);
		verify(dao).ajouterPlat("chaussette", 4500);
	}
	
	
}
