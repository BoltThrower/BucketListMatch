package edu.osu.bucketlistmatch;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.osu.database.DB;

/**
 * This fragment displays the user's list of bucket list items.
 * 
 * @author Shi Ho Wang
 * 
 */
public class BucketListFragment extends SherlockListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Gets all bucket list items for the user.
		JSONArray bucketListItems = DB.getAllBucketListBooks(
				LoginActivity.user, LoginActivity.pass);

		// Display the bucket list items.
		if (bucketListItems != null) {
			if (bucketListItems.length() > 0) {
				BucketListAdapter adapter = new BucketListAdapter(
						getActivity(), bucketListItems);
				setListAdapter(adapter);
			} else {
				Log.e("JSONArray", "JSONArray is empty.");
			}
		} else {
			Log.e("JSONArray", "JSONArray is null.");
		}

		return inflater.inflate(R.layout.list_layout, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	/**
	 * Runs when a bucket list item is selected.
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Save selected item name.
		LoginActivity
				.setSelectedItem((String) l.getAdapter().getItem(position));

		// Open chapter fragment for the selected bucket list item.
		FragmentTransaction trans = getFragmentManager().beginTransaction();

		trans.replace(R.id.fragment_container, new ChaptersFragment());
		trans.addToBackStack(null);
		trans.commit();
	}
}
