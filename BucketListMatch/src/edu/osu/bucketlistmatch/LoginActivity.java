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
	/**
	 * This activity allows the user to request for an e-mail for a temporary
	 * password to log back in.
	 * 
	 * @author Chris Hartman
	 * 
	 */

	EditText username, password;
	Button signin;
	static String user, pass, selectedItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		signin = (Button) findViewById(R.id.signInButton);

		signin.setOnClickListener(signinListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getSupportMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void openHome(View view) {
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
	}

	/**
	 * Opens the sign up page.
	 * 
	 * @param v
	 */
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.forgetPasswordButton:
			startActivity(new Intent(this, ForgetPasswordActivity.class));
			break;
		case R.id.signUpButton:
			startActivity(new Intent(this, SignUpActivity.class));
			break;
		}
	}

	private OnClickListener signinListener = new OnClickListener() {

		public void onClick(View v) {
			user = username.getText().toString();
			pass = password.getText().toString();

			// Authenticate the login information.
			int status = DB.validateUser(user, pass);

			if (status == 0)
				openHome(v);
			else
				Toast.makeText(getApplicationContext(),
						"Authentication Failed. Try again.", Toast.LENGTH_SHORT)
						.show();
		}
	};
	
	static void setSelectedItem(String item) {
		selectedItem = item;
	}
}
