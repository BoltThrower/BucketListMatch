package edu.osu.database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Build;

public class Parser {

	private InputStream is;
	public String result;
	URL url;

	// constructor
	public Parser() {
		is = null;
		result = "No content.";
		try {
			url = new URL ("http://server14.ies.cse.ohio-state.edu/info.php");
		} catch (MalformedURLException e) {
			result = "1: " + e.getMessage();
		}
	}

	// Source: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
	public void makeRequest() {

		HttpURLConnection urlConnection = null;
		
		// Making HTTP request
		try {     
			 urlConnection = (HttpURLConnection) url.openConnection();
			 is = new BufferedInputStream(urlConnection.getInputStream());
			//readStream(is);
			 
			 result = "Made it";
			 BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				reader.close();
				result = sb.toString();
		 } catch (IOException e) {
			result = "2: " + e.getMessage() + "\n\n" + e.getLocalizedMessage();
		} finally {     
			urlConnection.disconnect();
		 }

	}
	
}
