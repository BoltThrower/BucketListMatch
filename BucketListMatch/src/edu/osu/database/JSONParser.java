package edu.osu.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

/**
 * This class helps to organize the HTTP connection and the fetching and conversion of results into JSON
 * format.
 * @author Everly Okorji
 *
 */
public class JSONParser {

	private InputStream is;
	
	private JSONArray jArr;
	private String URL, str;
	private int status;
	
	/**
	 * Constructor. This is where other methods in the class are called, to fetch results of a query and
	 * store in a JSON array.
	 * @param url
	 * @throws JSONException
	 */
	public JSONParser(String url) throws JSONException {
		is = null;
		URL = url;
		status = -1;
		str = getStringFromUrl();
		jArr = parse(str);
		if (jArr != null) status = 0;
	}
	
	/**
	 * This method returns the string that was derived directly from the HTTP call.
	 * @return string
	 */
	public String getString() {
		return str;
	}
	
	/**
	 * This method returns the prepared JSONArray, or null if there is none.
	 * @return
	 */
	public JSONArray getJSONArray() {
		if (status == 0) return jArr;
		else return null;
	}

	// Source: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
	/**
	 * This method makes the connection to HTTP, queries the PHP server on the back-end and fetches
	 * the result of the query.
	 * @return a string which represents the JSON-encoded result of the query
	 */
	private String getStringFromUrl() {

		String json_string = null;
		
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(URL);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			Log.e("HTTP Error", "1: Unsupported Encoding. " + e.toString());
			status = 1;
		} catch (ClientProtocolException e) {
			Log.e("HTTP Error", "2: Client Protocol Exception. " + e.toString());
			status = 2;
		} catch (IOException e) {
			Log.e("HTTP Error", "3: Input Stream Error. " + e.toString());
			status = 3;
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json_string = sb.toString();
		} catch (Exception e) {
			status = 4;
			Log.e("Buffer Error", "4: Error converting result to string. " + e.toString());
		}

		return json_string;

	}

	/**
	 * This method simply takes a JSON-encoded string and stores the contents as a JSONArray object
	 * @param str
	 * @return JSONArray object represented by the string which was passed in, or null is the string
	 * is invalid
	 * @throws JSONException for invalid string input
	 */
	private JSONArray parse(String str) throws JSONException {
		return new JSONArray(str);
	}
	
}
