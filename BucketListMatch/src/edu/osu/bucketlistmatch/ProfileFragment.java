package edu.osu.bucketlistmatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
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

	private String username = "Adhesiveeye";
	private String password = "sticky";

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
		JSONObject profileJSON = DB.fetchProfileDetails(username, password);

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

	private void setProfilePic(View view, JSONObject profileJSON) {
		ImageView img = (ImageView) view.findViewById(R.id.profileImage);

		img.setImageResource(R.drawable.ic_launcher);
		img.setContentDescription("User profile picture");
	}

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

	private void setDescription(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.descriptionText);
		try {
			String description = profileJSON.getString("Description");
			text.setText(description);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			String dob = profileJSON.getString("DateOfBirth");
			text.setText(dob);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setPhone(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.phoneText);
		try {
			String phone = profileJSON.getString("Phone");
			text.setText(phone);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setCity(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.cityText);
		try {
			String city = profileJSON.getString("City");
			text.setText(city);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setState(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.stateText);
		try {
			String state = profileJSON.getString("State");
			text.setText(state);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	private void setZip(View view, JSONObject profileJSON) {
		TextView text = (TextView) view.findViewById(R.id.zipText);
		try {
			String zip = profileJSON.getString("ZipCode");
			text.setText(zip);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
