package edu.osu.database;


/**
 * This is where all methods that update information in the database are
 * handled.
 * 
 * @author Everly Okorji
 * 
 */
public interface IUpdate {

	/**
	 * This method creates a new user, given the profile information.
	 * @param user An array of length 13, containing string values for the new user's info
	 * @return 0 if add was successful, 1 for SQL Exception, 2 for FileNotFound Exception
	 */
	int addUser(String[] user);
	
	// TODO Declare methods HERE

}
