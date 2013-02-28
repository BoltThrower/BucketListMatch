package edu.osu.bucketlistmatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import database.DB;

import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends SherlockActivity {

	public DB db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Establish database connection to application
		//db = new DB(DatabaseSetup());

		// Close the connection to database to release resources
		//db.close();
		
		// Start splash screen activity
		Intent intent = new Intent(this, SplashScreenActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
