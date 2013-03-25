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
		
		// Create an instance of HomeFragment
        HomeFragment homeFrag = new HomeFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        homeFrag.setArguments(getIntent().getExtras());

        // Add the fragment to the FrameLayout which is used as the fragment container.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFrag).commit();
		
        
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
				SherlockFragment frag;
				SherlockListFragment listFrag;
				FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
				// Create the appropriate fragment to replace the current fragment with.
				switch (position) {
				case 0:
					frag = new HomeFragment();
					trans.replace(R.id.fragment_container, frag);
					trans.commit();
					break;
				case 1:
					
					listFrag = new DiscoverFragment();
					trans.replace(R.id.fragment_container, listFrag);
					trans.commit();
					break;
				case 2:
					listFrag = new BucketListFragment();
					trans.replace(R.id.fragment_container, listFrag);
					trans.commit();
					break;
				case 3:
					listFrag = new ScrapbookFragment();
					trans.replace(R.id.fragment_container, listFrag);
					trans.commit();
					break;
				case 4:
					frag = new MatchFragment();
					trans.replace(R.id.fragment_container, frag);
					trans.commit();
					break;
				case 5:
					frag = new ProfileFragment();
					trans.replace(R.id.fragment_container, frag);
					trans.commit();
					break;
				default:
					
					break;
				}
				return true;
			}
		};

		// Set items and navigation listener to the action bar.
		getSupportActionBar().setListNavigationCallbacks(spinnerAdapter, navLis);
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
	
	// Call to update the share intent used if UI changes causes changes to intent
	private void setShareIntent(Intent shareIntent) {
	    if (shareActionProvider != null) {
	        shareActionProvider.setShareIntent(shareIntent);
	    }
	}
}
