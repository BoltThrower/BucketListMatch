package database;

/**
 * This is where all methods that update information in the database are
 * handled.
 * 
 * @author Everly Okorji
 * 
 */
public interface Update {

	/**
	 * This method creates a new user, given the profile information.
	 * @param user An array of length 13, containing string values for the new user's info
	 * @return true if the action was successful, false otherwise
	 */
	boolean addUser(String[] user);
	
	// TODO Declare methods HERE

}
