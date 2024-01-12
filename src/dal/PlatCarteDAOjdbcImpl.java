package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Plat;
import bo.PlatCarte;

public class PlatCarteDAOjdbcImpl implements GenericDAO<PlatCarte> {

	private static final String TABLE_NAME = " plat_cartes ";

	private static final String DELETE = "DELETE FROM"+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET id_carte = ?, id_plat = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (id_plat, id_carte) VALUES (?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;

	private Connection cnx;


	public PlatCarteDAOjdbcImpl()throws DALException {

		cnx = ConnectionProvider.getConnection();
	}

	public List<PlatCarte> selectAll() throws DALException{
		List<PlatCarte> listePlat = new ArrayList<>();


		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PlatCarte platCarte = new PlatCarte();
				Plat plat = new Plat();
				Carte carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				plat.setId(rs.getInt("id_plat"));
				platCarte.setCarte(carte);
				platCarte.setPlat(plat);
				platCarte.setId(rs.getInt("id"));

				listePlat.add(platCarte);

			} 
		}catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du plat_carte", e);
		}
	return listePlat;
	}

	public PlatCarte selectById(int id) throws DALException  {
		PlatCarte platCarte = null;
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				platCarte = new PlatCarte();
				Plat plat = new Plat();
				Carte carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				plat.setId(rs.getInt("id_plat"));
				platCarte.setCarte(carte);
				platCarte.setPlat(plat);
				platCarte.setId(rs.getInt("id"));

			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du plat_carte de l'id", e);
		}




		return platCarte;
	}

	public void insert(PlatCarte platCarte) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, platCarte.getCarte().getId());
			ps.setInt(2, platCarte.getPlat().getId());

			ps.executeUpdate();


			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { 
				int id = rs.getInt(1); 
				platCarte.setId(rs.getInt(id));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees du plat_carte", e);

		}
	}


	public void update(PlatCarte platCarte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setInt(1, platCarte.getCarte().getId());
			ps.setInt(2, platCarte.getPlat().getId());
			ps.setInt(3, platCarte.getId());
			platCarte.setId(platCarte.getId());
			ps.executeUpdate();


		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les informations de plat_carte", e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression dde plat_carte " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer ce plat_carte d'id " + id, e);
		}
	}
		
		}

