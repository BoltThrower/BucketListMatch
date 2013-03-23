package edu.osu.database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.ImageView;

public class Parser {

	private InputStream is;
	public String result;
	URL url;

	// constructor
	public Parser() {
		is = null;
		result = "No content.";
		try {
			url = new URL ("http://server14.ies.cse.ohio-state.edu/blm.php");
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
	
	private ImageView parseImage() {
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		ImageView imageView = null;
		//imageView = (ImageView) findViewById(R.id.image_view);
		imageView.setImageBitmap(bitmap);
		return imageView;
	}
	
	private String parseText(int len) {
		Reader reader = null;   
		try {
			reader = new InputStreamReader(is, "UTF-8");
			char[] buffer = new char[len];   
			reader.read(buffer);    
			return new String(buffer);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		return null;
		
	}
	
}
