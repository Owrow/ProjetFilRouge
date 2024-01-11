package bo;

public class Carte {
	private int id;
	private String nom;
	
	public Carte() {
	}
	
	public Carte(String nom) {
	}
	
	public Carte(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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

	@Override
	public String toString() {
		return "Carte [id=" + id + ", nom=" + nom + "]";
	}
	}
