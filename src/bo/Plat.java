package bo;

import java.util.List;

public class Plat {
	
	private int id;
	private String nom;
	private String description;
	private float prix;
	private List<Carte> cartes;

	private Categorie categorie;
	

	public Plat() {
		super();
	}
	
	
	public Plat(int id, String nom, String description, float prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		
	}
	



	public Plat(String nom, String description, float prix) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}



	public Plat(String nom, String description, float prix, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
	}
	public Plat(int id, String nom, String description, float prix, Categorie categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
	}
	

	
	
	public Plat(String nom, String description, float prix, List<Carte> cartes, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.cartes = cartes;
		this.categorie = categorie;
	}


	public List<Carte> getCartes() {
		return cartes;
	}


	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Plat [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", cartes="
				+ cartes + ", categorie=" + categorie + "]";
	}
	
	
	
	
	

}
