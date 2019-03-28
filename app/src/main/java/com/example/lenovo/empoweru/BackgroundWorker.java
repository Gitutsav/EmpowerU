package com.example.lenovo.empoweru;

/**
 * Created by Lenovo on 11-05-2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = "login";
        String login_url = "http://smartschooldemo.empoweru.in/grievance/";
        if(type.equals("login")) {
            try {

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                String post_data =
                        URLEncoder.encode("school","UTF-8")+"="+URLEncoder.encode("school","UTF-8")+"&"
                        +URLEncoder.encode("grievance_type","UTF-8")+"="+URLEncoder.encode("grievance_name","UTF-8")+"&"+
                        URLEncoder.encode("imagedd","UTF-8")+"="+URLEncoder.encode("myBase64Image","UTF-8")+"&"
                        +URLEncoder.encode("grievance","UTF-8")+"="+URLEncoder.encode("reason","UTF-8")+"&"
                        +URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode("41","UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result= line+result;
                    //result="1234";
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        
       // alertDialog = new AlertDialog.Builder(context).create();
       // alertDialog.setTitle("Insertion Status");
    }

    @Override
    protected void onPostExecute(String result) {

      //  alertDialog.setMessage(result);
       // alertDialog.show();
       // alertDialog.setCanceledOnTouchOutside(true);
        //alertDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}