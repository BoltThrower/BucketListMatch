package edu.osu.bucketlistmatch;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import edu.osu.database.DB;

/**
 * This fragment handles the displaying the Profile view.
 * 
 * @author Shi Ho Wang
 * 
 */
public class ProfileFragment extends SherlockFragment {

	private String user = LoginActivity.user;
	private String pass = LoginActivity.pass;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.profile_layout, container, false);

		// Fetch user's profile
		JSONObject profileJSON = DB.fetchProfileDetails(this.user, this.pass);

		// Set user's profile
		setProfilePic(view, profileJSON);
		setUsername(view, profileJSON);
		setDescription(view, profileJSON);
		setEmail(view, profileJSON);
		setName(view, profileJSON);
		setDateOfBirth(view, profileJSON);
		setPhone(view, profileJSON);
		setCity(view, profileJSON);
		setState(view, profileJSON);
		setCountry(view, profileJSON);
		setZip(view, profileJSON);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	/**
	 * Sets the profile picture in the profile view.
	 * 
	 * @param view
	 *            Profile view where the profile picture is set.
	 * @param profileJSON
	 *            JSON object that contains the profile picture.
	 */
	private void setProfilePic(View view, JSONObject profileJSON) {
		ImageView img = (ImageView) view.findViewById(R.id.profileImage);
		img.setImageResource(R.drawable.ic_action_user);
		img.setContentDescription("User profile picture");
	}

	/**
	 * Sets the username in the profile view.
	 * 
	 * @param view
	 *            Profile view where the description is set.
	 * @param profileJSON
	 *            JSON object that contains the username.
	 */
	private void setUsername(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.username);
		try {
			String username = profileJSON.getString("Username");
			text.setText(username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the description in the profile view.
	 * 
	 * @param view
	 *            Profile view where the description is set.
	 * @param profileJSON
	 *            JSON object that contains the description.
	 */
	private void setDescription(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.descriptionText);
		if (profileJSON.isNull("Description")) text.setText("");
		else {
			String description = profileJSON.optString("Description");
			text.setText(description);
		}
	}

	/**
	 * Sets the email in the profile view.
	 * 
	 * @param view
	 *            Profile view where the email is set to.
	 * @param profileJSON
	 *            JSON object that contains the email.
	 */
	private void setEmail(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.emailText);
		try {
			String email = profileJSON.getString("Email");
			text.setText(email);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets the name in the profile view.
	 * 
	 * @param view
	 *            Profile view where the name is set to.
	 * @param profileJSON
	 *            JSON object that contains the name.
	 */
	private void setName(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.nameText);
		try {
			String firstName = profileJSON.getString("FirstName");
			String lastName = profileJSON.getString("LastName");
			text.setText(firstName + " " + lastName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the date of birth in the profile view.
	 * 
	 * @param view
	 *            Profile view where the date of birth is set to.
	 * @param profileJSON
	 *            JSON object that contains the date of birth.
	 */
	private void setDateOfBirth(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.dateOfBirthText);
		try {
			String dob = profileJSON.getJSONObject("DateOfBirth")
					.getString("date").substring(0, 10);
			text.setText(dob);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the phone number in the profile view.
	 * 
	 * @param view
	 *            Profile view where the phone number is set to.
	 * @param profileJSON
	 *            JSON object that contains the phone number.
	 */
	private void setPhone(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.phoneText);
		if (profileJSON.isNull("Phone")) text.setText("");
		else {
			String phone = profileJSON.optString("Phone");
			text.setText(phone);
		}
	}

	/**
	 * Sets the city in the profile view.
	 * 
	 * @param view
	 *            Profile view where the city is set to.
	 * @param profileJSON
	 *            JSON objects that contains the city.
	 */
	private void setCity(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.cityText);
		if (profileJSON.isNull("City")) text.setText("");
		else {
			String city = profileJSON.optString("City");
			text.setText(city);
		}
	}

	/**
	 * Sets the state in the profile view.
	 * 
	 * @param view
	 *            Profile view where the state is set to.
	 * @param profileJSON
	 *            JSON object that contains the state.
	 */
	private void setState(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.stateText);
		if (profileJSON.isNull("State")) text.setText("");
		else {
			String state = profileJSON.optString("State");
			text.setText(state);
		}
	}

	/**
	 * Sets the country in the profile view.
	 * 
	 * @param view
	 *            Profile view where the country is set to.
	 * @param profileJSON
	 *            JSON object that contains the country.
	 */
	private void setCountry(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.countryText);
		try {
			String country = profileJSON.getString("Country");
			text.setText(country);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the zip code in the profile view.
	 * 
	 * @param view
	 *            Profile view where the zip code is set to.
	 * @param profileJSON
	 *            JSON object that contains the zip code.
	 */
	private void setZip(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.zipText);
		if (profileJSON.isNull("ZipCode")) text.setText("");
		else {
			String zip = profileJSON.optString("ZipCode");
			text.setText(zip);
		}
	}
}
