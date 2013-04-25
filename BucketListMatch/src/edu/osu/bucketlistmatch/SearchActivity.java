package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;

/**
 * This activity allows the user to search bucket list items with keywords.
 * 
 * @author Shi Ho Wang
 * 
 */
public class SearchActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

}
