package bo;

import java.util.List;

public class Carte {
	private int id;
	private String nom;
	private List<Plat> plats;
	
	public Carte() {
	}
	
	public Carte(String nom) {
		this.nom =nom;
	}
	
	public Carte(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	

	public Carte(String nom, List<Plat> plats) {
		super();
		this.nom = nom;
		this.plats = plats;
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

	
	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	@Override
	public String toString() {
		return "Carte [id=" + id + ", nom=" + nom + ", plats=" + plats + "]";
	}
	
	
	}
