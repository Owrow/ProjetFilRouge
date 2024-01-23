package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Restaurant;

public class RestaurantDAOjdbcImpl implements GenericDAO<Restaurant> {

	private static final String TABLE_NAME = "restaurants";

	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET nom = ?, adresse = ?, ouverture = ?, fermeture = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ " (nom, adresse, ouverture, fermeture) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT r.id, r.nom, r.adresse, r.ouverture, r.fermeture, r.id_carte FROM " + TABLE_NAME + " r WHERE id = ?";
	private static final String SELECT = "SELECT r.id, r.nom, r.adresse, r.ouverture, r.fermeture, r.id_carte FROM " + TABLE_NAME + " r";

	private Connection cnx;
	
	public RestaurantDAOjdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Restaurant> selectAll() throws DALException {
		List<Restaurant> restaurants = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();

				restaurant.setId(rs.getInt("id"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setOuverture(rs.getTime("ouverture").toLocalTime());
				restaurant.setFermeture(rs.getTime("fermeture").toLocalTime());
				
				Carte carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				restaurant.setCarte(carte);
				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du restaurant", e);
		}
		return restaurants;
	}

	public Restaurant selectById(int id) throws DALException {
		Restaurant restaurant = null;
		Carte carte = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				restaurant = new Restaurant();
				carte = new Carte();
				restaurant.setId(rs.getInt("id"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setOuverture(rs.getTime("ouverture").toLocalTime());
				restaurant.setFermeture(rs.getTime("fermeture").toLocalTime());
				
				carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				restaurant.setCarte(carte);

			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations du restaurant d'id " + id, e);
		}
		return restaurant;
	}

	public void insert(Restaurant restaurant) throws DALException {
		try {
			// L'ajout de RETURN_GENERATED_KEYS permet de récupérer l'id généré par la base
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setTime(3, Time.valueOf(restaurant.getOuverture()));
			ps.setTime(4, Time.valueOf(restaurant.getFermeture()));
			ps.executeUpdate();

			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				restaurant.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}
	}

	public void update(Restaurant restaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setTime(3, Time.valueOf(restaurant.getOuverture()));
			ps.setTime(4, Time.valueOf(restaurant.getFermeture()));
			ps.setInt(5,  restaurant.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(
					"Impossible de mettre a jour les informations pour le restaurant d'id " + restaurant.getId(), e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du restaurant d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer le restauarant d'id " + id, e);
		}
	}
}
