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
