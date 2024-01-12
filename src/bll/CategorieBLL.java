package bll;

import java.util.Arrays;
import java.util.List;

import bo.Categorie;
import dal.CategorieDAOjdbcImpl;
import dal.DALException;
import dal.GenericDAO;

public class CategorieBLL {
	private GenericDAO<Categorie> dao;

	public CategorieBLL() throws BLLException {
		try {
			dao = new CategorieDAOjdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);

		}
	}

	public List<Categorie> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des categories", e);
		}
	}

	public Categorie selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de la categorie d'id " + id, e);
		}
	}

	public Categorie insert(String nom) throws BLLException {
		BLLException bbleException = new BLLException();

		List<String> valeursValides = Arrays.asList("Entrée", "Plat", "Dessert", "Boisson");
		if (!valeursValides.contains(nom)) {
			bbleException.ajouterErreur("Le nom du plat doit valoir Entrée, Plat, Dessert ou Boisson");
		}

		Categorie categorie = new Categorie(nom);
		try {
			dao.insert(categorie);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return categorie;
	}

	public void update(Categorie categorie) throws BLLException {

		try {
			dao.update(categorie);
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
