package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class ProfileFragment extends SherlockFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		
		
		return inflater.inflate(R.layout.profile_view, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
}
