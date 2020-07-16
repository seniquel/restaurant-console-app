package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

@Profile("jpa")
public class PlatDaoJpa implements IPlatDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Plat> listerPlats() {
		return em.createQuery("from Plat", Plat.class).getResultList();
	}

	@Override
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat();
		plat.setNom("jus de pieds");
		plat.setPrixEnCentimesEuros(1200);
		em.persist(plat);
	}

}
