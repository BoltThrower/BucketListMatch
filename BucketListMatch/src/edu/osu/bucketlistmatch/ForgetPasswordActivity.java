package edu.osu.bucketlistmatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.osu.database.DB;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * This activity allows the user to enter his/her Username and email in
 * order to retrieve a forgotten password that is sent by email.
 * 
 * @author Chris Hartman
 * 
 */
public class ForgetPasswordActivity extends Activity {

	EditText username, emailAddress;
	String user, email;
	Button submit;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		
		username = (EditText) findViewById(R.id.forgetPasswordUsername);
		emailAddress = (EditText) findViewById(R.id.forgetPasswordEmail);
		submit = (Button) findViewById(R.id.forgetPasswordSendButton);

		submit.setOnClickListener(submitForgetPasswordListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forget_password, menu);
		return true;
	}
	
	private OnClickListener submitForgetPasswordListener = new OnClickListener() {

		public void onClick(View v) {
			// Make sure that the email is in a proper format,
			// as well as make sure that the email and username both exist in the database.
			user = username.getText().toString().trim();
			email = emailAddress.getText().toString().trim();
			
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			
			if(user.equals("") || email.equals("")){
				// Not enough was submitted.
				Toast.makeText(getApplicationContext(), "Please enter all of the appropriate information.", Toast.LENGTH_SHORT).show();
			}
			if(matcher.matches()){
				// Validate the username and email against the database.
				// The hosting server must be configured to use SMTP services for sending email.
				
				if(DB.forgotPassword(user, email) != 0){
					Toast.makeText(getApplicationContext(), "Could not send email with forgotten password.", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(getApplicationContext(), "Email has been sent.", Toast.LENGTH_SHORT).show();
				}
			}
			else{
				// Invalid email address.
				Toast.makeText(getApplicationContext(), "Invalid email address was entered.", Toast.LENGTH_SHORT).show();
			}
		}
	};
}
