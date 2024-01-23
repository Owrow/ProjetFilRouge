package bll;

import java.util.List;

import bo.Table;
import dal.DALException;
import dal.GenericDAO;
import dal.TableDAOJdbcImpl;

public class TableBLL {
		private GenericDAO<Table> dao;
		
		public TableBLL() throws BLLException {
			try {
				dao = new TableDAOJdbcImpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
		}
		
		public List<Table> selectAll() throws BLLException {
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des composants", e);
			}
		}
		
		public Table selectById(int id) throws BLLException {
			try {
				return dao.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
			}
		}
		
		public void insert(int numero, int nombre_places, int id_restaurant) throws BLLException {
			BLLException blleException = new BLLException();
			Table table = new Table(numero, nombre_places);
			if (numero < 0)
				blleException.ajouterErreur("Le numéro de table doit être positif");
			if (nombre_places < 0)
				blleException.ajouterErreur("Le nombre de place doit être positif");
			
			try {
				TableDAOJdbcImpl tableDao = new TableDAOJdbcImpl();
				tableDao.insert(table,id_restaurant);
				
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}

		}
		
		public void update(Table table) throws BLLException {
			BLLException blleException = new BLLException();
			if (table.getNumero() < 0)
				blleException.ajouterErreur("Le numéro de table doit être positif");
			if (table.getNombre_places() < 0)
				blleException.ajouterErreur("Le nombre de place doit être positif");
			
			try {
				dao.update(table);
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


