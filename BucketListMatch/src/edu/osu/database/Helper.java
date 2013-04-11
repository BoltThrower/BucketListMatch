package edu.osu.database;

public class Helper {

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
