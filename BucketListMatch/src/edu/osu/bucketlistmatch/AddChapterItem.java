package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class AddChapterItem extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_chapter_item);
	}
	
	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.addChapterItemDone:
			// Create bucket list and send to database.
			
			break;
		}
	}
}
