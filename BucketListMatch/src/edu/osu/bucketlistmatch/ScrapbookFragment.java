package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

/**
 * This fragment handles the displaying the Scrapbook view.
 * 
 * @author Shi Ho Wang
 *
 */
public class ScrapbookFragment extends SherlockListFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_layout, container, false);
		
		populateListItems();
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	private void populateListItems() {
		
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
		FragmentTransaction trans = getFragmentManager().beginTransaction();
		
		trans.replace(R.id.fragment_container, new ChaptersFragment());
		trans.addToBackStack(null);
		trans.commit();
	}
}
