package edu.osu.database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {

	static InputStream is;
	static String result;
	static  URL url;

	// constructor
	public Parser() {
		is = null;
		result = "";
		try {
			url = new URL ("http://server14.ies.cse.ohio-state.edu");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {     
			urlConnection.disconnect();
		 }

	}
	
}
