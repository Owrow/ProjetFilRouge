package bo;

import java.time.LocalTime;

public class Restaurant {

	private int id;
	private Carte carte;
	private String nom;
	private String adresse;
	private LocalTime ouverture;
	private LocalTime fermeture;
	
	public Restaurant() {
		super();
	}
	public Restaurant(String nom, String adresse, LocalTime ouverture, LocalTime fermeture, Carte carte) {
		super();
		this.nom = nom;
		this.carte = carte;
		this.adresse = adresse;
		this.ouverture = ouverture;
		this.fermeture = fermeture;
	}

	public Restaurant(String nom, String adresse, LocalTime ouverture, LocalTime fermeture) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.ouverture = ouverture;
		this.fermeture = fermeture;
	}
	public Restaurant(int id, String nom, String adresse, LocalTime ouverture, LocalTime fermeture, Carte carte) {
		super();
		this.id = id;
		this.carte = carte;
		this.nom = nom;
		this.adresse = adresse;
		this.ouverture = ouverture;
		this.fermeture = fermeture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public LocalTime getOuverture() {
		return ouverture;
	}
	public void setOuverture(LocalTime ouverture) {
		this.ouverture = ouverture;
	}
	public LocalTime getFermeture() {
		return fermeture;
	}
	public void setFermeture(LocalTime fermeture) {
		this.fermeture = fermeture;
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", ouverture=" + ouverture
				+ ", fermeture=" + fermeture + "]";
	}

}
