package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

/**
 * This fragment handles the displaying the Bucket List view.
 * 
 * @author Shi Ho Wang
 *
 */
public class BucketListFragment extends SherlockListFragment{
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
		String[] values = new String[] { "Japan", "France", "Tokyo", "Paris",
				"Korea" };
		BucketListAdapter adapter = new BucketListAdapter(getActivity(),
				R.layout.bucketlist_item, values);
		    setListAdapter(adapter);
	}
}