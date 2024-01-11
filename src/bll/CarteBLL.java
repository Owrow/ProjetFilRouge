package bll;

import java.util.List;

import bo.Carte;
import dal.CarteDAOJdbcImpl;
import dal.DALException;
import dal.GenericDAO;

public class CarteBLL {
		private GenericDAO<Carte> dao;
		
		public CarteBLL() throws BLLException {
			try {
				dao = new CarteDAOJdbcImpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
		}
		
		public List<Carte> selectAll() throws BLLException {
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des composants", e);
			}
		}
		
		public Carte selectById(int id) throws BLLException {
			try {
				return dao.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
			}
		}
		
		public Carte insert(String nom) throws BLLException {
			BLLException blleException = new BLLException();
			Carte carte = new Carte(nom);
			try {
				dao.insert(carte);
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
			return carte;
		}
		
		public void update(Carte carte) throws BLLException {
			
			try {
				dao.update(carte);
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


