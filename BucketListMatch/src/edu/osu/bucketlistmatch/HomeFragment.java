package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * This fragment displays random images from public bucket list items.
 * 
 * @author Shi Ho Wang
 *
 */
public class HomeFragment extends SherlockFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		
		
		return inflater.inflate(R.layout.home_layout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
}
