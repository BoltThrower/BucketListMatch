package edu.osu.bucketlistmatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.osu.database.DB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * This activity handles new user account creation.
 * 
 * @author Shi Ho Wang, Chris Hartman
 *
 */
public class SignUpActivity extends Activity {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		Spinner spinner = (Spinner) findViewById(R.id.countrySignUp);
		Spinner dobMonthsSpinner = (Spinner) findViewById(R.id.dobMonth);
		Spinner dobDaysSpinner = (Spinner) findViewById(R.id.dobDay);
		Spinner dobYearsSpinner = (Spinner) findViewById(R.id.dobYear);
		
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.country_list, android.R.layout.simple_spinner_item);
		
		ArrayAdapter<CharSequence> dobMonthsAdapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> dobDaysAdapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> dobYearsAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		dobMonthsSpinner.setAdapter(dobMonthsAdapter);
		dobDaysSpinner.setAdapter(dobDaysAdapter);
		dobYearsSpinner.setAdapter(dobYearsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	public void validateSignUp(View v){
		// Enter code to make sure that the username is valid, does not already exist, etc.
		
		EditText username, password1, password2, firstName, lastName, emailAddress;
		Spinner countrySpinner, dobMonth, dobDay, dobYear;
		
		String user, pass1, pass2, first, last, dob, country, email;
		
		username = (EditText)findViewById(R.id.usernameRegisterTextField); 
		password1 = (EditText)findViewById(R.id.password1TextField);
		password2 = (EditText)findViewById(R.id.password2TextField);
		firstName = (EditText)findViewById(R.id.firstNameText);
		lastName = (EditText)findViewById(R.id.lastNameText);
		emailAddress = (EditText)findViewById(R.id.emailTextField);
		
		countrySpinner = (Spinner)findViewById(R.id.countrySignUp);
		dobMonth = (Spinner)findViewById(R.id.dobMonth);
		dobDay = (Spinner)findViewById(R.id.dobDay);
		dobYear = (Spinner)findViewById(R.id.dobYear);
		
		dob = dobMonth.getSelectedItem().toString() + "/" + dobDay.getSelectedItem().toString() + "/" + dobYear.getSelectedItem().toString();
		
		user = username.getText().toString().trim(); 
		pass1 = password1.getText().toString().trim(); 
		pass2 = password2.getText().toString().trim(); 
		first = firstName.getText().toString().trim(); 
		last = lastName.getText().toString().trim(); 
		email = emailAddress.getText().toString().trim();
		
		// Validate correct email format.
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		
		String[] userInfo = {user, pass1, first, last, dob, countrySpinner.getSelectedItem().toString(), email};
		
		// Code that makes sure that both of the passwords entered is correct.
		if(!pass1.equals(pass2))
		{
			Toast.makeText(getApplicationContext(), "The passwords that were entered do not match. Please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else if(user.equals("") || pass1.equals("") || pass2.equals("") || first.equals("") || last.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Please enter all of the appropriate information.", Toast.LENGTH_SHORT).show();
		}
		
		else if(!matcher.matches())
		{
			// Invalid email address.
			Toast.makeText(getApplicationContext(), "Invalid email address was entered.", Toast.LENGTH_SHORT).show();
		}
		
		else if (DB.addUser(userInfo) != 0) 
		{
			Toast.makeText(getApplicationContext(), "Could not sign up. Please try again.", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			// Add new user to the database, log the user in, and send the user to the Home Screen Activity.
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			
			// Save username and password to loginActivity.
			LoginActivity.user = user;
			LoginActivity.pass = pass1;
		}
	}
	
	public void openLoginActivity(View v){
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}

}
