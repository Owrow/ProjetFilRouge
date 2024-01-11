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

	private static final String DELETE = "DELETE FROM"+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET nom = ?, WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (nom) VALUES (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;

	private Connection cnx;

	public CategorieDAOjdbcImpl(){
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public List<Categorie> selectAll() {
		List<Categorie> listeCategorie = new ArrayList<>(); 



		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();

				categorie.setNom(rs.getString("nom"));


				listeCategorie.add(categorie);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return listeCategorie;



	}

	@Override
	public Categorie selectById(int id) {
		Categorie categorie = null;

		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categorie = new Categorie();

				categorie.setNom(rs.getString("nom"));

				rs = ps.getGeneratedKeys();
				if (rs.next()) { 
					id = rs.getInt(1); 
					categorie.setId(id);
				}}

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return categorie;

	}

	@Override
	public void insert(Categorie categorie) {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, categorie.getNom());

			ps.executeUpdate();


			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { 
				int id = rs.getInt(1); 
				categorie.setId(rs.getInt(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}





	@Override
	public void update(Categorie categorie) {


		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(UPDATE);
			ps.setInt(1, categorie.getId());
			ps.setString(2, categorie.getNom());

			ps.executeUpdate();



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





	@Override
	public void delete(int id) {


		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}







	}


}
