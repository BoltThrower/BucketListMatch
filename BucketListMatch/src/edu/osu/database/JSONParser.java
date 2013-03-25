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

public class JSONParser {

	private InputStream is;
//	JSONObject jObj = null;
	private JSONArray jArr;
	private String URL;
	private int status;
	
	// constructor
	public JSONParser(String url) {
		is = null;
		URL = url;
		status = -1;
		
		jArr = parse(getStringFromUrl());
		if (jArr != null) status = 0;
	}
	
	public JSONArray getJSONArray() {
		if (status == 0) return jArr;
		else return null;
	}

	// Source: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
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

	private JSONArray parse(String str) {
		try {
			return new JSONArray(str);
		} catch (JSONException e) {
			Log.e("JSONParser Error", "Error creating JSONArray object. " + e.toString());
			return null;
		}
	}
	
}
