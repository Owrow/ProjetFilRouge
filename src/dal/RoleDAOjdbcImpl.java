package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Categorie;
import bo.Role;

public class RoleDAOjdbcImpl implements GenericDAO<Role> {

	private static final String TABLE_NAME = "roles";

	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET libelle = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (libelle) VALUES (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "	+ TABLE_NAME + "  WHERE id = ?";

	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;

	private Connection cnx;

	public RoleDAOjdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Role> selectAll() throws DALException {
		List<Role> listeRole = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Role role = new Role();				
				role.setLibelle("libelle");
				listeRole.add(role);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations des roles", e);
		}

		return listeRole;
	}

	public Role selectById(int id) throws DALException {
		Role role = null;
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setLibelle(rs.getString("libelle"));
				}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du role de l'id", e);
		}

		return role;
	}

	public void insert(Role plat) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, plat.getLibelle());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				plat.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees du role", e);

		}
	}

	public void update(Role plat) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, plat.getLibelle());
			plat.setId(plat.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les informations du role", e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du role d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer ce role d'id " + id, e);
		}
	}

}
