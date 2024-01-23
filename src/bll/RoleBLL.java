package bll;

import java.util.List;

import bo.Role;
import dal.DALException;
import dal.GenericDAO;
import dal.RoleDAOjdbcImpl;

public class RoleBLL {
		private GenericDAO<Role> dao;
		
		public RoleBLL() throws BLLException {
			try {
				dao = new RoleDAOjdbcImpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
		}
		
		public List<Role> selectAll() throws BLLException {
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des roles", e);
			}
		}
		
		public Role selectById(int id) throws BLLException {
			try {
				return dao.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation du role d'id " + id, e);
			}
		}
		
		public void insert(String libelle) throws BLLException {
			BLLException blleException = new BLLException();
			Role role = new Role(libelle);

			try {
				RoleDAOjdbcImpl roleDao = new RoleDAOjdbcImpl();
				roleDao.insert(role);
				
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}

		}
		
		public void update(Role role) throws BLLException {
			BLLException blleException = new BLLException();
			
			try {
				dao.update(role);
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


