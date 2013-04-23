package edu.osu.bucketlistmatch;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.osu.database.DB;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This adapter helps map bucket list items to a list.
 * 
 * @author Shi Ho Wang
 * 
 */
public class BucketListAdapter extends BaseAdapter {
	private Context context;
	private JSONArray values;

	public BucketListAdapter(Context context, JSONArray bucketListItems) {
		super();
		this.context = context;
		this.values = bucketListItems;
	}

	public int getDuration(String user, String pass, int position) {
		int result = 0;
		String dreambookName;
		JSONArray duration;
		
		try {
			dreambookName = this.values.getJSONObject(position).getString("Name");
			duration = DB.getDuration(user, pass, dreambookName);
			result = duration.getJSONObject(0).getInt("Duration");
		} catch (JSONException e) {
			Log.e("JSONException", "Could not retrieve JSONObject from JSONArray.");
		}
		
		return result;
	}
	
	public double getCost(String user, String pass, int position) {
		double result = 0;
		String dreambookName;
		JSONArray cost;
		
		try {
			dreambookName = this.values.getJSONObject(position).getString("Name");
			cost = DB.getCost(user, pass, dreambookName);
			
			// Set result to two decimal places.
			result = cost.getJSONObject(0).getDouble("Cost");
		} catch (JSONException e) {
			Log.e("JSONException", "Could not retrieve JSONObject from JSONArray.");
		}
		
		
		return result;
	}
	
	@Override
	public int getCount() {
		return this.values.length();
	}

	@Override
	public Object getItem(int position) {
		String result = "";
		try {
			result = this.values.getJSONObject(position).getString("Name");
		} catch (JSONException e) {
			Log.e("JSONException", "Could not retrieve JSONObject from JSONArray.");
		}
		return result;
	}

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

			rowView = inflater.inflate(R.layout.bucketlist_item, null);
		}

		// Get json object from json array at given position.
		JSONObject jsonObject = null;

		// Create the row item objects.
		ImageView coverImage = (ImageView) rowView
				.findViewById(R.id.coverImage);

		TextView task = (TextView) rowView.findViewById(R.id.task);
		TextView location = (TextView) rowView.findViewById(R.id.location);
		TextView duration = (TextView) rowView.findViewById(R.id.duration);
		TextView creater = (TextView) rowView.findViewById(R.id.creater);
		TextView cost = (TextView) rowView.findViewById(R.id.cost);

		DecimalFormat df = new DecimalFormat("#0.00");
		
		// Set the row item objects.
		try {
			jsonObject = this.values.getJSONObject(position);
			task.setText(jsonObject.getString("Name"));
			location.setText(jsonObject.getString("Country") + " - " + jsonObject.optString("State"));
			duration.setText(getDuration(LoginActivity.user, LoginActivity.pass, position) + "hrs");
			creater.setText(jsonObject.getString("CreatedBy"));
			cost.setText("$" + df.format(getCost(LoginActivity.user, LoginActivity.pass, position)));

		} catch (JSONException e) {
			Log.e("JSON object error.",
					"Failed to retrieve json object from bucket list json array.");
		}

		// Flag icons.
		ImageView totemIcon = (ImageView) rowView.findViewById(R.id.totemIcon);

		ImageView dealIcon = (ImageView) rowView.findViewById(R.id.dealIcon);

		ImageView challengeIcon = (ImageView) rowView
				.findViewById(R.id.challengeIcon);

		return rowView;
	}

}
