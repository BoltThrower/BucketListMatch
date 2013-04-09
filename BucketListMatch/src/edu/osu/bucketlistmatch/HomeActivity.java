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
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragment_container, new HomeFragment()).commit();

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
				}
				return true;
			}
		};

		// Set items and navigation listener to the action bar.
		getSupportActionBar()
				.setListNavigationCallbacks(spinnerAdapter, navLis);

		// Disable home button.
		getSupportActionBar().setHomeButtonEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.home, menu);

		// Locate share item
		// May not need this
		MenuItem item = menu.findItem(R.id.action_share);

		// Fetch and store ShareActionProvider
		// May not need this
		shareActionProvider = (ShareActionProvider) item.getActionProvider();

		return true;
	}

	/**
	 * Called when a menu item is selected.
	 * 
	 * @param item
	 * @return
	 */
	public boolean onOptionsMenuSelected(MenuItem item) {
		// Handle cases depending on the menu item's id.
		switch (item.getItemId()) {
		
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Call to update the share intent used if UI changes causes changes to
	// intent
	private void setShareIntent(Intent shareIntent) {
		if (shareActionProvider != null) {
			shareActionProvider.setShareIntent(shareIntent);
		}
	}

	/**
	 * Performs appropriate action depending on id of button clicked. These
	 * buttons are in Profile.
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		switch (view.getId()) {
		case R.id.privacy:
			ft.replace(R.id.fragment_container, new PrivacyFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case R.id.terms:
			ft.replace(R.id.fragment_container, new TermsFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case R.id.signout:

			break;
		}
	}

	public void shareEmail(View view) {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "" }); // Blank
																		// default
																		// email
																		// address
																		// to
																		// send
																		// to.
		emailIntent.putExtra(Intent.EXTRA_SUBJECT,
				"An Invitation to Bucket List Match!");
		emailIntent
				.putExtra(
						Intent.EXTRA_TEXT,
						"A friend of yours sent this e-mail to invite you to join the mobile application, Bucket List Match.\n\nYou may register here: http://www.blm.com/register\n\nThanks.");

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There are no email clients installed.",
					Toast.LENGTH_SHORT).show();
		}
	}
}
