package database;

/**
 * 
 * @author Everly Okorji
 *
 */
interface Query {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean isValidUser(String username, String password);

}
