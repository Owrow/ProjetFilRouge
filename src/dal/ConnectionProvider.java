package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection(){
		// modifier l'utilisateur, le mot de passe et le nom de la database pour se connecter
		String dbName = System.getenv("DATABASE_NAME"); 
		String url = "jdbc:sqlserver://localhost;databasename=" + dbName + ";trustservercertificate=true";
		
			try {
				return DriverManager.getConnection(url, System.getenv("USER_SQLSERVER"), System.getenv("PASSWORD_SQLSERVER"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		

}
}