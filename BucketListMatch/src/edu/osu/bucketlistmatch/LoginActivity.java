package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import edu.osu.database.DB;
import edu.osu.database.Parser;

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
 * This activity allows the user to request for an e-mail for a temporary password to log back in.
 * 
 * @author Chris Hartman
 *
 */
	
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
	
	/**
	 * Opens the sign up page.
	 * @param v
	 */
	public void openSignUp(View v) {
		Intent i = new Intent(this, SignUpActivity.class);
		startActivity(i);
	}
	
	public void forgetPassword(View v){
//		Intent i = new Intent(this, ForgetPasswordActivity.class);
//		startActivity(i);
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
			
//			String result;
//			int x = DB.validateUser(user,  pass);
//			if (x < 0) {
//				result = DB.err;
//			} else {
//				result = "" + x;
//			}
//			
//			String result= "not entered!" ;
//			try {
//				result = "" + DB.add();
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Parser p = new Parser();
			p.makeRequest();
			
			// Notification for a bad username or password
			Toast.makeText(getApplicationContext(), p.result, Toast.LENGTH_SHORT).show();
			openHome(v);
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
