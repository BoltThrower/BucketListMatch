package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class LoginActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void openHome(View view) {
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
	}

}
