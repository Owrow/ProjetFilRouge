package bo;

public class Plat {
	
	private int id;
	private String nom;
	private String description;
	private float prix;
	private Categorie categoriePlat;
	
	
	
	public Plat(String nom, String description, float prix, Categorie categoriePlat) {
		
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.categoriePlat = categoriePlat;
	}



	public Plat() {
		
	}
	
	

 public Plat(int id, String nom, String description, float prix) {
		
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		
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



	public Categorie getCategoriePlat() {
		return categoriePlat;
	}



	public void setCategoriePlat(Categorie categoriePlat) {
		this.categoriePlat = categoriePlat;
	}



	@Override
	public String toString() {
		return "Plat [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + "]";
	}
	
	
	

}
