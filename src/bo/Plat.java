package bo;

public class Plat {
	
	private int id;
	private String nom;
	private String description;
	private float prix;

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
		return "Plat [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", categorie="
				+ categorie + "]";
	}
	
	
	
	
	

}
