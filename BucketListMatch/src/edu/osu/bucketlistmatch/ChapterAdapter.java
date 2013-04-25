package edu.osu.bucketlistmatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This adapter helps map chapter items to a listview.
 * 
 * @author Shi Ho Wang
 * 
 */
public class ChapterAdapter extends BaseAdapter {
	private Context context;
	private JSONArray values;

	public ChapterAdapter(Context context, JSONArray chapterItems) {
		super();
		this.context = context;
		this.values = chapterItems;
	}

	/**
	 * Gets number of chapter items.
	 */
	@Override
	public int getCount() {
		return this.values.length();
	}

	/**
	 * Gets the chapter item at position.
	 */
	@Override
	public Object getItem(int position) {
		String result = "";
		try {
			result = this.values.getJSONObject(position).getString("Name");
		} catch (JSONException e) {
			Log.e("JSONException",
					"Could not retrieve JSONObject from JSONArray.");
		}
		return result;
	}

	/**
	 * Gets the chapter item's id at position.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			rowView = inflater.inflate(R.layout.chapter_item, null);
		}

		// Get json object from json array at given position.
		JSONObject jsonObject = null;

		// Create the row item objects.
		ImageView coverImage = (ImageView) rowView
				.findViewById(R.id.coverImage);

		TextView task = (TextView) rowView.findViewById(R.id.task);
		TextView description = (TextView) rowView
				.findViewById(R.id.description);
		TextView duration = (TextView) rowView.findViewById(R.id.duration);
		TextView creater = (TextView) rowView.findViewById(R.id.creater);
		TextView cost = (TextView) rowView.findViewById(R.id.cost);

		// Set the row item objects.
		try {
			jsonObject = this.values.getJSONObject(position);
			task.setText(jsonObject.getString("Name"));
			description.setText(jsonObject.getString("Description"));
			duration.setText(jsonObject.getString("Duration"));
			creater.setText(jsonObject.getString("CreatedBy"));
			cost.setText("$" + jsonObject.getString("Cost"));

		} catch (JSONException e) {
			Log.e("JSON object error.",
					"Failed to retrieve json object from bucket list json array.");
		}

		return rowView;
	}

}
