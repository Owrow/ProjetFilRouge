

package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Table;

public class TableDAOJdbcImpl implements GenericDAO<Table>{
private static final String TABLE_NAME = " tables ";
	
	private static final String DELETE = "DELETE FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET numero = ?, nombre_places = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (id_restaurant,numero, nombre_places) VALUES (1,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;
	
	private Connection cnx;
	
	public TableDAOJdbcImpl() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}
	
	public List<Table> selectAll() throws DALException {
		List<Table> tables = new ArrayList<>(); 
	
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Table table = new Table();
				table.setId(rs.getInt("id"));
				table.setNumero(rs.getInt("numero"));
				table.setNombre_places(rs.getInt("nombre_place"));
			
				tables.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return tables;
	}
	
	public Table selectById(int id) throws DALException {
		Table table = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				table = new Table();
				table.setId(rs.getInt("id"));
				table.setNumero(rs.getInt("numero"));
				table.setNombre_places(rs.getInt("nombre_place"));

}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return table;
	}
	
	public void insert(Table table) throws DALException {
		try {

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, table.getNumero());
			ps.setInt(2, table.getNombre_places());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { 
				int id = rs.getInt(1); 
				table.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Table table) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setInt(1, table.getNumero());
			ps.setInt(2, table.getNombre_places());
			ps.setInt(3, table.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
}
