package bo;

import java.util.List;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String telephone;
	private String mdp;
	private int id_role;

	public Client() {
	}


	public Client(int id, String nom, String prenom, String mail, String telephone, String mdp, int id_role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.mdp = mdp;
		this.id_role = id_role;
	}

	public Client(String nom, String prenom, String mail, String telephone, String mdp, int id_role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.mdp = mdp;
		this.id_role = id_role;
	}
	
	


	public Client(String nom, String prenom, String mail, String telephone, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.mdp = mdp;
	}


	public Client(int id, String nom, String prenom, String mail, String telephone, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.telephone = telephone;
		this.mdp = mdp;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	
	public int getRole() {
		return id_role;
	}

	public void setRole(int id_role) {
		this.id_role = id_role;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", telephone="
				+ telephone + ", mdp=" + mdp + ", role=" + id_role + "]";
	}

	
	
}
