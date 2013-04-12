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
		return -1;
	}

	// TODO
	public static int editChapterInfo(int type, String username,
			String password, String chapterName, String info) {
		return -1;
	}

	// TODO
	public static int addMediaToChapter(String username, String password,
			String bookName, String chapterName, String[] mediaInfo) {
		return -1;
	}

	// TODO
	public static int addTotemToChapter(String username, String password,
			String bookName, String chapterName, String[] totemInfo) {
		return -1;
	}

	// TODO
	public static int addChallengeToChapter(String username, String password,
			String bookName, String chapterName, String[] challengeInfo) {
		return -1;
	}

	// TODO
	public static int editMediaInfo(int type, String username, String password,
			String chapterName, String info) {
		return -1;
	}

	// TODO
	public static int editTotemInfo(int type, String username, String password,
			String chapterName, String info) {
		return -1;
	}

	// TODO
	public static int editChallengeInfo(int type, String username,
			String password, String chapterName, String info) {
		return -1;
	}

	// TODO
	public static int removeMediaFromChapter(String username, String password,
			String chapterName, String mediaName) {
		return -1;
	}

	// TODO
	public static int removeTotemFromChapter(String username, String password,
			String chapterName, String TotemName) {
		return -1;
	}

	// TODO
	public static int removeChallengeFromChapter(String username,
			String password, String chapterName, String challengeName) {
		return -1;
	}

	// TODO
	public static int removeChapterFromBook(String username, String password,
			String bookName, String chapterName) {
		return -1;
	}

	// TODO
	public static int deleteChapter(String username, String password,
			String chapterName) {
		return -1;
	}

	// TODO
	public static int addDeal(String username, String password,
			String[] dealInfo) {
		return -1;
	}

	// TODO
	public static int editDealInfo(int type, String username, String password,
			String dealName, String info) {
		return -1;
	}

	// TODO
	public static int deleteDeal(String username, String password,
			String dealName) {
		return -1;
	}

	// TODO
	public static int addMerchant(String[] merchantInfo) {
		return -1;
	}

	// TODO
	public static int addMerchantReview(String username, String password,
			String merchantName, String description, int rating) {
		return -1;
	}

	// TODO
	public static int editMerchantInfo(int type, String username,
			String password, String info) {
		return -1;
	}

	// TODO
	public static int deleteMerchant(String username, String password) {
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
