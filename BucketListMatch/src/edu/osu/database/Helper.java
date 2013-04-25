package edu.osu.database;

/**
 * This class holds helper methods which are called in the DB.java class.
 * @author Everly Okorji
 *
 */
public class Helper {

	/**
	 * This class determines whether or not a given string is a valid username, by simply checking
	 * if the string is alphanumeric.
	 * @param str
	 * @return true if the string is a valid username, false otherwise
	 */
	public static boolean isValidUsername(String str) {
		
		char c;
		
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method reads a string and modifies it to support HTTP calls. Certain characters which are used
	 * as special characters in HTTP urls have to be converted to valid formats, and this method does the conversion.
	 * @param str
	 * @return A modified string with all special characters replaced with their valid equivalents.
	 */
	public static String parseForHTTP(String str) {
		String result = "";

		for (int i = 0; i < str.length(); i++) {

			switch (str.charAt(i)) {
			case ' ':
				result += "%20";
				break;
			case '<':
				result += "%3C";
				break;
			case '>':
				result += "%3E";
				break;
			case '#':
				result += "%23";
				break;
			case '%':
				result += "%25";
				break;
			case '{':
				result += "%7B";
				break;
			case '}':
				result += "%7D";
			case '|':
				result += "%7E";
				break;
			case '\\':
				result += "%5B";
				break;
			case '^':
				result += "%5E";
				break;
			case '~':
				result += "%7E";
				break;
			case '[':
				result += "%5B";
				break;
			case ']':
				result += "%5D";
				break;
			case '`':
				result += "%60";
				break;
			case ';':
				result += "%3B";
				break;
			case '/':
				result += "%2F";
				break;
			case '?':
				result += "%3F";
				break;
			case ':':
				result += "%3A";
				break;
			case '@':
				result += "%40";
				break;
			case '=':
				result += "%3D";
				break;
			case '&':
				result += "%26";
				break;
			case '$':
				result += "%24";
				break;
			default:
				result += str.charAt(i);
				break;
			}

		}
		return result;
	}

}
