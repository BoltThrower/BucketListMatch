package edu.osu.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * This class is the handler for database communications to the application.
 * All queries and updates to the database are organized by this class.
 * @author Everly Okorji
 *
 */
public class DB {
	
	private static JSONParser parser;
	private final static String URL_main = "http://server14.ies.cse.ohio-state.edu/BLM/";
	// QUERY METHODS

	public static int validateUser(String username, String password) {
		
		JSONArray array = null;
		try {
			parser = new JSONParser(URL_main + "validateUser.php?u=" + username + "&p=" + password);
			array = parser.getJSONArray();
		} catch (JSONException e) {
			Log.e("JSONParser Error.", "Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchProfileDetails.");
		}
		
		if (array == null) return -1;
		
		if (array.length() == 0) return 1;
		else if (array.length() > 1) return 2;
		else return 0;
		
	}
	
	public static JSONObject fetchProfileDetails(String username, String password) {

		JSONObject result = null;
		if (validateUser (username, password) == 0) {
			try {
				parser = new JSONParser(URL_main + "fetchProfileDetails.php?u=" + username + "&p=" + password);
				JSONArray tmp = parser.getJSONArray();
				result = tmp.getJSONObject(0);
			} catch (JSONException e) {
				Log.e("JSONParser Error.", "Result of JSON Array may be null, or contain a null value being referenced. Error occurred in fetchProfileDetails.");
			}
			
		} else {
			Log.e("Invalid User Error.", "Cannot fetch profile details; user credentials are invalid.");
		}
		
		return result;
		
	}
	
	
	// UPDATE METHODS

	// TODO Implement
	/**
	 * This method adds a user to the database.
	 * @param user
	 * @return int 0 if successful, otherwise a status number is returned depending on the error
	 */
	public static int addUser(String user[]) {
		return -1;
	}

	// TODO Implement
	static int changeUserProfilePhoto (String username, String password, String location) {
		return -1;
		
	}
	
	// TODO Implement
	static int updateUserLocation (String username, String password, String city, String state, String country) {
		return -1;
		
	}
	
	// TODO Implement
	static int updateUserDescription (String username, String password, String description) {
		return -1;
		
	}
	
	// TODO Implement
	static int addBucketListBook (String username, String password, String[] info, boolean privacy) {
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
