package com.example.lenovo.empoweru;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Block_data_import_to_sqlite extends AppCompatActivity {
    private final String URL = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt";

    Button getdata;
private Block_details3 ud;
    private TextView block_details;
String userid,passsword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_data_import_to_sqlite);
        getdata=(Button) findViewById(R.id.getdata);
        block_details=(TextView) findViewById(R.id.blockdetails);
        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        // String restoredText = prefs.getString("text", null);
        //   if (restoredText != null) {
        userid = prefs.getString("userid", " ");//"No name defined" is the default value.
        passsword = prefs.getString("password", " ");
        ud=new Block_details3(this);
         getdata.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
        new JSONTask1().execute("http://kasmmobileapp.empoweru.in/user_login/?username="+userid+"&password="+passsword);
            }
           });
    }
    public class JSONTask1 extends AsyncTask<String, String, String> {

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
                JSONArray parentArray1 = parentObject.getJSONArray("user_details");
                JSONArray parentArray3 = parentObject.getJSONArray("monitot_list");
                JSONArray parentArray2 = parentObject.getJSONArray("slot_list");
                JSONArray parentArray4 = parentObject.getJSONArray("phone");
                JSONArray parentArray5 = parentObject.getJSONArray("data");
                JSONArray parentArray6 = parentObject.getJSONArray("teacher");
                JSONArray parentArray7 = parentObject.getJSONArray("option_list");
                JSONArray parentArray8 = parentObject.getJSONArray("cluster_details");
                JSONArray parentArray9 = parentObject.getJSONArray("helpline");
                JSONArray parentArray10 = parentObject.getJSONArray("class_list");
                JSONArray parentArray11 = parentObject.getJSONArray("school_detail");
                JSONArray parentArray12 = parentObject.getJSONArray("question_category");
                JSONArray parentArray13 = parentObject.getJSONArray("hostel_monitot_list");
                JSONArray parentArray14 = parentObject.getJSONArray("hostel_option_list");
                JSONArray parentArray15= parentObject.getJSONArray("block_details");
                for (int i = 0; i <parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    String name = finalobject.getString("name");
                    String designation = finalobject.getString("designation");
                    String dob = finalobject.getString("dob");
                    int gender = finalobject.getInt("gender");
                    String email = finalobject.getString("email");
                    String contact = finalobject.getString("contact");
                    String role = finalobject.getString("role");
                    Long adhaar = finalobject.getLong("adhaar");
                    String block = finalobject.getString("block");
                    ud.inseruserdetails3( name, designation, dob, gender,  email,  contact,role, adhaar, block);
                    loopeddata.append(name+designation+ dob+ gender+  email+ contact+role+ adhaar+ block);
                }
                for (int i = 0; i <parentArray3.length(); i++) {
                    JSONObject finalobject = parentArray3.getJSONObject(i);
                    String mq_name = finalobject.getString("mq_name");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int opg_id = finalobject.getInt("opg_id");
                    String mq_name_regional = finalobject.getString("mq_name_regional");
                    int q_no= finalobject.getInt("q_no");
                    int question_type = finalobject.getInt("question_type");
                    int mq_id = finalobject.getInt("mq_id");
                    ud.insertmonitotdetail3(mq_name,is_image,input_type_id,opg_id,mq_name_regional,q_no,question_type,mq_id);
                    loopeddata.append(mq_name+is_image+input_type_id+opg_id+mq_name_regional);
                }
                for (int i = 0; i <parentArray13.length(); i++) {
                    JSONObject finalobject = parentArray13.getJSONObject(i);
                    int hmq_id = finalobject.getInt("hmq_id");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int hq_no= finalobject.getInt("hq_no");
                    String hmq_name_regional = finalobject.getString("hmq_name_regional");
                    int opg_id = finalobject.getInt("opg_id");
                    String hmq_name = finalobject.getString("hmq_name");
                    ud.inserthostelmonitotlist3(hmq_id,is_image,input_type_id,hq_no,hmq_name_regional,opg_id,hmq_name);
                    loopeddata.append(hmq_name+is_image+input_type_id+opg_id+hmq_name_regional);
                }
                for (int i = 0; i < parentArray6.length(); i++) {
                    JSONObject finalobject = parentArray6.getJSONObject(i);
                    int teacher_id = finalobject.getInt("teacher_id");
                    String teachername = finalobject.getString("teacher_name");
                    String designation = finalobject.getString("designation");
                    int gender = finalobject.getInt("gender");
                    String contact = finalobject.getString("contact");
                    String role = finalobject.getString("role");
                    int school_id = finalobject.getInt("school_id");
                    ud.insertNoteteacher3(teacher_id,teachername,designation,gender,contact,role,school_id);
                    loopeddata.append(teacher_id + "..." + teachername + "\n");
                }
                for (int i = 0; i < parentArray7.length(); i++) {
                    JSONObject finalobject = parentArray7.getJSONObject(i);
                    int opc_id = finalobject.getInt("opc_id");
                    String opc_name_regional = finalobject.getString("opc_name_regional");
                    int input_type_id = finalobject.getInt("input_type_id");
                    String option_choices = finalobject.getString("option_choices");
                    int opg_id = finalobject.getInt("opg_id");
                    int mq_id = finalobject.getInt("mq_id");
                    ud.insertNoteoptionlist3(opc_id,opc_name_regional ,input_type_id ,option_choices , opg_id,mq_id );
                    loopeddata.append(opc_name_regional + "..." + option_choices + "\n");
                }
                for (int i = 0; i < parentArray14.length(); i++) {
                    JSONObject finalobject = parentArray14.getJSONObject(i);
                    int opc_id = finalobject.getInt("opc_id");
                    String opc_name_regional = finalobject.getString("opc_name_regional");
                    int input_type_id = finalobject.getInt("input_type_id");
                    String option_choices = finalobject.getString("option_choices");
                    int opg_id = finalobject.getInt("opg_id");
                    int hmq_id = finalobject.getInt("hmq_id");
                    ud.insertNotehosteloptionlist3(hmq_id, opc_id, opc_name_regional, input_type_id, option_choices, opg_id);
                    loopeddata.append(opc_name_regional + "..." + option_choices + "\n");
                }
                for (int i = 0; i < parentArray11.length(); i++) {
                    JSONObject finalobject = parentArray11.getJSONObject(i);
                    String is_coed = finalobject.getString("is_coed");
                    String school_category = finalobject.getString("school_category");
                    String school_name = finalobject.getString("school_name");
                    String cluster = finalobject.getString("cluster");
                    String dise_code = finalobject.getString("dise_code");
                    int cluster_id = finalobject.getInt("cluster_id");
                    String control_department = finalobject.getString("control_department");
                    int school_id = finalobject.getInt("school_id");
                    String block = finalobject.getString("block");
                    ud.insertNoteschooldetail3(is_coed,school_category,school_name,cluster,dise_code,cluster_id,control_department,school_id,block);
                    loopeddata.append(school_category + "..." + control_department + "\n");
                }
                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int perm=finalobject.getInt("perm");
                    int level = finalobject.getInt("level");
                    ud.insertNotedata3(role,user_id,name,perm,level);
                    loopeddata.append(user_id + "..." + name + "\n");
                }
                for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");

                    ud.insertNotequestioncategory3(qc,qc_id);
                    loopeddata.append(qc + "..." + qc_id + "\n");
                }
                for (int i = 0; i < parentArray15.length(); i++) {
                    JSONObject finalobject = parentArray15.getJSONObject(i);
                    String block_name = finalobject.getString("block_name");
                    int block_id = finalobject.getInt("block_id");

                    ud.insertNoteblockdetails3(block_name,block_id);
                    loopeddata.append(block_id + "..." + block_name + "\n");
                }
                for (int i = 0; i < parentArray4.length(); i++) {
                    JSONObject finalobject = parentArray4.getJSONObject(i);
                    String number = finalobject.getString("number");
                    ud.insertNotephone3(number);
                    loopeddata.append(number + "\n");
                }

                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    String cluster_name = finalobject.getString("cluster_name");
                    int cluster_id = finalobject.getInt("cluster_id");
                    int block_id=finalobject.getInt("block_id");
                    ud.insertNoteclusterdetails3(cluster_name,cluster_id,block_id);
                    loopeddata.append(cluster_name + "..." + cluster_id + "\n");
                }
                for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");

                    ud.insertNotehelpline3(helpline_no,helpline_name);
                    loopeddata.append(helpline_no + "..." + helpline_name + "\n");
                }
                for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");
                    int school_id = finalobject.getInt("school_id");
                    ud.insertNoteclasslist3(class_id,class_name,student_count,class_value,school_id);                //dat.insertNotedata(1,2,"mf",3);
                    loopeddata.append(class_id + "..." + class_name + "\n");
                }


                for (int i = 0; i < parentArray2.length(); i++) {
                    JSONObject finalobject = parentArray2.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");
                    ud.insertNoteslotlist3(to_time,slot_id,from_time);
                    loopeddata.append(to_time + "..." + from_time + "\n");
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
            block_details.setText(s);
            Toast.makeText(Block_data_import_to_sqlite.this,s,Toast.LENGTH_LONG).show();
        }
    }

}
