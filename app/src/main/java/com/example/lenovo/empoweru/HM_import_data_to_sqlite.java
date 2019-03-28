package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class HM_import_data_to_sqlite extends AppCompatActivity {



    private final String URL = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt";
    private TextView tvData, rdtest;
    private ListView lvMovies;

    Button getdata;
    private ProgressDialog dialog;
    TextView cast, story, direction, duration;
    //   private NotesAdapter mAdapter;

    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

String userid,passsword;
private HM_details5 hmd;

    // Git error fix - http://stackoverflow.com/questions/16614410/android-studio-checkout-github-error-createprocess-2-windows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_testingjson);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getdata=(Button) findViewById(R.id.getdata);
        hmd=new HM_details5(this);
        cast=(TextView)findViewById(R.id.cast);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        userid = prefs.getString("userid", " ");//"No name defined" is the default value.
        passsword = prefs.getString("password", " ");



        getdata.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View v) {


            }
        });
       // calljson();
        new JSONTask().execute("http://kasmmobileapp.empoweru.in/user_login/?username="+userid+"&password="+passsword);
    }
public void calljson(){


    new JSONTask().execute("http://kasmmobileapp.empoweru.in/user_login/?username="+userid+"&password="+passsword);

}
    //    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public  class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream, "iso-8859-1"));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //return buffer.toString();
                String finalJson = buffer.toString();
                StringBuffer loopeddata = new StringBuffer();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray1 = parentObject.getJSONArray("menu_list");
                JSONArray parentArray3 = parentObject.getJSONArray("rate_list");
                JSONArray parentArray2 = parentObject.getJSONArray("user_details");
                JSONArray parentArray4 = parentObject.getJSONArray("teacher");
                JSONArray parentArray5 = parentObject.getJSONArray("slot_list");
                JSONArray parentArray6 = parentObject.getJSONArray("school_detail");
                JSONArray parentArray7 = parentObject.getJSONArray("data");
                JSONArray parentArray8 = parentObject.getJSONArray("class_list");
                JSONArray parentArray9 = parentObject.getJSONArray("helpline");
                JSONArray parentArray10 = parentObject.getJSONArray("phone");
                //String str1= parentArray1.getJSONObject(0).getString("menu");
                //String str2 =parentArray3.getJSONObject(0).getString("e_fund_deduction");
                //String str3=parentArray2.getJSONObject(0).getString("name");
                // str1=str1+str2+str3+"555555";
                // return  str1;
              for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    String number = finalobject.getString("number");
                    hmd.insertNotephone(number);
                    loopeddata.append("phone  "+number + "\n");
                  //  Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
               for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");

                    hmd.insertNotehelpline(helpline_no,helpline_name);
                    loopeddata.append("helpline   "+helpline_no + "..." + helpline_name + "\n");
                   // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray4.length(); i++) {
                    JSONObject finalobject = parentArray4.getJSONObject(i);
                    int teacher_id = finalobject.getInt("teacher_id");
                    String teachername = finalobject.getString("teacher_name");
                    String designation = finalobject.getString("designation");
                    int gender = finalobject.getInt("gender");
                    String contact = finalobject.getString("contact");
                    String role = finalobject.getString("role");
                    hmd.insertNoteteacher(teacher_id,teachername,designation,gender,contact,role);
                    loopeddata.append("teacher"+teacher_id + "..." + teachername + "\n");
                   // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
                for (int i = 0; i < parentArray6.length(); i++) {
                    JSONObject finalobject = parentArray6.getJSONObject(i);
                    String is_coed = finalobject.getString("is_coed");
                    String school_category = finalobject.getString("school_category");
                    String school_name = finalobject.getString("school_name");
                    String cluster = finalobject.getString("cluster");
                    String dise_code = finalobject.getString("dise_code");
                    String control_department = finalobject.getString("control_department");
                    int school_id = finalobject.getInt("school_id");
                    String block = finalobject.getString("block");
                    hmd.insertNoteschooldetail(is_coed,school_category,school_name,cluster,dise_code,control_department,school_id,block);
                    loopeddata.append(school_category + "..." + control_department + "\n");
                   // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray7.length(); i++) {
                    JSONObject finalobject = parentArray7.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int level = finalobject.getInt("level");
                   hmd.insertNotedata(role,user_id,name,level);
                    //dat.insertNotedata(1,2,"mf",3);

                    loopeddata.append("data"+user_id + "..." + name + "\n");
                    //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");
                   hmd.insertNoteclasslist(class_id,class_name,student_count,class_value);
                    //dat.insertNotedata(1,2,"mf",3);

                    loopeddata.append("class_list  "+class_id + "..." + class_name + "\n");
                  //  Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }


                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");
                   hmd.insertNoteslotlist(to_time,slot_id,from_time);
                    loopeddata.append("slot_list  "+to_time + "..." + from_time + "\n");
                   // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
              for (int i = 0; i < parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    int menuid = finalobject.getInt("menu_id");
                    String menuname = finalobject.getString("menu_name");
                    String menunameregional = finalobject.getString("menu_name_regional");
                    hmd.insertNotemenulist(menuid,menuname,menunameregional);
                    loopeddata.append("menu_list"+menuname + "..." + menunameregional + "\n");
                   //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
              for (int i = 0; i < parentArray3.length(); i++) {
                   JSONObject finalobject = parentArray3.getJSONObject(i);
                    int class_type = finalobject.getInt("class_type");
                    String fund_deduction = finalobject.getString("fund_deduction");
                    String rice_deduction = finalobject.getString("rice_deduction");
                    String e_fund_deduction = finalobject.getString("e_fund_deduction");
                    int mdm_food_rate_id=finalobject.getInt("mdm_food_rate_id");
                   hmd.insertNoteratelist(e_fund_deduction,fund_deduction, mdm_food_rate_id, class_type,rice_deduction);
                    loopeddata.append("rate_lisdt   "+e_fund_deduction + "..." + fund_deduction + "\n");
                   //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i <parentArray2.length(); i++) {
                    JSONObject finalobject = parentArray2.getJSONObject(i);
                    String doj = finalobject.getString("doj");
                    String school = finalobject.getString("school");
                    String name = finalobject.getString("name");
                    String degree = finalobject.getString("degree");
                    String dob = finalobject.getString("dob");
                    int gender = finalobject.getInt("gender");
                    String pro_quali = finalobject.getString("pro_quali");
                    String noa = finalobject.getString("noa");
                    String email = finalobject.getString("email");
                    String cluster = finalobject.getString("cluster");
                    String designation = finalobject.getString("designation");
                    String contact = finalobject.getString("contact");
                    String working_status = finalobject.getString("working_status");
                    String village = finalobject.getString("village");
                    String appointed_for = finalobject.getString("appointed_for");
                    String panchayat = finalobject.getString("panchayat");
                    Long adhaar = finalobject.getLong("adhaar");
                    String block = finalobject.getString("block");
                    //   String appointed_for = finalobject.getString("appointed_for");
                    hmd.insertNoteuserdetails(doj, school, name, degree, dob, gender, pro_quali, noa, email, cluster, designation, contact, working_status, village, appointed_for, panchayat, adhaar, block);
                    loopeddata.append("user_details   "+doj+ school+ name+ degree+ dob+ gender+ pro_quali+ noa+ email+ cluster+designation+ contact+ working_status+village+ appointed_for+ panchayat+ adhaar+ block);
                    //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
                return loopeddata.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {

                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  cast.setText(s);
        // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }

}


