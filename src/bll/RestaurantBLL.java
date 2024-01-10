package bll;

import java.time.LocalTime;
import java.util.List;

import bo.Restaurant;
import dal.DALException;
import dal.GenericDao;
import dal.RestaurantDAOjdbcImpl;


public class RestaurantBLL {
	private GenericDao<Restaurant> dao;

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
		try {
			dao.insert(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return restaurant;
	}

	public void update(Restaurant restaurant) throws BLLException {

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
