package edu.osu.bucketlistmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * This fragment handles the displaying the Profile view.
 * 
 * @author Shi Ho Wang
 *
 */
public class ProfileFragment extends SherlockFragment{
	
	
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
		
		// Set user's profile information
		setProfilePic(view);
		setUsername(view);
		setDescription(view);
		setEmail(view);
		setRealName(view);
		setDateOfBirth(view);
		setPhoneNumber(view);
		setCity(view);
		setState(view);
		setCountry(view);
		setZip(view);
		
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	private void setProfilePic(View view) {
		ImageView img = (ImageView) view.findViewById(R.id.profileImage);
		img.setImageResource(R.drawable.ic_launcher);
		img.setContentDescription("User profile picture");
	}
	
	private void setUsername(View view) {
		TextView text = (TextView) view.findViewById(R.id.username);
		text.setText("superman2000");
	}
	
	private void setDescription(View view) {
		TextView text = (TextView) view.findViewById(R.id.descriptionText);
		text.setText("adjsf;kajskdlfjak;sljfdkl;ajsklfjaskljfdklasdjflkjdslkjkladsjflksdajfkljsdlkfjasdlkfjlkasjflkjasdlkfjaslk;jflhbiowh;gioehwuohowuhgowiehgohii");
	}
	
	private void setEmail(View view) {
		TextView text = (TextView) view.findViewById(R.id.emailText);
		text.setText("superman@osu.edu");
	}
	
	private void setRealName(View view) {
		TextView text = (TextView) view.findViewById(R.id.realNameText);
		text.setText("Clark Kent");
	}
	
	private void setDateOfBirth(View view) {
		TextView text = (TextView) view.findViewById(R.id.dateOfBirthText);
		text.setText("01/01/1960");
	}
	
	private void setPhoneNumber(View view) {
		TextView text = (TextView) view.findViewById(R.id.phoneNumberText);
		text.setText("(xxx) xxx-xxxx");
	}
	
	private void setCity(View view) {
		TextView text = (TextView) view.findViewById(R.id.cityText);
		text.setText("Columbus");
	}
	
	private void setState(View view) {
		TextView text = (TextView) view.findViewById(R.id.stateText);
		text.setText("Ohio");
	}
	
	private void setCountry(View view) {
		TextView text = (TextView) view.findViewById(R.id.countryText);
		text.setText("United States");
	}
	
	private void setZip(View view) {
		TextView text = (TextView) view.findViewById(R.id.zipText);
		text.setText("43210");
	}
}
