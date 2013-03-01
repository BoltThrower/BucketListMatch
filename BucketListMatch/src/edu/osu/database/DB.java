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

	protected static Connection con;
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
		return query.validateUser(username, password);
	}
	
	public boolean addUser(String user[]) {
		
		if (user.length != USER_ATTRIBUTE_LENGTH) return false;
		update.addUser(user);
		return true;
		
	}
	
	public String[] fetchProfileDetails(String username, String password) {
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
		} catch (SQLException e) {
			// TODO Handle exception
			err = e.getMessage() + "\n" + e.getCause();
		}
		return null; // TODO If code gets to this point (i.e. database is not
						// connected),
						// show error message?

	}
	
}
