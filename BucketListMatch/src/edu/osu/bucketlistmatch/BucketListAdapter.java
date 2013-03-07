package edu.osu.bucketlistmatch;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * This adapter helps map bucket list items to a list.
 * 
 * @author Shi Ho Wang
 *
 */
public class BucketListAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context c;
	private int resourceId;
	String[] values;
	
	public BucketListAdapter(Context context, int resource, String[] objects) {
		c = context;
		resourceId = resource;
		values = objects;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return values.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if (convertView == null) {
			v = inflater.inflate(R.layout.bucketlist_item, null);
		}
		
		// Create the row item objects.
		TextView location = (TextView) v.findViewById(R.id.location);
		
		// Set the row item objects.
		location.setText(values[position]);
		
		return v;
	}

}
