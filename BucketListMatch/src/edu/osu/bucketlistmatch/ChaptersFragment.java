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
 * This fragment displays a list of chapters for a selected bucket list item.
 * 
 * @author Shi Ho Wang
 * 
 */
public class ChaptersFragment extends SherlockListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Gets all chapters for the selected bucket list item.
		JSONArray chapterItems = DB.getChapters(LoginActivity.user,
				LoginActivity.pass, LoginActivity.selectedItem);

		// Display the chapters.
		if (chapterItems != null) {
			if (chapterItems.length() > 0) {
				ChapterAdapter adapter = new ChapterAdapter(getActivity(),
						chapterItems);
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
