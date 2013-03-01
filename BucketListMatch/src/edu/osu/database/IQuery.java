package edu.osu.database;


/**
 * This is where all methods that fetch information from the database are handled.
 * 
 * @author Everly Okorji
 *
 */
interface IQuery {

	/**
	 * This method checks to see if a user exists in the database and returns an integer value
	 * depending on the resulting status.
	 * @param username
	 * @param password
	 * @return 0 if the username and password combination exists, 1 if password does not match,
	 * 2 if username does not exist. If an error occurs, this method returns a negative number.
	 */
	int validateUser (String username, String password);
	
	/**
	 * This method checks to see if a user is valid, then fetches all user profile information
	 * from the database.
	 * @param username
	 * @param password
	 * @return An array containing all user information.
	 */
	String[] fetchUserDetails (String username, String password);
}
