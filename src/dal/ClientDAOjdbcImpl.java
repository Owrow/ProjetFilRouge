package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Client;
import bo.Role;

public class ClientDAOjdbcImpl implements GenericDAO<Client> {

	private static final String TABLE_NAME = "clients";

	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET nom = ?, prenom = ?, telephone = ?, mail = ?, mdp = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (nom, prenom, telephone, mail, mdp, id_role) VALUES (?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT c.id, c.nom, c.prenom, c.mail, c.telephone FROM "
			+ TABLE_NAME + " c WHERE c.id = ?";

	private static final String SELECT = "SELECT c.id, c.nom, c.prenom, c.mail, c.telephone FROM "
			+ TABLE_NAME + " c ";

	private Connection cnx;

	public ClientDAOjdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Client> selectAll() throws DALException {
		List<Client> listeClient = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client();				
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setPrenom(rs.getString("mail"));
				client.setPrenom(rs.getString("telephone"));
				client.setPrenom(rs.getString("mdp"));
				
				listeClient.add(client);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations des clients", e);
		}

		return listeClient;
	}

	public Client selectById(int id) throws DALException {
		Client client = null;
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client = new Client();
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setMail(rs.getString("mail"));
				client.setTelephone(rs.getString("telephone"));

			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du client de l'id", e);
		}

		return client;
	}

	public void insert(Client client) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getMail());
			ps.setString(4, client.getTelephone());
			ps.setString(5, client.getMdp());
			ps.setInt(6, 0);
		ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				client.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees du client", e);

		}
	}

	public void update(Client client) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getMail());
			ps.setString(4, client.getTelephone());
			ps.setString(5, client.getMdp());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les informations du client", e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du client d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer ce client d'id " + id, e);
		}
	}


}
