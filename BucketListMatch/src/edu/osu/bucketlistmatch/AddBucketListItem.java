package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;

import edu.osu.database.DB;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class AddBucketListItem extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bucket_list_item);

		// Get spinner objects.
		Spinner countrySpinner = (Spinner) findViewById(R.id.country);
		Spinner monthSpinner = (Spinner) findViewById(R.id.startMonth);
		Spinner daySpinner = (Spinner) findViewById(R.id.startDay);
		Spinner yearSpinner = (Spinner) findViewById(R.id.startYear);

		// Choose spinner values and layouts.
		SpinnerAdapter countryAdapter = ArrayAdapter.createFromResource(this,
				R.array.country_list, android.R.layout.simple_spinner_item);
		SpinnerAdapter monthAdapter = ArrayAdapter.createFromResource(this,
				R.array.months, android.R.layout.simple_spinner_item);
		SpinnerAdapter dayAdapter = ArrayAdapter.createFromResource(this,
				R.array.days, android.R.layout.simple_spinner_item);
		SpinnerAdapter yearAdapter = ArrayAdapter.createFromResource(this,
				R.array.start_years, android.R.layout.simple_spinner_item);

		// Set spinner values.
		countrySpinner.setAdapter(countryAdapter);
		monthSpinner.setAdapter(monthAdapter);
		daySpinner.setAdapter(dayAdapter);
		yearSpinner.setAdapter(yearAdapter);

	}

	public void onClick(View view) {
		switch (view.getId()) {
		// Create bucket list and send to database.
		case R.id.addBucketListItemDone:
			// Get name, image, state, country, and start date.
			String[] book = new String[5];

			EditText name = (EditText) findViewById(R.id.name);
			EditText state = (EditText) findViewById(R.id.state);

			book[0] = name.getText().toString();
			book[1] = "";
			book[2] = state.getText().toString();

			Spinner country = (Spinner) findViewById(R.id.country);
			Spinner month = (Spinner) findViewById(R.id.startMonth);
			Spinner day = (Spinner) findViewById(R.id.startDay);
			Spinner year = (Spinner) findViewById(R.id.startYear);

			book[3] = country.getSelectedItem().toString();
			book[4] = month.getSelectedItem().toString() + "/"
					+ day.getSelectedItem().toString() + "/"
					+ year.getSelectedItem().toString();

			// Get privacy.
			boolean privacy;
			RadioButton button = (RadioButton) findViewById(R.id.publicRadio);
			if (button.isChecked()) {
				privacy = true;
			} else {
				privacy = false;
			}

			// Add bucket list item to bucket list.
			DB.addBucketListBook(LoginActivity.user, LoginActivity.pass, book,
					privacy);
			
			finish();
			break;
		}
	}
}
