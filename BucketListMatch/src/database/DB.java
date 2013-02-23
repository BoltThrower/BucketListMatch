package database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is the handler for database communications to the application.
 * All queries and updates to the database are organized by this class.
 * @author Everly Okorji
 *
 */
public class DB {

	protected static Connection con;
	private Query query;
	private Update update;
	
	public final static int USER_ATTRIBUTE_LENGTH = 13;
	
	/**
	 * This is the constructor for the DB class.
	 * @param connection
	 */
	public DB (Connection connection) {
		con = connection;
		query = new Query1();
		update = new Update1();
	}

	public int validateUser(String username, String password) {
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
	
}
