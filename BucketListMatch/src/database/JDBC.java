package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://server14.ies.cse.ohio-state.edu:1433;databaseName={BLM_Database};userName=ApplAccount;password=@pplBLM!cc0uNt59i1;";
			Connection con = DriverManager.getConnection(connectionUrl);
			
			System.out.println("Completed!");
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
