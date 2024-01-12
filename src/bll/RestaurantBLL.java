package bll;

import java.time.LocalTime;
import java.util.List;

import bo.Carte;
import bo.Restaurant;
import dal.DALException;
import dal.GenericDAO;
import dal.RestaurantDAOjdbcImpl;


public class RestaurantBLL {
	private GenericDAO<Restaurant> dao;

	public RestaurantBLL() throws BLLException {
		try {
			dao = new RestaurantDAOjdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	public List<Restaurant> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des restaurants", e);
		}
	}

	public Restaurant selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du restaurant d'id " + id, e);
		}
	}

	public Restaurant insert(String nom, String adresse, LocalTime ouverture, LocalTime fermeture) throws BLLException {
		Restaurant restaurant = new Restaurant(nom, adresse, ouverture, fermeture);
		BLLException blleException = new BLLException();
		if (nom.length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (nom.length() > 30) {
			blleException.ajouterErreur("Le nom doit faire maximum 30 caractères");
		}
		
		if (adresse.length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (adresse.length() > 80) {
			blleException.ajouterErreur("Le nom doit faire maximum 80 caractères");
		}
		
		try {
			dao.insert(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return restaurant;
	}

	public void update(Restaurant restaurant) throws BLLException {

		BLLException blleException = new BLLException();
		if (restaurant.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (restaurant.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom doit faire maximum 30 caractères");
		}
		
		if (restaurant.getAdresse().length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (restaurant.getAdresse().length() > 80) {
			blleException.ajouterErreur("Le nom doit faire maximum 80 caractères");
		}
		try {
			dao.update(restaurant);
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
