package cybersoft.java12.crmapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cybersoft.java12.crmapp.util.DBConst;

public class MySqlConnection {
	private static Connection connection = null;
		
	public static Connection getConnection() {
		try {
			Class.forName(DBConst.DRIVER);
			if(connection == null || connection.isClosed())
				connection = DriverManager.getConnection(DBConst.URL , DBConst.USERNAME, DBConst.PASSWORD);
			
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver could not found.");
		} catch (SQLException ex) {
			System.out.println("Database connection could not establish.");
		}
		return null;
	}
}
