package bll;

import java.util.List;

import bo.Carte;
import bo.Plat;
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
			if (nom.length() < 2) {
				blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
			}
			
			if (nom.length() > 30) {
				blleException.ajouterErreur("Le nom doit faire maximum 30 caractères");
			}
			try {
				carte.setNom(nom);
				
				dao.insert(carte);
				
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
			return carte;
		}
		
		
		public void insertPlatCarte(int id, List<Plat> plats) throws BLLException {
			BLLException blleException = new BLLException();

			for (Plat plat : plats) {
				if (plat.getId() == 0) {
					blleException.ajouterErreur("Un plat de la liste a un ID null");
					break;  
				}

				if (blleException.getErreurs().size()>0) {
					throw blleException;
				}

				try {
					CarteDAOJdbcImpl carteDao = new CarteDAOJdbcImpl();
					carteDao.insertPlatCarte(id, plats);

				} catch (DALException e) {
					throw new BLLException("Echec de l'insertion", e);
				}
			}
		}
		
		public void insertCarteDansRestaurant(int idCarte, int idRestaurant) throws BLLException {
			BLLException blleException = new BLLException();
			
			try {
				CarteDAOJdbcImpl carteDao = new CarteDAOJdbcImpl();
				carteDao.insertCarteDansRestaurant(idCarte,idRestaurant);
				
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
		}
		
		
		
		
		
		public void update(Carte carte) throws BLLException {
			BLLException blleException = new BLLException();
			if (carte.getNom().length() < 2) {
				blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
			}
			
			if (carte.getNom().length() > 30) {
				blleException.ajouterErreur("Le nom doit faire maximum 30 caractères");
			}
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


