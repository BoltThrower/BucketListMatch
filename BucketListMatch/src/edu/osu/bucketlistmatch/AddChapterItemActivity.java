package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

/**
 * Allows the user to create and add a new chapter to an existing bucket list
 * item.
 * 
 * @author Shi Ho Wang
 * 
 */
public class AddChapterItemActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_chapter_item);
	}

	/**
	 * Handles all the button clicks in this activity.
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		// Create bucket list and send to database.
		case R.id.addChapterItemDone:

			// Add chapter to bucket list item and close activity.

			finish();
			break;
		}
	}
}
