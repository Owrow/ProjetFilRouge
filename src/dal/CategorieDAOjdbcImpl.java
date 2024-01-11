package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Categorie;

public class CategorieDAOjdbcImpl implements GenericDAO<Categorie> {

	private static final String TABLE_NAME = " categories ";

	private static final String DELETE = "DELETE FROM" + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET nom = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (nom) VALUES (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;

	private Connection cnx;

	public CategorieDAOjdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categories = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNom(rs.getString("nom"));
				categorie.setId(rs.getInt("id"));

				categories.add(categorie);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations de categorie", e);
		}

		return categories;

	}

	public Categorie selectById(int id) throws DALException {
		Categorie categorie = null;

		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categorie = new Categorie();
				categorie.setNom(rs.getString("nom"));
				categorie.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations de la categorie d'id " + id, e);
		}

		return categorie;

	}

	public void insert(Categorie categorie) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, categorie.getNom());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				categorie.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);

		}
	}

	public void update(Categorie categorie) throws DALException {

		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, categorie.getNom());
			ps.setInt(2, categorie.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les données pour la categorie d'id " + categorie.getId(), e);
		}

	}


	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de la categorie d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la categorie d'id " + id, e);
		}
	}
}

