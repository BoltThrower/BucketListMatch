package edu.osu.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * This class is the handler for database communications to the application. All
 * queries and updates to the database are organized by this class.
 * 
 * @author Everly Okorji
 * 
 */
public class DB {

	private static JSONParser parser;
	private final static String URL_main = "http://server14.ies.cse.ohio-state.edu/BLM/";

	public static enum User {
		password, profilePicture, firstname, lastname, description, dateOfBirth, city, state, country, zipCode, email, phone
	};

	/*
	 * NOTE: Each method checks that the entries are valid before passing them
	 * on to the database.
	 */

	// QUERY METHODS

	/**
	 * This method checks to see if a user exists in the system.
	 * 
	 * @param username
	 * @param password
	 * @return An int representing the status of the operation: 0 for success, 1
	 *         if the username-password combination does not exist, -1 for
	 *         internal error that results in duplicate combinations existing in
	 *         the system.
	 */
	public static int validateUser(String username, String password) {

		JSONArray array = null;
		try {
			parser = new JSONParser(URL_main + "user/validateUser.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password));
			array = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchProfileDetails.");
		}

		if (array == null)
			return 1;
		else if (array.length() > 1)
			return -1;
		else
			return 0;

	}

	/**
	 * This method validates a user by username and password, and then fetches
	 * their profile information and returns the information in a JSON format.
	 * 
	 * @param username
	 *            The user's username.
	 * @param password
	 *            The user's password.
	 * @return A JSONObject that contains fields with user profile information.
	 */
	public static JSONObject fetchProfileDetails(String username,
			String password) {

		JSONObject result = null;
		if (validateUser(username, password) == 0) {
			try {
				parser = new JSONParser(URL_main
						+ "user/fetchProfileDetails.php?u="
						+ Helper.parseForHTTP(username) + "&p="
						+ Helper.parseForHTTP(password));
				JSONArray tmp = parser.getJSONArray();
				result = tmp.getJSONObject(0);
			} catch (JSONException e) {
				Log.e("JSONParser Error.",
						"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchProfileDetails.");
			}

		} else {
			Log.e("Invalid User Error.",
					"Cannot fetch profile details; user credentials are invalid.");
		}

		return result;

	}

	public static JSONArray match(String username, String password) {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main + "search_algorithms/match.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password));
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchBucketListBooks.");
		}

		return result;
	}

	// UPDATE METHODS

	// TODO Implement
	/**
	 * This method takes user information through a string and adds the user to
	 * the database.
	 * 
	 * @param user
	 *            array, where each string represents an attribute in the
	 *            database entity, and they are passed in the same order which
	 *            they appear in the database i.e. (Username, Password,
	 *            ProfilePicture, Firstname, LastName, Description, DateOfBirth,
	 *            City, State, Country, ZipCode, Email, Phone)
	 * @return int 0 if successful, otherwise a status number is returned
	 *         depending on the resulting error.
	 */
	public static int addUser(String user[]) {

		try {
			parser = new JSONParser(URL_main + "user/addUser.php?u="
					+ Helper.parseForHTTP(user[0]) + "&p="
					+ Helper.parseForHTTP(user[1]) + "&fn="
					+ Helper.parseForHTTP(user[2]) + "&ln="
					+ Helper.parseForHTTP(user[3]) + "&db="
					+ Helper.parseForHTTP(user[4]) + "&co="
					+ Helper.parseForHTTP(user[5]) + "&e="
					+ Helper.parseForHTTP(user[6]));
			JSONArray tmp = parser.getJSONArray();
			if (tmp.length() != 0) return 1;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	/**
	 * This method validates a user, checks to see if info is a valid entry,
	 * then calls the web service depending on what attribute is being updated.
	 * 
	 * @param type
	 *            an enum variable describing what attribute is being updated.
	 *            See declaration on top of this page.
	 * @param username
	 * @param password
	 * @param info
	 *            The new value to be put into the database. NOTE: for updating
	 *            profile picture, the info variable represents a path name for
	 *            the new photo to be used.
	 * @return
	 */
	static int changeUserInfo(int type, String username, String password,
			String info) {
		return -1;
	}

	static int deleteUser(String username, String password) {
		return -1;
	}

	// TODO Implement
	/**
	 * This method validates user credentials, then adds a bucketlist entry to
	 * the database.
	 * 
	 * @param username
	 * @param password
	 * @param book
	 * @param privacy
	 * @return int 0 if successful, otherwise a status number is returned
	 *         depending on the error.
	 */
	static int addBucketListBook(String username, String password,
			String[] book, boolean privacy) {
		return -1;

	}

	/**
	 * 
	 * @return
	 */
	public static JSONArray getAllBucketListBook() {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main
					+ "dreambook/fetchAllBucketListBooks.php");
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchBucketListBooks.");
		}

		return result;
	}

	static int editBucketListBookPrivacy(String username, String password,
			String bookName, boolean new_privacy) {
		return -1;
	}

	static int editBucketListBookInfo(String username, String password,
			String bookName, String info) {
		return -1;
	}

	static JSONArray getAllScrapbook(String username, String password) {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main + "dreambook/fetchScrapbooks.php");
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchScrapbooks.");
		}

		return result;
	}

	static int moveToScrapbook(String username, String password, String bookName) {
		return -1;
	}

	static int deleteDreamBook(String username, String password,
			String bookName, boolean type) {
		return -1;
	}

	static int addChapter(String username, String password, String bookName,
			String[] chapterInfo) {
		return -1;
	}

	static JSONArray getAllChapters(String bookName) {
		JSONArray result = null;

		return result;
	}

	static int removeChapterFromBook(String username, String password,
			String bookName, String chapterName) {
		return -1;
	}

	static int addMediaToChapter(String username, String password,
			String bookName, String chapterName, String[] mediaInfo) {
		return -1;
	}

	static int addChallengeToChapter(String username, String password,
			String bookName, String chapterName, String[] challengeInfo) {
		return -1;
	}

	static int addTotemToChapter(String username, String password,
			String bookName, String chapterName, String[] totemInfo) {
		return -1;
	}

	// HELPER METHODS

	// TODO
	static Bitmap convertToImage(String binary) {
		return null;
	}

	// TODO
	static String convertToBinary(Bitmap image) {
		return null;
	}

}
