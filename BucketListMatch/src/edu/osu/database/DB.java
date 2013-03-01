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
	
	
	
	// QUERY METHODS

	public static int validateUser(String username, String password) {
		if (con == null) return -1;
		return query.validateUser(username, password);
	}
	
	public static String[] fetchProfileDetails(String username, String password) {
		if (con == null) return null;
		if (validateUser(username, password) == 0) {
			return query.fetchUserDetails(username, password);
		}
		return null;
	}
	
	
	
	
	
	
	// UPDATE METHODS
	
	/**
	 * This method creates a new user, given the profile information.
	 * @param user An array of length 13, containing string values for the new user's info
	 * @return 0 if add was successful, 1 for SQL Exception, 2 for FileNotFound Exception,
	 * -1 if a connection to database does not exist.
	 */
	public static int addUser(String user[]) {
		if (con == null) return -1;
		if (user.length != USER_ATTRIBUTE_LENGTH) return 3;
		return update.addUser(user);
	}
	
	/**
	 * This method replaces the existing user profile photo with the specified new one.
	 * @param username
	 * @param binary A string that represents the stream for the image info.
	 */
	static void changeUserProfilePhoto (String username, String binary) {
		// TODO Validate user?
		update.changeUserProfilePhoto (username, binary);
	}
	
	/**
	 * This method updates the user's location information with the new information.
	 * @param username
	 * @param city
	 * @param state
	 * @param country
	 */
	static void updateUserLocation (String username, String city, String state, String country) {
		// TODO validate user?
		update.updateUserLocation(username, city, state, country);
	}
	
	/**
	 * This method updates the description of a user
	 * @param username
	 * @param description
	 */
	static void updateUserDescription (String username, String description) {
		// TODO Validate user?
		update.updateUserDescription(username, description);
	}
	
	/**
	 * This method adds a new Bucket List Book to a user's account.
	 * @param username
	 * @param textInfo A string array of length 6, holding information about title, cover image,
	 * state, country, start date and cost.
	 * @param numInfo An integer array of length 3, holding information about the duration (days),
	 * users completed and users not completed.
	 * @param boolInfo True if privacy settings are activated, false otherwise.
	 */
	static void addBucketListBook (String username, String[] textInfo, int[] numInfo, boolean privacy) {
		// ValidateUser?
		update.addBucketListBook(username, textInfo, numInfo, privacy);
	}
	
	
	
	
	// HELPER METHODS
	
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
	
	// TODO Implement, RETURN VARIABLE!
	static void convertToImage(String binary) {
		
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
