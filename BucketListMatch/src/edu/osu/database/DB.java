package edu.osu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is the handler for database communications to the application.
 * All queries and updates to the database are organized by this class.
 * @author Everly Okorji
 *
 */
public class DB {

	static Connection con;
	private static IQuery query;
	private static IUpdate update;
	public static String err;
	
	public final static int USER_ATTRIBUTE_LENGTH = 13;
	
	/**
	 * This is the constructor for the DB class.
	 * @param connection
	 */
	public DB () {
		con = DatabaseSetup();
		query = new Query();
		update = new Update();
	}

	public static int validateUser(String username, String password) {
		if (con == null) return -1;
		return query.validateUser(username, password);
	}
	
	public static int addUser(String user[]) {
		if (con == null) return -1;
		if (user.length != USER_ATTRIBUTE_LENGTH) return 3;
		return update.addUser(user);
		
	}
	
	public static String[] fetchProfileDetails(String username, String password) {
		if (con == null) return null;
		if (validateUser(username, password) == 0) {
			return query.fetchUserDetails(username, password);
		}
		return null;
	}
	
	
	// TODO Define new procedures and functions here
	
	/**
	 * Closes the connection to the database, if there exists one.
	 */
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Connection DatabaseSetup() {
		try {

			// Fetch the class to be used in the connection
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Define the Java DB connectivity URL
			String connectionUrl = "jdbc:sqlserver://server14.ies.cse.ohio-state.edu:1433;"
					+ "databaseName={BLM_Database};userName=ApplAccount;password=@pplBLM!cc0uNt59i1;";

			// Set up a connection using Java's driver manager
			return DriverManager.getConnection(connectionUrl);

		} catch (ClassNotFoundException e) {
			// TODO Handle exception
			err = e.getMessage() + "\n" + e.getCause();
			return null;
		} catch (SQLException e) {
			// TODO Handle exception
			err = e.getMessage() + "\n" + e.getCause();
			return null;
		}

	}
	
}
