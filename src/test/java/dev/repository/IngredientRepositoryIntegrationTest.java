package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JpaTestConfig;
import dev.entite.Ingredient;
import dto.IngredientDto;

@SpringJUnitConfig(classes = {IngredientRepository.class, JpaTestConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")
public class IngredientRepositoryIntegrationTest {

	@Autowired
	private IngredientRepository repository;
	
	@Test
	public void testRecherche() {
		Optional<IngredientDto> dto = repository.recherche("Sel");
		assertThat(dto.get().getNom()).isEqualTo("Sel");
	}
}
