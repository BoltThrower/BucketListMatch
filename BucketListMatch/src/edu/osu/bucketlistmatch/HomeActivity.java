package edu.osu.bucketlistmatch;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * This activity provides navigation between views Home, Discover, Bucket List,
 * Scrapbook, Match, and Profile.
 * 
 * @author Shi Ho Wang
 * @author Chris Hartman
 * 
 */
public class HomeActivity extends SherlockFragmentActivity {

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

		return true;
	}

	/**
	 * Runs when a menu item is selected.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle cases depending on the menu item's id.
		switch (item.getItemId()) {
		case R.id.action_search:
			startActivity(new Intent(this, SearchActivity.class));
			break;
		case R.id.action_add_item:
			promptItemSelection();
			break;
		case R.id.action_share:
			shareEmail();
			return true;
		}

		return false;
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
		case R.id.editProfile:
			ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.emailViewSwitcher);
			switcher.showNext();

			break;
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
			Intent intent = new Intent(this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
	}

	/**
	 * Allows the user to share this app with email.
	 */
	private void shareEmail() {
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

	/**
	 * Prompts the user to either add a bucket list item or add chapter.
	 */
	private void promptItemSelection() {
		final CharSequence[] items = { "Add Bucket List Item", "Add Chapter" };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// Set items and selection behavior.
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int position) {
				switch (position) {
				case 0:
					startActivity(new Intent(getBaseContext(),
							AddBucketListItemActivity.class));
					break;
				case 1:
					startActivity(new Intent(getBaseContext(),
							AddChapterItemActivity.class));
					break;
				}
			}
		});

		// Create and show dialog.
		AlertDialog alert = builder.create();
		alert.show();
	}
}
