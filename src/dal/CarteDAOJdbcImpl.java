package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Plat;

public class CarteDAOJdbcImpl implements GenericDAO<Carte>{
private static final String TABLE_NAME = " cartes ";
	
	private static final String DELETE = "DELETE FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET nom = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (nom) VALUES (?)";
	private static final String INSERTPLATCARTE = "INSERT INTO plats_cartes (id_plat,id_carte) VALUES (?,?)";
	private static final String UPDATEINTORESTAURANT = "UPDATE restaurants SET id_carte = ? WHERE id = ?";
	
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;
	
	private Connection cnx;
	
	public CarteDAOJdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}
	
	public List<Carte> selectAll() throws DALException {
		List<Carte> cartes = new ArrayList<>(); 
		
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carte carte = new Carte();
				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));
				cartes.add(carte);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations de la carte", e);

		}
		return cartes;
	}
	
	public Carte selectById(int id) throws DALException {
		Carte carte = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				carte = new Carte();
				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));

}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer la carte d'id", e);
			
		}
		return carte;
	}
	
	public void insert(Carte carte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			ps.setString(1, carte.getNom());
			
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1); 
				carte.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees de la carte", e);
		}
	}
	
	
	public void insertPlatCarte(int id, List<Plat> plats) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(INSERTPLATCARTE);
		
			for (Plat current : plats) {
				ps.setInt(1, current.getId());
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees de la carte et des plats dans la table plats_cartes", e);
		}
	}
	
	public void insertCarteDansRestaurant(int idCarte, int idRestaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATEINTORESTAURANT);

				ps.setInt(1, idCarte);
				ps.setInt(2, idRestaurant);
				ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer l'id de la carte dans le restaurant", e);
		}
	}
	
	public void update(Carte carte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, carte.getNom());
			ps.setInt(2, carte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les informations de la carte", e);
		}
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la carte", e);
		}
	}
	
}
