package dev.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private int id;
    @Column(name = "NOM")
    private String nom;
    @ManyToMany(mappedBy = "ingredients")
    private List<Plat> plats = new ArrayList<Plat>();
    
    public Ingredient() {
    	
    }
    
	public Ingredient(String nom) {
		this.nom = nom;
	}
	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Getter
	 * @return the plats
	 */
	public List<Plat> getPlats() {
		return plats;
	}
	/** Setter
	 * @param plats the plats to set
	 */
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
    
    
}
