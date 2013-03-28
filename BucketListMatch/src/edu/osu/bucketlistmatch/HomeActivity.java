package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

/**
 * This activity provides navigation between views Home, Discover, Bucket List,
 * Scrapbook, Match, and Profile.
 * 
 * @author Shi Ho Wang
 * 
 */
public class HomeActivity extends SherlockFragmentActivity {

	private ShareActionProvider shareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Add the fragment to the FrameLayout which is used as the fragment
		// container.
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, new HomeFragment()).commit();

		// Provides data for the spinner widget.
		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_dropdown_item_1line);

		// Allows drop-down menu in action bar.
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Navigation listener.
		OnNavigationListener navLis = new OnNavigationListener() {
			// Get the same strings provided for the drop-down's ArrayAdapter
			String[] strings = getResources().getStringArray(
					R.array.action_list);

			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {
				FragmentTransaction trans = getSupportFragmentManager()
						.beginTransaction();
				// Create the appropriate fragment to replace the current
				// fragment with.
				switch (position) {
				case 0:
					trans.replace(R.id.fragment_container, new HomeFragment());
					trans.commit();
					break;
				case 1:
					trans.replace(R.id.fragment_container,
							new DiscoverFragment());
					trans.commit();
					break;
				case 2:
					trans.replace(R.id.fragment_container,
							new BucketListFragment());
					trans.commit();
					break;
				case 3:
					trans.replace(R.id.fragment_container,
							new ScrapbookFragment());
					trans.commit();
					break;
				case 4:
					trans.replace(R.id.fragment_container, new MatchFragment());
					trans.commit();
					break;
				case 5:
					trans.replace(R.id.fragment_container,
							new ProfileFragment());
					trans.commit();
					break;
				default:

					break;
				}
				return true;
			}
		};

		// Set items and navigation listener to the action bar.
		getSupportActionBar()
				.setListNavigationCallbacks(spinnerAdapter, navLis);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.home, menu);

		// Locate share item
		MenuItem item = menu.findItem(R.id.action_share);

		// Fetch and store ShareActionProvider
		shareActionProvider = (ShareActionProvider) item.getActionProvider();

		return true;
	}

	// Call to update the share intent used if UI changes causes changes to
	// intent
	private void setShareIntent(Intent shareIntent) {
		if (shareActionProvider != null) {
			shareActionProvider.setShareIntent(shareIntent);
		}
	}
	
	/**
	 * Open settings activity button in the profile fragment.
	 * 
	 * @param view
	 */
	public void openSettings(View view) {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}
}
