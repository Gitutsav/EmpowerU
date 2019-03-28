package com.example.lenovo.empoweru;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidHTTPRequestsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();
		// Creating HTTP Post
		HttpPost httpPost = new HttpPost(
				"http://kasmmobileapp.empoweru.in/teacher_attendance/");

		// Building post parameters
		// key and value pair
		/*jsonObject.accumulate("fname", "person.getName()");
		jsonObject.accumulate("lname", "person.getCountry()");
		jsonObject.accumulate("contact", "person.getTwitter()");
		jsonObject.accumulate("about", "person.getCountry()");*/

	/*	String postdata= URLEncoder.encode("school_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(school_id),"UTF-8")+"&"
				+URLEncoder.encode("lattitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(lattitude),"UTF-8")+"&"+
				URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(String.valueOf(longitude),"UTF-8")+"&"
				+URLEncoder.encode("accuracy","UTF-8")+"="+URLEncoder.encode(String.valueOf(accuracy),"UTF-8")+"&"
				+URLEncoder.encode("markedon","UTF-8")+"="+URLEncoder.encode(markedon,"UTF-8")+"&"
				+URLEncoder.encode("markedtype","UTF-8")+"="+URLEncoder.encode(String.valueOf(markedtype),"UTF-8")+"&"
				+URLEncoder.encode("marked_by_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(marked_by_id),"UTF-8")+"&"+
				URLEncoder.encode("slot_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(slot_id),"UTF-8")+"&"
				+URLEncoder.encode("remark","UTF-8")+"="+URLEncoder.encode(remark,"UTF-8")+"&"
				+URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
				+URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id , "UTF-8" )+"&"
				+URLEncoder.encode("attendencestatus","UTF-8")+"="+URLEncoder.encode(attendencestatus , "UTF-8" );*/
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(12);
		nameValuePair.add(new BasicNameValuePair("school_id", "731"));
		nameValuePair.add(new BasicNameValuePair("lattitude", "1.0"));
		nameValuePair.add(new BasicNameValuePair("longnitude", "74.21"));
		nameValuePair.add(new BasicNameValuePair("accuracy", "12"));
		nameValuePair.add(new BasicNameValuePair("markedon","2018-06-17 10:45"));
		nameValuePair.add(new BasicNameValuePair("markedtype", "1"));
		nameValuePair.add(new BasicNameValuePair("marked_by_id", "3315"));
		nameValuePair.add(new BasicNameValuePair("slot_id", "2"));
		nameValuePair.add(new BasicNameValuePair("remark","Hi, trying Android HTTP post!"));
		nameValuePair.add(new BasicNameValuePair("date", "2018-06-17"));
		nameValuePair.add(new BasicNameValuePair("user_id1", "5687,6125"));
		nameValuePair.add(new BasicNameValuePair("attendencestatus1","1,2"));
		// Url Encoding the POST parameters
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}

		// Making HTTP Request
		try {
			HttpResponse response = httpClient.execute(httpPost);

			// writing response to log
			Log.d("Http Response:", response.toString());
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();

		}
	}
}