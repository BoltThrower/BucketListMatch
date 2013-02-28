import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.osu.database.DB;

public class BucketListMatch {

	/**
	 * The class that runs when the BLM app is opened. It should be the only
	 * main class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Declare all necessary variables
		DB db;

		// Establish database connection to application
		db = new DB(DatabaseSetup());

		// TODO Put other code HERE

		// Close the connection to database to release resources
		db.close();

	}

	/**
	 * This method creates a link between the application / UI and the database.
	 * 
	 * @return A connection object which is linked to the BLM database.
	 */
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
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return null; // TODO If code gets to this point (i.e. database is not
						// connected),
						// show error message?

	}

}
