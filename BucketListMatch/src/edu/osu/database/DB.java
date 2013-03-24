package edu.osu.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

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
		parser = new JSONParser(URL_main + "validateUser.php?user=" + username + "&pass=" + password);
		JSONArray array = parser.getJSONArray();
		
		if (array == null) return -1;
		
		if (array.length() == 0) return 1;
		else if (array.length() > 1) return 2;
		else return 0;
	}
	
	public static JSONObject fetchProfileDetails(String username, String password) {
		if (validateUser (username, password) == 0) {
			parser = new JSONParser(URL_main + "fetchProfileDetails.php");
			JSONArray tmp = parser.getJSONArray();
			try {
				return tmp.getJSONObject(0);
			} catch (JSONException e) {
				return null;
			}
		} else {
			// TODO User details not valid. Pass on this information somehow.
			return null;
		}
	}
	
	
	
	
	// UPDATE METHODS

	// TODO Implement
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
