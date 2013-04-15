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

	/**
	 * 
	 * @return
	 */
	public static JSONArray getPublicDreamBooks() {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main
					+ "dreambook/fetchAllDreamBooks.php");
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchBucketListBooks.");
		}

		return result;
	}

	public static JSONArray getAllBucketListBooks(String username,
			String password) {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main
					+ "dreambook/fetchBucketListBooks.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password));
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchScrapbooks.");
		}

		return result;
	}

	public static JSONArray getAllScrapbooks(String username, String password) {
		JSONArray result = null;

		try {
			parser = new JSONParser(URL_main
					+ "dreambook/fetchScrapbooks.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password));
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchScrapbooks.");
		}

		return result;
	}
	
	public static JSONArray getChapters(String username, String password, String dreambookName) {
		JSONArray result = null;
		try {
			parser = new JSONParser(URL_main
					+ "dreambook/fetchBucketLists.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(dreambookName));
			result = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchScrapbooks.");
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
		
		if(!Helper.isValidUsername(user[0])) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "user/addUser.php?u="
					+ Helper.parseForHTTP(user[0]) + "&p="
					+ Helper.parseForHTTP(user[1]) + "&fn="
					+ Helper.parseForHTTP(user[2]) + "&ln="
					+ Helper.parseForHTTP(user[3]) + "&db="
					+ Helper.parseForHTTP(user[4]) + "&co="
					+ Helper.parseForHTTP(user[5]) + "&e="
					+ Helper.parseForHTTP(user[6]));
			if (parser.getJSONArray() == null)
				return 2;
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
	public static int changeUserInfo(int type, String username,
			String password, String info) {

		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		info = Helper.parseForHTTP(info);

		try {
			switch (type) {
			case Enum.C_PASS:
				parser = new JSONParser(URL_main + "user/changePassword.php?u="
						+ username + "&p=" + password + "&np=" + info);
				break;
			case Enum.C_PROFILE_PIC:
				parser = new JSONParser(URL_main + "user/changePhoto.php?u="
						+ username + "&p=" + password + "&pp=" + info);
				break;
			case Enum.C_FIRST:
				parser = new JSONParser(URL_main
						+ "user/changeFirstName.php?u=" + username + "&p="
						+ password + "&fn=" + info);
				break;
			case Enum.C_LAST:
				parser = new JSONParser(URL_main + "user/changeLastName.php?u="
						+ username + "&p=" + password + "&ln=" + info);
				break;
			case Enum.C_DESCR:
				parser = new JSONParser(URL_main
						+ "user/changeDescription.php?u=" + username + "&p="
						+ password + "&d=" + info);
				break;
			case Enum.C_DOB:
				parser = new JSONParser(URL_main
						+ "user/changeDateOfBirth.php?u=" + username + "&p="
						+ password + "&db=" + info);
				break;
			case Enum.C_CITY:
				parser = new JSONParser(URL_main + "user/changeCity.php?u="
						+ username + "&p=" + password + "&c=" + info);
				break;
			case Enum.C_STATE:
				parser = new JSONParser(URL_main + "user/changeState.php?u="
						+ username + "&p=" + password + "&s=" + info);
				break;
			case Enum.C_COUNTRY:
				parser = new JSONParser(URL_main + "user/changeCountry.php?u="
						+ username + "&p=" + password + "&co=" + info);
				break;
			case Enum.C_ZIP:
				parser = new JSONParser(URL_main + "user/changeZipCode.php?u="
						+ username + "&p=" + password + "&z=" + info);
				break;
			case Enum.C_EMAIL:
				parser = new JSONParser(URL_main + "user/changeEmail.php?u="
						+ username + "&p=" + password + "&e=" + info);
				break;
			case Enum.C_PHONE:
				parser = new JSONParser(URL_main + "user/changePhone.php?u="
						+ username + "&p=" + password + "&ph=" + info);
				break;
			default:
				return 3;
			}

			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

	public static int deleteUser(String username, String password) {
		try {
			parser = new JSONParser(URL_main + "user/deleteUser.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

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
	public static int addBucketListBook(String username, String password,
			String[] book, boolean privacy) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		String privacy_char;
		if (privacy)
			privacy_char = "Y";
		else
			privacy_char = "N";
		try {
			parser = new JSONParser(URL_main
					+ "dreambook/addBucketListBook.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(book[0]) + "&i="
					+ Helper.parseForHTTP(book[1]) + "&s="
					+ Helper.parseForHTTP(book[2]) + "&co="
					+ Helper.parseForHTTP(book[3]) + "&sd="
					+ Helper.parseForHTTP(book[4]) + "&pr=" + privacy_char);
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;

	}

	public static int editBucketListBookPrivacy(String username,
			String password, String bookName, boolean new_privacy) {
		try {
			if (new_privacy == true) {
				parser = new JSONParser(URL_main
						+ "dreambook/setPrivate.php?u="
						+ Helper.parseForHTTP(username) + "&p="
						+ Helper.parseForHTTP(password) + "&n="
						+ Helper.parseForHTTP(bookName));
			} else {
				parser = new JSONParser(URL_main + "dreambook/setPublic.php?u="
						+ Helper.parseForHTTP(username) + "&p="
						+ Helper.parseForHTTP(password) + "&n="
						+ Helper.parseForHTTP(bookName));
			}
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

	public static int editBucketListBookInfo(int type, String username,
			String password, String bookName, String info) {

		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		bookName = Helper.parseForHTTP(bookName);
		info = Helper.parseForHTTP(info);

		try {
			switch (type) {
			case Enum.B_IMAGE:
				parser = new JSONParser(URL_main + "dreambook/editImage.php?u="
						+ username + "&p=" + password + "&n=" + bookName
						+ "&i=" + info);
				break;
			case Enum.B_STATE:
				parser = new JSONParser(URL_main + "dreambook/editState.php?u="
						+ username + "&p=" + password + "&n=" + bookName
						+ "&s=" + info);
				break;
			case Enum.B_COUNTRY:
				parser = new JSONParser(URL_main
						+ "dreambook/editCountry.php?u=" + username + "&p="
						+ password + "&n=" + bookName + "&co=" + info);
				break;
			case Enum.B_STARTDATE:
				parser = new JSONParser(URL_main
						+ "dreambook/editStartDate.php?u=" + username + "&p="
						+ password + "&n=" + bookName + "&sd=" + info);
				break;
			default:
				return 3;
			}

			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}

		return 0;
	}

	public static int moveToScrapbook(String username, String password,
			String bookName) {
		try {
			parser = new JSONParser(URL_main
					+ "dreambook/moveToScrapbook.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(bookName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

	public static int deleteDreamBook(String username, String password,
			String bookName) {
		try {
			parser = new JSONParser(URL_main
					+ "dreambook/deleteDreamBook.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(bookName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

	public static int addChapter(String username, String password,
			String bookName, String[] chapterInfo) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "chapter/addChapter.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(bookName) + "&ch="
					+ Helper.parseForHTTP(chapterInfo[0]) + "&sd="
					+ Helper.parseForHTTP(chapterInfo[1]) + "&du="
					+ Helper.parseForHTTP(chapterInfo[2]) + "&co="
					+ Helper.parseForHTTP(chapterInfo[3]) + "&ur="
					+ Helper.parseForHTTP(chapterInfo[4]) + "&rom="
					+ Helper.parseForHTTP(chapterInfo[5]) + "&des="
					+ Helper.parseForHTTP(chapterInfo[6]) + "&pr="
					+ Helper.parseForHTTP(chapterInfo[7]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addChapterToBook(String username, String password,
			String bookName, String chapterName, String chapterCreator) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "chapter/addToBook.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(bookName) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&chc="
					+ Helper.parseForHTTP(chapterCreator));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editChapterInfo(int type, String username,
			String password, String chapterName, String info) {
		try {
			username = Helper.parseForHTTP(username);
			password = Helper.parseForHTTP(password);
			chapterName = Helper.parseForHTTP(chapterName);
			info = Helper.parseForHTTP(info);
			switch(type) {
			case Enum.A_START_DATE:
				parser = new JSONParser(URL_main + "chapter/editStartDate.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&sd=" + info);
				break;
			case Enum.A_DURATION:
				parser = new JSONParser(URL_main + "chapter/editDuration.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&cd=" + info);
				break;
			case Enum.A_COST:
				parser = new JSONParser(URL_main + "chapter/editCost.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&m=" + info);
				break;
			case Enum.A_URL:
				parser = new JSONParser(URL_main + "chapter/editURL.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&url=" + info);
				break;
			case Enum.A_ROM:
				parser = new JSONParser(URL_main + "chapter/editROM.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&rom=" + info);	
				break;
			case Enum.A_DESCRIPTION:
				parser = new JSONParser(URL_main + "chapter/editDescription.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName +
						"&des=" + info);	
				break;
			default:
				return 3;
			
			}
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addMediaToChapter(String username, String password,
			String bookName, String chapterName, String[] mediaInfo) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "chapter/media/.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&mn="
					+ Helper.parseForHTTP(mediaInfo[0]) + "&mf="
					+ Helper.parseForHTTP(mediaInfo[1]) + "&mt="
					+ Helper.parseForHTTP(mediaInfo[2]) + "&des="
					+ Helper.parseForHTTP(mediaInfo[3]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addTotemToChapter(String username, String password,
			String bookName, String chapterName, String[] totemInfo) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "chapter/totem/addTotem.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&tn="
					+ Helper.parseForHTTP(totemInfo[0]) + "&ti="
					+ Helper.parseForHTTP(totemInfo[1]) + "&des="
					+ Helper.parseForHTTP(totemInfo[2]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addChallengeToChapter(String username, String password,
			String bookName, String chapterName, String[] challengeInfo) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "chapter/challenge/addChallenge.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&t="
					+ Helper.parseForHTTP(challengeInfo[0]) + "&des="
					+ Helper.parseForHTTP(challengeInfo[1]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editMediaInfo(int type, String username, String password,
			String chapterName, String mediaName, String info) {
		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		chapterName = Helper.parseForHTTP(chapterName);
		mediaName = Helper.parseForHTTP(mediaName);
		
		try {
			switch(type) {
			case Enum.D_NAME:
				parser = new JSONParser(URL_main + "chapter/media/editName.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&mn=" +
						mediaName + "&nn=" + info);
				break;
			case Enum.D_FILE:
				parser = new JSONParser(URL_main + "chapter/media/editFile.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&mn=" +
						mediaName + "&mf=" + info);
				break;
			case Enum.D_TYPE:
				parser = new JSONParser(URL_main + "chapter/media/editType.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&mn=" +
						mediaName + "&mt=" + info);
				break;
			case Enum.D_DESCRIPTION:
				parser = new JSONParser(URL_main + "chapter/media/editDescription.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&mn=" +
						mediaName + "&des=" + info);
				break;
			default:
				return 3;
			}
			
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editTotemInfo(int type, String username, String password,
			String chapterName, String totemName, String info) {
		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		chapterName = Helper.parseForHTTP(chapterName);
		totemName = Helper.parseForHTTP(totemName);
		
		try {
			switch(type) {
			case Enum.E_PHOTO:
				parser = new JSONParser(URL_main + "chapter/totem/editPhoto.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&tn=" +
						totemName + "&ti=" + info);
				break;
			case Enum.E_DESCRIPTION:
				parser = new JSONParser(URL_main + "chapter/media/editDescription.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&tn=" +
						totemName + "&des=" + info);
				break;
			default:
				return 3;
			}
			
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editChallengeInfo(int type, String username,
			String password, String chapterName, String title, String info) {
		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		chapterName = Helper.parseForHTTP(chapterName);
		title = Helper.parseForHTTP(title);
		
		try {
			switch(type) {
			case Enum.F_TITLE:
				parser = new JSONParser(URL_main + "chapter/challenge/editTitle.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&t=" +
						title + "&nt=" + info);
				break;
			case Enum.F_DESCRIPTION:
				parser = new JSONParser(URL_main + "chapter/challenge/editDescription.php?u=" +
						username + "&p=" + password + "&ch=" + chapterName + "&t=" +
						title + "&des=" + info);
				break;
			default:
				return 3;
			}
			
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int removeMediaFromChapter(String username, String password,
			String chapterName, String mediaName) {
		try {
			parser = new JSONParser(URL_main + "chapter/media/deleteMedia.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&mn="
					+ Helper.parseForHTTP(mediaName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int removeTotemFromChapter(String username, String password,
			String chapterName, String totemName) {
		try {
			parser = new JSONParser(URL_main + "chapter/totem/deleteTotem.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&tn="
					+ Helper.parseForHTTP(totemName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int removeChallengeFromChapter(String username,
			String password, String chapterName, String challengeName) {
		try {
			parser = new JSONParser(URL_main + "chapter/challenge/deleteChallenge.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&t="
					+ Helper.parseForHTTP(challengeName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int removeChapterFromBook(String username, String password,
			String bookName, String chapterName, String chapterCreator) {
		try {
			parser = new JSONParser(URL_main + "chapter/removeFromBook.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(bookName) + "&ch="
					+ Helper.parseForHTTP(chapterName) + "&chc="
					+ Helper.parseForHTTP(chapterCreator));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int deleteChapter(String username, String password,
			String chapterName) {
		try {
			parser = new JSONParser(URL_main + "chapter/deleteChapter.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&ch="
					+ Helper.parseForHTTP(chapterName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addDeal(String username, String password,
			String[] dealInfo) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "deal/addDeal.php?mu="
					+ Helper.parseForHTTP(username) + "&mp="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(dealInfo[0]) + "&i="
					+ Helper.parseForHTTP(dealInfo[1]) + "&des="
					+ Helper.parseForHTTP(dealInfo[2]) + "&da="
					+ Helper.parseForHTTP(dealInfo[3]) + "&fp="
					+ Helper.parseForHTTP(dealInfo[4]) + "&ed="
					+ Helper.parseForHTTP(dealInfo[5]) + "&max="
					+ Helper.parseForHTTP(dealInfo[6]) + "&min="
					+ Helper.parseForHTTP(dealInfo[7]) + "&st="
					+ Helper.parseForHTTP(dealInfo[8] + "&c=")
					+ Helper.parseForHTTP(dealInfo[9] + "&s=")
					+ Helper.parseForHTTP(dealInfo[10]) + "&co=" 
					+ Helper.parseForHTTP(dealInfo[11]) + "&z="
					+ Helper.parseForHTTP(dealInfo[12]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editDealInfo(int type, String username, String password,
			String dealName, String info) {
		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		dealName = Helper.parseForHTTP(dealName);
		
		try {
			switch(type) {
			case Enum.G_IMAGE:
				parser = new JSONParser(URL_main + "deal/editImage.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&i=" + info);
				break;
			case Enum.G_DESCRIPTION:
				parser = new JSONParser(URL_main + "deal/editDescirption.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&des=" + info);
				break;
			case Enum.G_DISCOUNT_AMOUNT:
				parser = new JSONParser(URL_main + "deal/editDiscountAmount.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&da=" + info);
				break;
			case Enum.G_FINAL_PRICE:
				parser = new JSONParser(URL_main + "deal/editFinalPrice.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&fp=" + info);
				break;
			case Enum.G_EXPIRATION_DATE:
				parser = new JSONParser(URL_main + "deal/editExpirationDate.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&ed=" + info);
				break;
			case Enum.G_MAX_QUANTITY:
				parser = new JSONParser(URL_main + "deal/editMaxQuantity.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&max=" + info);
				break;
			case Enum.G_MIN_QUANTITY:
				parser = new JSONParser(URL_main + "deal/editMinQuantity.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&min=" + info);
				break;
			case Enum.G_STREET:
				parser = new JSONParser(URL_main + "deal/editStreet.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&st=" + info);
				break;
			case Enum.G_CITY:
				parser = new JSONParser(URL_main + "deal/editCity.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&c=" + info);
				break;
			case Enum.G_STATE:
				parser = new JSONParser(URL_main + "deal/editState.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&s=" + info);
				break;
			case Enum.G_COUNTRY:
				parser = new JSONParser(URL_main + "deal/editCountry.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&co=" + info);
				break;
			case Enum.G_ZIP_CODE:
				parser = new JSONParser(URL_main + "deal/editZipCode.php?mu=" +
						username + "&mp=" + password + "&n=" + dealName +
						"&z=" + info);
				break;
			default:
				return 3;
			}
			
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int deleteDeal(String username, String password,
			String dealName) {
		try {
			parser = new JSONParser(URL_main + "deal/deleteDeal.php?mu="
					+ Helper.parseForHTTP(username) + "&mp="
					+ Helper.parseForHTTP(password) + "&n="
					+ Helper.parseForHTTP(dealName));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addMerchant(String[] merchantInfo) {
		try {
			if(!Helper.isValidUsername(merchantInfo[0])) {
				return 3;
			}
			parser = new JSONParser(URL_main + "merchant/addMerchant.php?mu="
					+ Helper.parseForHTTP(merchantInfo[0]) + "&mp="
					+ Helper.parseForHTTP(merchantInfo[1]) + "&n="
					+ Helper.parseForHTTP(merchantInfo[2]) + "&i="
					+ Helper.parseForHTTP(merchantInfo[3]) + "&des="
					+ Helper.parseForHTTP(merchantInfo[4]) + "&st="
					+ Helper.parseForHTTP(merchantInfo[5]) + "&c="
					+ Helper.parseForHTTP(merchantInfo[6]) + "&s="
					+ Helper.parseForHTTP(merchantInfo[7]) + "&co="
					+ Helper.parseForHTTP(merchantInfo[8]) + "&z="
					+ Helper.parseForHTTP(merchantInfo[9]) + "&e="
					+ Helper.parseForHTTP(merchantInfo[10]) + "&ph="
					+ Helper.parseForHTTP(merchantInfo[11]) + "&t="
					+ Helper.parseForHTTP(merchantInfo[12]) + "&d="
					+ Helper.parseForHTTP(merchantInfo[13]) + "&h="
					+ Helper.parseForHTTP(merchantInfo[14]) + "&res="
					+ Helper.parseForHTTP(merchantInfo[15]) + "&del="
					+ Helper.parseForHTTP(merchantInfo[16]) + "&tak="
					+ Helper.parseForHTTP(merchantInfo[17]) + "&os="
					+ Helper.parseForHTTP(merchantInfo[18]) + "&att="
					+ Helper.parseForHTTP(merchantInfo[19]) + "&as="
					+ Helper.parseForHTTP(merchantInfo[20]) + "&url="
					+ Helper.parseForHTTP(merchantInfo[21]));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int addMerchantReview(String username, String password,
			String merchantName, String description, int rating) {
		if(!Helper.isValidUsername(username)) {
			return 3;
		}
		try {
			parser = new JSONParser(URL_main + "merchant/review/addReview.php?u="
					+ Helper.parseForHTTP(username) + "&p="
					+ Helper.parseForHTTP(password) + "&mer="
					+ Helper.parseForHTTP(merchantName) + "&des="
					+ Helper.parseForHTTP(description) + "&rat="
					+ Helper.parseForHTTP(String.valueOf(rating)));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int editMerchantInfo(int type, String username,
			String password, String info) {
		username = Helper.parseForHTTP(username);
		password = Helper.parseForHTTP(password);
		
		try {
			switch(type) {
			case Enum.H_NAME:
				parser = new JSONParser(URL_main + "merchant/changeName.php?mu=" +
						username + "&mp=" + password + "&n=" + info);
				break;
			case Enum.H_PROFILE_PICTURE:
				parser = new JSONParser(URL_main + "merchant/changeProfilePicture.php?mu=" +
						username + "&mp=" + password + "&i=" + info);
				break;
			case Enum.H_DESCRIPTION:
				parser = new JSONParser(URL_main + "merchant/changeDesciption.php?mu=" +
						username + "&mp=" + password + "&des=" + info);
				break;
			case Enum.H_STREET:
				parser = new JSONParser(URL_main + "merchant/changeStreet.php?mu=" +
						username + "&mp=" + password + "&st=" + info);
				break;
			case Enum.H_CITY:
				parser = new JSONParser(URL_main + "merchant/changeCity.php?mu=" +
						username + "&mp=" + password + "&c=" + info);
				break;
			case Enum.H_STATE:
				parser = new JSONParser(URL_main + "merchant/changeState.php?mu=" +
						username + "&mp=" + password + "&s=" + info);
				break;
			case Enum.H_COUNTRY:
				parser = new JSONParser(URL_main + "merchant/changeCountry.php?mu=" +
						username + "&mp=" + password + "&co=" + info);
				break;
			case Enum.H_ZIP_CODE:
				parser = new JSONParser(URL_main + "merchant/changeZipCode.php?mu=" +
						username + "&mp=" + password + "&z=" + info);
				break;
			case Enum.H_EMAIL:
				parser = new JSONParser(URL_main + "merchant/changeEmail.php?mu=" +
						username + "&mp=" + password + "&e=" + info);
				break;
			case Enum.H_PHONE:
				parser = new JSONParser(URL_main + "merchant/changePhone.php?mu=" +
						username + "&mp=" + password + "&ph=" + info);
				break;
			case Enum.H_MERCHANT_TYPE:
				parser = new JSONParser(URL_main + "merchant/changeMerchantType.php?mu=" +
						username + "&mp=" + password + "&t=" + info);
				break;
			case Enum.H_LONGER_DESCRIPTION:
				parser = new JSONParser(URL_main + "merchant/changeLongerDescription.php?mu=" +
						username + "&mp=" + password + "&ld=" + info);
				break;
			case Enum.H_HOURS:
				parser = new JSONParser(URL_main + "merchant/changeHours.php?mu=" +
						username + "&mp=" + password + "&h=" + info);
				break;
			case Enum.H_RESERVATIONS:
				parser = new JSONParser(URL_main + "merchant/changeReservations.php?mu=" +
						username + "&mp=" + password + "&res=" + info);
				break;
			case Enum.H_DELIVERY:
				parser = new JSONParser(URL_main + "merchant/changeDelivery.php?mu=" +
						username + "&mp=" + password + "&del=" + info);
				break;
			case Enum.H_TAKEOUT:
				parser = new JSONParser(URL_main + "merchant/changeTakeout.php?mu=" +
						username + "&mp=" + password + "&tak=" + info);
				break;
			case Enum.H_OUTDOOR_SEATING:
				parser = new JSONParser(URL_main + "merchant/changeOutdoorSeating.php?mu=" +
						username + "&mp=" + password + "&os=" + info);
				break;
			case Enum.H_ATTIRE:
				parser = new JSONParser(URL_main + "merchant/changeAttire.php?mu=" +
						username + "&mp=" + password + "&att=" + info);
				break;
			case Enum.H_ALCOHOL_SERVED:
				parser = new JSONParser(URL_main + "merchant/changeAlcoholServed.php?mu=" +
						username + "&mp=" + password + "&as=" + info);
				break;
			case Enum.H_URL:
				parser = new JSONParser(URL_main + "merchant/changeURL.php?mu=" +
						username + "&mp=" + password + "&url=" + info);
				break;
			default:
				return 3;
			}
			
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
	}

	// TODO
	public static int deleteMerchant(String username, String password) {
		try {
			parser = new JSONParser(URL_main + "merchant/deleteMerchant.php?mu="
					+ Helper.parseForHTTP(username) + "&mp="
					+ Helper.parseForHTTP(password));
			if (parser.getJSONArray() == null)
				return 2;
		} catch (JSONException e) {
			Log.e("JSONParser Error.",
					"Result of JSON Array may be null, or contain a null value being referenced. Error occurred in addUser.");
			return 1;
		}
		return 0;
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
