package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Categorie;
import bo.Plat;

public class PlatDAOjdbcImpl implements GenericDAO<Plat> {

	private static final String TABLE_NAME = "plats";

	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET nom = ?, description_plats = ?, prix = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (nom, description_plat, prix, id_categorie) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT p.id, p.nom, p.description_plat, p.prix, c.id AS id_categorie, c.nom AS nom_categorie FROM "
			+ TABLE_NAME + " p LEFT JOIN categories c ON c.id = p.id_categorie WHERE p.id = ?";

	private static final String SELECT = "SELECT p.id, p.nom, p.description_plat, p.prix, c.id AS id_categorie, c.nom AS nom_categorie FROM "
			+ TABLE_NAME + " p LEFT JOIN categories c ON c.id = p.id_categorie";

	private Connection cnx;

	public PlatDAOjdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Plat> selectAll() throws DALException {
		List<Plat> listePlat = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plat plat = new Plat();				
				plat.setNom(rs.getString("nom"));
				plat.setDescription(rs.getString("description_plat"));
				plat.setPrix(rs.getFloat("prix"));
				
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("id_categorie"));
				categorie.setNom(rs.getString("nom_categorie"));

				plat.setCategorie(categorie);
			

				listePlat.add(plat);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du plat", e);
		}

		return listePlat;
	}

	public Plat selectById(int id) throws DALException {
		Plat plat = null;
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				plat = new Plat();
				plat.setNom(rs.getString("nom"));
				plat.setDescription(rs.getString("description_plat"));
				plat.setPrix(rs.getFloat("prix"));

			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du plat de l'id", e);
		}

		return plat;
	}

	public void insert(Plat plat) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, plat.getNom());
			ps.setFloat (3, plat.getPrix());
			ps.setString(2, plat.getDescription());
			ps.setFloat(3, plat.getPrix());
			ps.setInt(4, plat.getCategorie().getId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				plat.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees du plat", e);

		}
	}

	public void update(Plat plat) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, plat.getNom());
			ps.setString(2, plat.getDescription());
			ps.setFloat(3, plat.getPrix());
			plat.setId(plat.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les informations du plat", e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du plat d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer ce plat d'id " + id, e);
		}
	}

}
