package bll;

import bo.Categorie;
import bo.Plat;
import dal.DALException;
import dal.GenericDAO;
import dal.PlatDAOjdbcImpl;

public class PlatBLL {
	private GenericDAO<Plat> dao;
	
	
	public PlatBLL() throws BLLException {
		dao = new PlatDAOjdbcImpl();
	}
	
	public Plat insert(String nom, String description, float prix, Categorie categoriePlat) throws BLLException {

		Plat plat = new Plat(nom, description, prix,categoriePlat );
		try {
			dao.insert(plat);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return plat;
	}

}
