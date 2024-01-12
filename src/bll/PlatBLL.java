package bll;


import java.util.List;


import bo.Categorie;
import bo.Plat;
import dal.DALException;
import dal.GenericDAO;
import dal.PlatDAOjdbcImpl;

public class PlatBLL {
	private GenericDAO<Plat> dao;

	public PlatBLL() throws BLLException {
		try {
			dao = new PlatDAOjdbcImpl();

		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	public List<Plat> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des plats", e);
		}
	}

	public Plat selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du plat d'id " + id, e);
		}
	}

	public Plat insert(String nom, String description_plat, float prix, Categorie categorie) throws BLLException {
		BLLException blleException = new BLLException();
		if (nom.length() < 5) {
			blleException.ajouterErreur("Le nom doit faire au moins 5 caractères");
		}

		if (nom.length() > 50) {
			blleException.ajouterErreur("Le nom doit faire maximum 50 caractères");
		}

		if (description_plat.length() < 5) {
			blleException.ajouterErreur("Le nom doit faire au moins 10 caractères");
		}

		if (description_plat.length() > 100) {
			blleException.ajouterErreur("Le nom doit faire maximum 100 caractères");
		}

		if (prix < 0) {
			blleException.ajouterErreur("Le prix doit être un nombre positif");
		} else {
			String prixAsString = String.valueOf(prix);
			if (!prixAsString.matches("\\d+(\\.\\d+)?")) {

				blleException.ajouterErreur(
						"Le prix doit contenir uniquement des chiffres et éventuellement un point décimal");
			}
		}

		Plat plat = new Plat(nom, description_plat, prix, categorie);
		try {
			dao.insert(plat);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return plat;
	}

	public void update(Plat plat) throws BLLException {

		try {
			dao.update(plat);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour", e);
		}
	}

	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
}
