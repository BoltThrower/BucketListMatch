package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import edu.osu.database.DB;

import android.R.string;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class LoginActivity extends SherlockActivity {
	
	EditText username, password;
	Button signin;
	String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		username = (EditText)findViewById(R.id.username); 
		password = (EditText)findViewById(R.id.password);
		signin = (Button)findViewById(R.id.signInButton);
		
		signin.setOnClickListener(signinListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getSupportMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void openHome(View view) {
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
	}
	
	
	private OnClickListener signinListener = new OnClickListener(){
		
		public void onClick (View v)
		{
			// Authenticate the login information.
			//System.out.println(DB.validateUser(user, pass));
			//int result = query.validateUser(user, pass);
			//System.out.println(result);
			user = username.getText().toString(); 
			pass = password.getText().toString();
			
			String result;
			int x = DB.validateUser(user,  pass);
			if (x < 0) {
				result = DB.err;
			} else {
				result = "" + x;
			}
			
			// Notification for a bad username or password
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		}
	};
	
	private void authenticateLogin(View view) {
		
		//int result = query.validateUser(user, pass);
		//System.out.println(result);
		//System.out.println(user);
		//System.out.println(pass);
		//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		
		//Intent i = new Intent(this, HomeActivity.class);
		//startActivity(i);
		
		
	}
	


}
