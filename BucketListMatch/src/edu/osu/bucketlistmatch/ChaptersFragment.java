package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;

public class ChaptersFragment extends SherlockListFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.item_list_layout, container, false);
		
		populateListItems();
		
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	private void populateListItems() {
		String[] values = new String[] { "Ate Cheeseburger", "Visited Statue", "Hotel Room"};
		    BucketListAdapter adapter = new BucketListAdapter(getSherlockActivity(),
		        R.layout.bucketlist_item, values);
		    setListAdapter(adapter);
	}
}
