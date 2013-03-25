package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

/**
 * This fragment handles the displaying the Discover view.
 * 
 * @author Shi Ho Wang
 *
 */
public class DiscoverFragment extends SherlockListFragment {


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
		String[] values = new String[] { "Italy", "Washington", "Australia",
		        "Canada", "New York", "Ohio", "France", "Michigan",
		        "Paris", "Russia" };
		    BucketListAdapter adapter = new BucketListAdapter(getActivity(),
		        R.layout.bucketlist_item, values);
		    setListAdapter(adapter);
	}
	
	/**
	 * Runs when a list item is clicked.
	 */
	@Override 
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Open details fragment
		showDetails();
    }
	
	public void showDetails() {
		SherlockListFragment frag = new DetailsFragment();
		FragmentTransaction trans = getFragmentManager().beginTransaction();
		
		trans.replace(R.id.fragment_container, frag);
		trans.addToBackStack(null);
		trans.commit();
	}
}
