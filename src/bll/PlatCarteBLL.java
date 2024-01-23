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
		private PlatCarteDAOjdbcImpl daoPlatCarte;
		
		public PlatCarteBLL() throws BLLException {
			try {
				daoPlatCarte = new PlatCarteDAOjdbcImpl();
				dao = new PlatCarteDAOjdbcImpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
		}
		
		public List<PlatCarte> selectAll() throws BLLException {
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des plats cartes", e);
			}
		}
		
		public PlatCarte selectById(int id) throws BLLException {
			try {
				return dao.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation du plat carte d'id " + id, e);
			}
		}
		
		
		public PlatCarte selectByPlatCarte(int id) throws BLLException{

			try {
				return daoPlatCarte.selectByIdCarte(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des plats et cartes pour l'id de carte" + id, e);
			}
			
		}
		
		
		public PlatCarte insert(Plat plat, Carte carte) throws BLLException {
			BLLException blleException = new BLLException();						
			PlatCarte platCarte = new PlatCarte(plat, carte);
			try {
				dao.insert(platCarte);
			} catch (DALException e) {
				throw new BLLException("Echec de l'insertion", e);
			}
			return platCarte;
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


