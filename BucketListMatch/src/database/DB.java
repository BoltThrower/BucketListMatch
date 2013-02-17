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

	Connection con;
	
	/**
	 * This is the constructor for the DB class.
	 * @param connection
	 */
	public DB (Connection connection) {
		con = connection;
	}

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
	
	// TODO Define new procedures and functions here
	
}
