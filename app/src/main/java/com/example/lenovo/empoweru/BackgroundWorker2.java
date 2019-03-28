package com.example.lenovo.empoweru;

/**
 * Created by Lenovo on 11-05-2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
import java.util.Arrays;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker2 extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
    Context context;
    AlertDialog alertDialog;
    private long[] nearByColgs;
ArrayList<String>  res=new ArrayList<>();
    BackgroundWorker2(Context ctx) {
        context = ctx;
    }
    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... arrayLists) {

        ArrayList<String> passed = arrayLists[0];
        ArrayList<String> passed2 = arrayLists[1];
        ArrayList<String> passed3 = arrayLists[2];
       // String login_url = "http://172.25.110.108/login.php";
        String login_url = "http://kasmmobileapp.empoweru.in/teacher_attendance/";
            try {
                String name= passed.get(0);
                String surname= passed.get(1);
                String age= passed.get(2);
                String user_name = passed.get(3);
                String password = passed.get(4);
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
int school_id=26;
double lattitude=2.33;
double longitude=3.55;
int accuracy=15;
int markedtype=1;
int marked_by_id=3315;
int slot_id=2;
String markedon="2018-06-12 10:00";
String remark="testing";
String date="2018-06-12";
String user_id="[5657]";
String attendencestatus="[1]";


                 String postdata=URLEncoder.encode("school_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(school_id),"UTF-8")+"&"
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
                     +URLEncoder.encode("attendencestatus","UTF-8")+"="+URLEncoder.encode(attendencestatus , "UTF-8" );
           /*     String post_data =URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                                 +URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(surname,"UTF-8")+"&"+
                                URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                               +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                                +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                       +URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(date, "UTF-8" )+"&"
                   +URLEncoder.encode("att","UTF-8")+"="+URLEncoder.encode(remark, "UTF-8" );*/
                //String.valueOf(passed2.toArray())
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return null ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
    @Override
    protected void onPreExecute() {

        // alertDialog = new AlertDialog.Builder(context).create();
        // alertDialog.setTitle("Insertion Status");
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {

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