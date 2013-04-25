package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * This fragment displays privacy information.
 * 
 * @author Shi Ho Wang
 *
 */
public class PrivacyFragment extends SherlockFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		
		
		return inflater.inflate(R.layout.privacy_layout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
}
