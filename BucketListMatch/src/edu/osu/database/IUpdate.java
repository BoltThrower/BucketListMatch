package edu.osu.database;


/**
 * This is where all methods that update information in the database are
 * handled.
 * 
 * @author Everly Okorji
 * 
 */
public interface IUpdate {

	/**
	 * This method creates a new user, given the profile information.
	 * @param user An array of length 13, containing string values for the new user's info
	 * @return int 0 if add was successful, 1 for SQL Exception, 2 for FileNotFound Exception
	 */
	int addUser(String[] user);
//
//	/**
//	 * This method updates the user's location information with the new information.
//	 * @param username
//	 * @param city
//	 * @param state
//	 * @param country
//	 * @return int 0 if update was successful, 1 for SQL error
//	 */
//	int updateUserLocation (String username, String city, String state, String country);
//	
//	/**
//	 * This method replaces the existing user profile photo with the specified new one.
//	 * @param username
//	 * @param location The complete path of the photo on the device.
//	 * @return int 0 if successful, 1 for SQL error, 2 if file not found
//	 */
//	int changeUserProfilePhoto (String username, String location);
//	
//	/**
//	 * This method updates the description of a user
//	 * @param username
//	 * @param description
//	 * @return int 0 if update was successful, 1 for SQL error
//	 */
//	int updateUserDescription (String username, String description);
//	
//	/**
//	 * This method updates the user's zip code
//	 * @param username
//	 * @param zip
//	 * @return int 0 if update was successful, 1 for SQL error
//	 */
//	int updateUserZipCode(String username, String zip);
//
//	/**
//	 * This function replaces the previous phone number registered to a user with the specified
//	 * number.
//	 * @param username
//	 * @param phone
//	 * @return int 0 if update was successful, 1 for SQL error
//	 */
//	int updateUserPhone(String username, String phone);
//	
	
	int updateUserItem(int type, String username, String item1, String item2, String item3);
	
	/**
	 * This method adds a new Bucket List Book to a user's account.
	 * @param username
	 * @param textInfo A string array holding all bucket list book items to be added
	 * @return int status
	 */
	int addDreamBook (String username, String[] info, boolean privacy);
	
	int updateDreamBookItem (int type, String username, String item1, String item2);
	// TODO Declare methods here
}
