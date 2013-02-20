package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class HomeActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		// Create an instance of HomeFragment
        HomeFragment homeFrag = new HomeFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        homeFrag.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFrag).commit();
		
        
		// Provides data for the spinner widget.
		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_dropdown_item_1line);
		
		// Allows drop-down menu in actionbar.
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Navigation listener.
		OnNavigationListener navLis = new OnNavigationListener() {
			// Get the same strings provided for the drop-down's ArrayAdapter
			String[] strings = getResources().getStringArray(
					R.array.action_list);

			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {
				// Some how create the appropriate fragment to replace with.
				// Create new fragment from our own Fragment class
				HomeFragment frag = new HomeFragment();

				// Replace whatever is in the fragment container with this
				// fragment
				// and give the fragment a tag name equal to the string at the
				// position selected
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, frag,
								strings[position]).commit();
				return true;
			}
		};

		// Set items and navigation listener to the actionbar.
		getSupportActionBar().setListNavigationCallbacks(spinnerAdapter, navLis);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
}
