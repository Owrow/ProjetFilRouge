package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Plat;

public class PlatDAOjdbcImpl implements GenericDao<Plat> {

	private static final String TABLE_NAME = " plats ";

	private static final String DELETE = "DELETE FROM"+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET nom = ?, description_plats = ?, prix = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (nom, description_plats, prix) VALUES (?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;

	private Connection cnx;

	public PlatDAOjdbcImpl() {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Plat> selectAll(){
		List<Plat> listePlat = new ArrayList<>();


		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plat plat = new Plat();
				plat.setNom(rs.getString("nom"));
				plat.setDescription(rs.getString("description_plat"));
				plat.setPrix(rs.getFloat("prix"));

				listePlat.add(plat);

			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return listePlat;
	}

	public Plat selectById(int id)  {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		return plat;
	}

	public void insert(Plat plat) {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, plat.getNom());
			ps.setString(2, plat.getDescription());
			ps.setFloat(3, plat.getPrix());
			ps.executeUpdate();


			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { 
				int id = rs.getInt(1); 
				plat.setId(rs.getInt(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}


	public void update(Plat plat) {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, plat.getNom());
			ps.setString(2, plat.getDescription());
			ps.setFloat (3, plat.getPrix() );
			ps.executeUpdate();


				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) { 
					int id = rs.getInt(1); 
					plat.setId(id);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	public void delete(int id){

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


