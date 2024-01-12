package bll;

import java.util.List;

import bo.Carte;
import bo.Plat;
import bo.PlatCarte;
import dal.DALException;
import dal.GenericDAO;
import dal.PlatCarteDAOjdbcImpl;

public class PlatCarteBLL {
		private GenericDAO<PlatCarte> dao;
		
		public PlatCarteBLL() throws BLLException {
			try {
				dao = new PlatCarteDAOjdbcImpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
		}
		
		public List<PlatCarte> selectAll() throws BLLException {
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des composants", e);
			}
		}
		
		public PlatCarte selectById(int id) throws BLLException {
			try {
				return dao.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
			}
		}
		
		public PlatCarte insert(Plat plat, Carte carte) throws BLLException {
			BLLException blleException = new BLLException();						
			PlatCarte table = new PlatCarte(plat, carte);
			try {
				dao.insert(table);
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
			return table;
		}
		
		public void update(PlatCarte platCarte) throws BLLException {
			
			try {
				dao.update(platCarte);
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


