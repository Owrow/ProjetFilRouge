package bll;

import java.util.List;

import bo.Table;
import dal.DALException;
import dal.GenericDao;
import dal.TableDAOJdbcImpl;

public class TableDll {
		private GenericDao<Table> dao;
		
		public TableDll() throws BLLException {
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
		
		public Table insert(int numero, int nombre_places) throws BLLException {
			BLLException blleException = new BLLException();
			Table table = new Table(numero, nombre_places);
			try {
				dao.insert(table);
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
			return table;
		}
		
		public void update(Table table) throws BLLException {
			
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


