package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
	List<Plat> findByPrixEnCentimesEuros(int prix);

	@Query("select avg(prixEnCentimesEuros) from Plat where prixEnCentimesEuros>?1")
    double avgPrix(int prixMinimum);
	
	@Query("select i from Plat p join p.ingredients i where p.nom=?1")
	//@Query("select i from Ingredient i join i.plats p where p.nom=?1")
	List<Ingredient> FindIngredientsByNom(String nom);
}
