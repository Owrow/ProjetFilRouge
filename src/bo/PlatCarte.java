package bo;

public class PlatCarte {
	private int id;
	private Plat plat;
	private Carte carte;
	
	public PlatCarte() {
		super();
	}

	public PlatCarte(Plat plat, Carte carte) {
		super();
		this.plat = plat;
		this.carte = carte;
	}

	public PlatCarte(int id, Plat plat, Carte carte) {
		super();
		this.id = id;
		this.plat = plat;
		this.carte = carte;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Plat getPlat() {
		return plat;
	}


	public void setPlat(Plat plat) {
		this.plat = plat;
	}


	public Carte getCarte() {
		return carte;
	}


	public void setCarte(Carte carte) {
		this.carte = carte;
	}


	@Override
	public String toString() {
		return "PlatCarte [id=" + id + ", plat=" + plat + ", carte=" + carte + "]";
	}
}
