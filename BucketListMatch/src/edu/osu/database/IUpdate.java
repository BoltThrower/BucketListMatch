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
	 * @return 0 if add was successful, 1 for SQL Exception, 2 for FileNotFound Exception
	 */
	int addUser(String[] user);
	
	/**
	 * This method replaces the existing user profile photo with the specified new one.
	 * @param username
	 * @param binary A string that represents the stream for the image info.
	 */
	void changeUserProfilePhoto (String username, String binary);

	/**
	 * This method updates the user's location information with the new information.
	 * @param username
	 * @param city
	 * @param state
	 * @param country
	 */
	void updateUserLocation (String username, String city, String state, String country);
	
	/**
	 * This method updates the description of a user
	 * @param username
	 * @param description
	 */
	void updateUserDescription (String username, String description);
	
	/**
	 * This method adds a new Bucket List Book to a user's account.
	 * @param username
	 * @param textInfo A string array of length 6, holding information about title, cover image,
	 * state, country, start date and cost.
	 * @param numInfo An integer array of length 3, holding information about the duration (days),
	 * users completed and users not completed.
	 * @param boolInfo True if privacy settings are activated, false otherwise.
	 */
	void addBucketListBook (String username, String[] textInfo, int[] numInfo, boolean privacy);
	
	// TODO Declare methods here
}
