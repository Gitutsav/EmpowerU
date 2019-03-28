package com.example.lenovo.empoweru;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class datetest extends AppCompatActivity  {

    Button submit;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
HttpResponse response;String message,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_test);
        submit= (Button) findViewById(R.id.btn_time);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(view.getContext(),myBase64Image,Toast.LENGTH_LONG ).show();
                HttpClient httpClient = new DefaultHttpClient();
                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(
                        "http://smartschooldemo.empoweru.in/grievance/");
                //  URL url = new URL("file:/E:/Program Files/IBM/SDP/runtimes/base");
                // URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());

                // Syncing part
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
                nameValuePair.add(new BasicNameValuePair("school", "school"));
                nameValuePair.add(new BasicNameValuePair("grievance_type", "grievance_name"));
                nameValuePair.add(new BasicNameValuePair("imagedd", "myBase64Image"));
                nameValuePair.add(new BasicNameValuePair("grievance", "reason"));
                nameValuePair.add(new BasicNameValuePair("uid", "41"));


                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    e.printStackTrace();
                }//Making HTTP Request
                try {
                    response = httpClient.execute(httpPost);
                    String responseBody = EntityUtils.toString(response.getEntity());
                    JSONObject obj = new JSONObject(responseBody);
                    message = obj.getString("message");
                    status = obj.getString("status");
                    Toast.makeText(view.getContext(),message+"\n"+status,Toast.LENGTH_LONG ).show();
                    // Log.d("Http Response:", response.toString());
                } catch (ClientProtocolException e) {
                    // writing exception to log
                    e.printStackTrace();
                } catch (IOException e) {
                    // writing exception to log
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
