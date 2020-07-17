package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.JpaTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatRepository.class, JpaTestConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")
public class PlatRepositoryIntegrationTest {
	
	@Autowired
	private PlatRepository repository;

	@Test
	public void testFindAll() {
		List<Plat> plats = repository.findAll();
		assertThat(plats).hasSize(6);
	}
	
	@Test
	public void testFindAllSortAsc() {
		List<Plat> plats = repository.findAll(Sort.by(Sort.Direction.ASC, "prixEnCentimesEuros"));
		assertThat(plats).isSortedAccordingTo((p1,p2)->p1.getPrixEnCentimesEuros().compareTo(p2.getPrixEnCentimesEuros()));
	}
	
	@Test
	public void testFindAllSortDesc() {
		List<Plat> plats = repository.findAll(Sort.by(Sort.Direction.DESC, "prixEnCentimesEuros"));
		assertThat(plats).isSortedAccordingTo((p1,p2)->p2.getPrixEnCentimesEuros().compareTo(p1.getPrixEnCentimesEuros()));	
	}
	
	@Test
	public void testFindAllPageable() {
		List<Plat> plats = repository.findAll(PageRequest.of(0,2,Sort.by("nom").ascending())).toList();
		assertThat(plats).extracting(Plat::getNom).containsExactly("Blanquette de veau","Couscous");
	}
	
	@Test
	public void testFindById() {
		Optional<Plat> plat = repository.findById(1);
		assertThat(plat).contains(new Plat("Magret de canard",1300));
	}
	
	@Test
	@Transactional //getOne charge l'objet en Lazy, il faut donc un contexte transactionel pour obtenir les valeurs
	public void testGetOne() {
		Plat plat = repository.getOne(1);
		assertThat(plat.getNom()).isEqualTo("Magret de canard");
		assertThat(plat.getPrixEnCentimesEuros()).isEqualTo(1300);
	}
	
	@Test
	public void testCount() {
		assertThat(repository.count()).isEqualTo(6);
	}
	
	@Test
	public void testFindByPrix() {
		List<Plat> plats = repository.findByPrixEnCentimesEuros(1300);
		assertThat(plats).hasSize(2);
	}
	
	@Test
	public void testAvgPrix() {
		double res = repository.avgPrix(1500);
		assertThat(res).isEqualTo(2050); //avg({1600,2500}) = 2050
	}
	
	@Test
	public void testFindByNomWithIngredients() {
		assertThat(repository.FindByNomWithIngredients("Moules-frites")).hasSize(6);
	}
	
	@Test
	@Transactional
	public void testSave() {
		Plat plat = new Plat("Tarte aux escargots",1200);
		repository.save(plat);
		assertThat(repository.findAll()).contains(plat);
	}
	
	@Test
	@Transactional
	public void testChangerNom() {
		repository.ChangerNomPlat("Magret de canard", "Confit de canard");
		List<Plat> plats = repository.findAll();
		assertThat(plats).extracting(Plat::getNom).contains("Confit de canard");
		assertThat(plats).extracting(Plat::getNom).doesNotContain("Magret de canard");
	}
}
