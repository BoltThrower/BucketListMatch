package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import edu.osu.database.DB;

import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends SherlockActivity {

	public DB db = new DB();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Establish database connection to application
		//db = new DB(DatabaseSetup());

		// Close the connection to database to release resources
		//db.close();
		
		// Start splash screen activity
		//Intent intent = new Intent(this, SplashScreenActivity.class);
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
