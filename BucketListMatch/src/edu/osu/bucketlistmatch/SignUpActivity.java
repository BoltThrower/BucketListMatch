package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity handles new user account creation.
 * 
 * @author Shi Ho Wang, Chris Hartman
 *
 */
public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	public void validateSignUp(View v){
		// Enter code to make sure that the username is valid, does not already exist, etc.
		
		// Code that makes sure that both of the passwords entered is correct.
		EditText username, password1, password2;
		String user, pass1, pass2;
		
		username = (EditText)findViewById(R.id.usernameRegisterTextField); 
		password1 = (EditText)findViewById(R.id.password1TextField);
		password2 = (EditText)findViewById(R.id.password2TextField);
		
		user = username.getText().toString(); 
		pass1 = password1.getText().toString();
		pass2 = password2.getText().toString();
		
		if(!pass1.equals(pass2))
		{
			String badPasswords = "The passwords that were entered do not match.  Please try again.";
			Toast.makeText(getApplicationContext(), badPasswords, Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			// Add new user to the database, log the user in, and send the user to the Home Screen Activity.
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}
	
	public void openLoginActivity(View v){
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}

}
