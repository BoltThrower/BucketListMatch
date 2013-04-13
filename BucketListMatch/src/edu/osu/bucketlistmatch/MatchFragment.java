package edu.osu.bucketlistmatch;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.osu.database.DB;

/**
 * This fragment handles the displaying the Match view.
 * 
 * @author Shi Ho Wang
 * 
 */
public class MatchFragment extends SherlockListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Gets all match results for the user and display.
		JSONArray bucketListItems = DB.match(LoginActivity.user,
				LoginActivity.pass);

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
}
