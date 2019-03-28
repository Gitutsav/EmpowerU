package com.example.lenovo.empoweru;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovo.empoweru.leve4.user_details4;

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
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    Button login;
    Intent intent;
    EditText uid, pwrd;
    String usid, pawrd;
    private final String URL = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt";
    private TextView tvData, rdtest;
    private ListView lvMovies;
    Person person;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button getdata;
    private ProgressDialog dialog;
    TextView cast, story, direction, duration;
    //   private NotesAdapter mAdapter;

    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;
    TextView textview;
    Vibrator v;
    String username;
    int level;
    AlertDialog.Builder builder1;
    private LinearLayout empoweruweb;
    private HM_import_data_to_sqlite hidts;
    private HM_details5 hmd;
    private Block_details3 bd;
    private user_details4 ud;
    private District_details2 dd;
    private String designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
  //   Intent intent = new Intent(LoginActivity.this, sendingSMS.class);
  //  startActivity(intent);
        //deleteAppData();

        uid = (EditText) findViewById(R.id.editText2);
        pwrd = (EditText) findViewById(R.id.editText3);

        cast = (TextView) findViewById(R.id.cast);

        getdata = (Button) findViewById(R.id.getdata);
        hmd = new HM_details5(this);
        bd = new Block_details3(this);
        ud=new user_details4(this);
        dd=new District_details2(this);
        // getSupportActionBar().hide();
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TelephonyManager tMgr = (TelephonyManager) LoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String mPhoneNumber = tMgr.getLine1Number();
        // Toast.makeText(LoginActivity.this,"ssrg"+mPhoneNumber,Toast.LENGTH_LONG).show();
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccounts();
        String actype = "", acname;

       /* for (Account ac : accounts) {
             acname = ac.name;
             actype = ac.type;
            // Take your time to look at all available accounts
            System.out.println("Accounts : " + acname + ", " + actype);
            Toast.makeText(LoginActivity.this,"Accounts : " + acname + ", " + actype,Toast.LENGTH_LONG).show();
        }
    /*    if(actype.equals("com.whatsapp")){
            String phoneNumber = ac.name;
            Toast.makeText(LoginActivity.this,"ssrg"+mPhoneNumber,Toast.LENGTH_LONG).show();
        }*/
        empoweruweb = (LinearLayout) findViewById(R.id.empoweruweb);
        empoweruweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.empoweru.in/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        checkConnection();


     /*   if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }*/
        // uid.setText("687938626585" );
        //  pwrd.setText( "687938626585");

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setCancelable(false);
                dialog.setMessage("Loading your profile data , please wait..");
                dialog.show();
                Log.d("Login Pressed", "In Login onCLick Listener");
                usid = uid.getText().toString().trim();
                pawrd = pwrd.getText().toString().trim();
                v.vibrate(100);//0.1 sec
                Boolean isconnected = ConnectivityReceiver.isConnected();
                if (usid.isEmpty() || pawrd.isEmpty()) {
                    dialog.cancel();
                    builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setTitle("Enter Login Credentials");
                    //builder1.setMessage("Connect tO N");
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else {
                    //checkConnection();
                   if (isconnected) {
                        //dialog.cancel();
                        // Intent intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
                        //startActivity(intent);
                        // OnLogin();
                        new JSONTask().execute("http://kasmmobileapp.empoweru.in/user_login/?username=" + usid + "&password=" + pawrd);
                    } else if (isconnected == false) {
                        dialog.cancel();
                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setTitle("Check Your Internet Connection!!");
                        //builder1.setMessage("Connect tO N");
                        builder1.setCancelable(false);

                        builder1.setPositiveButton(
                                "ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // startActivity(new Intent(view.getContext(),BlockTabbedteacher.class));
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();


                    }

                }
            }
            // intent.putExtra("userid", usid);
            //intent.putExtra("passwrd", pawrd);
            //  startActivity(intent);

        });
    }
    private void deleteAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

        } catch (Exception e) {
            e.printStackTrace();
        } }
    public void OnLogin() {
        //String username = UsernameEt.getText().toString();
        //String password = PasswordEt.getText().toString();
        ArrayList<String>  res1=new ArrayList<>();
        res1.add("axxx");res1.add("b");
        res1.add("c"); res1.add("d"); res1.add("e");
        ArrayList<String>  res2=new ArrayList<>();
        res2.add("aa");res2.add("bb");
        res2.add("cc"); res2.add("dd"); res2.add("ee");
        ArrayList<String>  res3=new ArrayList<>();
        res3.add("aaa");res3.add("bbb");
        res3.add("ccc");res3.add("ddd"); res3.add("eee");
        String type = "login";
        BackgroundWorker backgroundWorker2 = new BackgroundWorker(getApplicationContext());
        backgroundWorker2.execute(type,"utsav","gupta","987654122","android");
    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            new JSONTask().execute("http://kasmmobileapp.empoweru.in/user_login/?username=" + usid + "&password=" + pawrd);
            color = Color.WHITE;
        } else {
            dialog.cancel();
            builder1 = new AlertDialog.Builder(LoginActivity.this);
            builder1.setTitle("Check Your Internet Connection!!");
            //builder1.setMessage("Connect tO N");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // startActivity(new Intent(view.getContext(),BlockTabbedteacher.class));
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

   /*      Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();

        // TextView textView = (TextView) findViewById(android.support.design.R.id.snackbar_text);
        //  textView.setTextColor(color);
          snackbar.show();*/
    }
    public void looogin(View v){
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading your profile data , please wait..");
        dialog.show();
      //  Log.d("Login Pressed", "In Login onCLick Listener");
        usid = uid.getText().toString().trim();
        pawrd = pwrd.getText().toString().trim();
    //    v.vibrate();//0.1 sec
        Boolean isconnected = ConnectivityReceiver.isConnected();
        if (usid.isEmpty() || pawrd.isEmpty()) {
            dialog.cancel();
            builder1 = new AlertDialog.Builder(LoginActivity.this);
            builder1.setTitle("Enter Login Credentials");
            //builder1.setMessage("Connect tO N");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            if (isconnected) {
                //dialog.cancel();
                // Intent intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
                //startActivity(intent);
                // OnLogin();
                new JSONTask().execute("http://kasmmobileapp.empoweru.in/user_login/?username=" + usid + "&password=" + pawrd);
            } else if (isconnected == false) {
                dialog.cancel();
                builder1 = new AlertDialog.Builder(LoginActivity.this);
                builder1.setTitle("Check Your Internet Connection!!");
                //builder1.setMessage("Connect tO N");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // startActivity(new Intent(view.getContext(),BlockTabbedteacher.class));
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        }

    }
    public void empoweruu(View v){
        empoweruweb = (LinearLayout) findViewById(R.id.empoweruweb);
        empoweruweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.empoweru.in/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    public class JSONTask extends AsyncTask<String, String, String> {

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
                //StringBuffer loopeddata = new StringBuffer();
                String loopeddata = "";
                JSONObject parentObject = new JSONObject(finalJson);
                String status = parentObject.getString("status");
                String message = parentObject.getString("message");
                if (status.equals("Success")) {
                    JSONArray parentArray5 = parentObject.getJSONArray("data");
                    JSONArray parentArray6 = parentObject.getJSONArray("user_details");

                    for (int i = 0; i < parentArray6.length(); i++) {
                        JSONObject finalobject = parentArray6.getJSONObject(i);
                        designation = finalobject.getString("designation");
                    }
                    for (int i = 0; i < parentArray5.length(); i++) {
                        JSONObject finalobject = parentArray5.getJSONObject(i);
                        username = finalobject.getString("name");
                        level = finalobject.getInt("level");


                        loopeddata = "s" + String.valueOf(level) + message;
                    }
                } else if (status.equals("Failed")) {
                    loopeddata = "f" + message;
                }

                return loopeddata;


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
            char first_char = s.charAt(0);
            char second_char = s.charAt(1);
            if (first_char == 's') {
                //   cast.setText(s);
                if (second_char == '5') {
                    SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("level", designation);
                    editor.putString("levell", String.valueOf(second_char));
                    editor.putString("name", username);
                    editor.putString("userid", usid);
                    editor.putString("password", pawrd);
                    editor.apply();
                  //  http://smartschooldemo.empoweru.in/user_login_data/?username=703290457258&password=703290457258
                    new JSONTaskHM().execute("http://smartschooldemo.empoweru.in/user_login_data/?username=" + usid + "&password=" + pawrd);

                    intent = new Intent(LoginActivity.this, Dashboard.class);
                    //  Toast.makeText(LoginActivity.this,s+"HEAD MASTER",Toast.LENGTH_LONG).show();



                } else if (second_char == '4') {
                    SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("level", designation);
                    editor.putString("name", username);
                    editor.putString("levell", String.valueOf(second_char));
                    editor.putString("userid", usid);
                    editor.putString("password", pawrd);
                    editor.apply();
                    new JSONTaskCRP().execute("http://smartschooldemo.empoweru.in/user_login_data/?username=" + usid + "&password=" + pawrd);
                    intent = new Intent(LoginActivity.this, CRP_nav_d.class);
                    // Toast.makeText(LoginActivity.this,s+"C.R.P.",Toast.LENGTH_LONG).show();

                } else if (second_char == '3') {
                    SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("level", designation);
                    editor.putString("name", username);
                    editor.putString("levell", String.valueOf(second_char));
                    editor.putString("userid", usid);
                    editor.putString("password", pawrd);
                    editor.apply();

                    new JSONTaskBlock().execute("http://smartschooldemo.empoweru.in/user_login_data/?username=" + usid + "&password=" + pawrd);
                    intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
                    //   Toast.makeText(LoginActivity.this,s+"BLOCK",Toast.LENGTH_LONG).show();

                }
                else if (second_char == '2') {
                    SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("level", designation);
                    editor.putString("name", username);
                    editor.putString("userid", usid);
                    editor.putString("levell", String.valueOf(second_char));
                    editor.putString("password", pawrd);
                    editor.apply();
                    new JSONTaskDistrict().execute("http://smartschooldemo.empoweru.in/user_login_data/?username=" + usid + "&password=" + pawrd);
                    intent = new Intent(LoginActivity.this, District_nav_d.class);
                    //   Toast.makeText(LoginActivity.this,s+"BLOCK",Toast.LENGTH_LONG).show();
                    // dialog.cancel();

                    //   dialog.cancel();
                }
            } else if (first_char == 'f') {
                dialog.cancel();
                builder1 = new AlertDialog.Builder(LoginActivity.this);
                String msg = s.substring(1);
                builder1.setTitle(msg);
                //builder1.setMessage("Connect tO N");
                builder1.setCancelable(false);
                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }

    public class JSONTaskHM extends AsyncTask<String, String, String> {

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
                JSONArray parentArray11 = parentObject.getJSONArray("leave");
                JSONArray parentArray12 = parentObject.getJSONArray("question_category");
                //String str1= parentArray1.getJSONObject(0).getString("menu");
                //String str2 =parentArray3.getJSONObject(0).getString("e_fund_deduction");
                //String str3=parentArray2.getJSONObject(0).getString("name");
                // str1=str1+str2+str3+"555555";
                // return  str1;
                //  hmd.onClear();

                hmd.insertNotephone("a");
                hmd.insertNotehelpline("a","a" );
                hmd.insertNoteschooldetail("d","f" ,"d" ,"d" , "h","g",1,"t");
                hmd.insertNoteuserdetails("d","d" , "df", "ty", "d", 1, "d", "","t" ,"yt","k","yt","ty","h","u","yu",1,"hy");
                hmd.insertNoteteacher(1,"d" , "d", 1, "d", "ede");
                hmd.insertNoteslotlist("d", 2, "d");
                hmd.insertNotedata(1,1 , "d",1);
                hmd.insertNoteclasslist(1,"ed" ,2 , 2 );
                hmd.insertNotemenulist(1, "TG","h" );
                hmd.insertNoteratelist("H", "h", 1, 1, "h");
                hmd.insertNoteleave("l",1 );
                hmd.insertNotequestioncategory3("k",1 );
                hmd.deleteData("phone");
                hmd.deleteData("helpline");
                hmd.deleteData("teacher");
                hmd.deleteData("school_detail");
                hmd.deleteData("data");
                hmd.deleteData("class_list");
                hmd.deleteData("slot_list");
                hmd.deleteData("menu_list");
                hmd.deleteData("user_details");
                hmd.deleteData("rate_list");
                hmd.deleteData("leave");
                hmd.deleteData("question_category");
                for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    String number = finalobject.getString("number");

                    hmd.insertNotephone(number);
                   // loopeddata.append("phone  " + number + "\n");
                    //  Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }
                for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");
                    hmd.insertNotequestioncategory3(qc, qc_id);
                   // loopeddata.append(qc + "..." + qc_id + "\n");
                }
                for (int i = 0; i < parentArray11.length(); i++) {
                    JSONObject finalobject = parentArray11.getJSONObject(i);
                    String leave_type = finalobject.getString("leave_type");
                    int leave_type_id = finalobject.getInt("leave_type_id");
                    hmd.insertNoteleave(leave_type, leave_type_id);
                    loopeddata.append(leave_type + "..." + leave_type_id + "\n");
                }
                for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");
                    hmd.insertNotehelpline(helpline_no, helpline_name);
                   // loopeddata.append("helpline   " + helpline_no + "..." + helpline_name + "\n");
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


                    hmd.insertNoteteacher(teacher_id, teachername, designation, gender, contact, role);
                   // loopeddata.append("teacher" + teacher_id + "..." + teachername + "\n");
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


                    hmd.insertNoteschooldetail(is_coed, school_category, school_name, cluster, dise_code, control_department, school_id, block);
                  //  loopeddata.append(school_category + "..." + control_department + "\n");
                    // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray7.length(); i++) {
                    JSONObject finalobject = parentArray7.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int level = finalobject.getInt("level");

                    hmd.insertNotedata(role, user_id, name, level);
                    //dat.insertNotedata(1,2,"mf",3);

                    //loopeddata.append("data" + user_id + "..." + name + "\n");
                    //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");


                    hmd.insertNoteclasslist(class_id, class_name, student_count, class_value);
                    //dat.insertNotedata(1,2,"mf",3);

                  //  loopeddata.append("class_list  " + class_id + "..." + class_name + "\n");
                    //  Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }


                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");

                    hmd.insertNoteslotlist(to_time, slot_id, from_time);


                   // loopeddata.append("slot_list  " + to_time + "..." + from_time + "\n");
                    // Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                    for (int i = 0; i < parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    int menuid = finalobject.getInt("menu_id");
                    String menuname = finalobject.getString("menu_name");
                    String menunameregional = finalobject.getString("menu_name_regional");


                    hmd.insertNotemenulist(menuid, menuname, menunameregional);

                   // loopeddata.append("menu_list" + menuname + "..." + menunameregional + "\n");
                    //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray3.length(); i++) {
                    JSONObject finalobject = parentArray3.getJSONObject(i);
                    int class_type = finalobject.getInt("class_type");
                    String fund_deduction = finalobject.getString("fund_deduction");
                    String rice_deduction = finalobject.getString("rice_deduction");
                    String e_fund_deduction = finalobject.getString("e_fund_deduction");
                    int mdm_food_rate_id = finalobject.getInt("mdm_food_rate_id");


                    hmd.insertNoteratelist(e_fund_deduction, fund_deduction, mdm_food_rate_id, class_type, rice_deduction);
                    loopeddata.append("rate_lisdt   " + e_fund_deduction + "..." + fund_deduction + "\n");
                    //Toast.makeText(HM_import_data_to_sqlite.this,loopeddata.toString(),Toast.LENGTH_LONG).show();
                }

                for (int i = 0; i < parentArray2.length(); i++) {
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
                   // loopeddata.append("user_details   " + doj + school + name + degree + dob + gender + pro_quali + noa + email + cluster + designation + contact + working_status + village + appointed_for + panchayat + adhaar + block);
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
            dialog.cancel();
            Splash_Screen.savePreferences("userlogin","5");
            startActivity(intent);

            //  cast.setText(s);
             //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    public class JSONTaskBlock extends AsyncTask<String, String, String> {

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
                JSONArray parentArray15 = parentObject.getJSONArray("block_details");
                bd.insertNotephone3("a");
                bd.insertNotehelpline3("a","a" );
                bd.insertNotehosteloptionlist3(2,2 ,"a" , 2,"a" ,1 );
                bd.insertNoteoptionlist3(1, "a", 2, "s",1 , 1);
                bd.insertNotequestioncategory3("s", 1);
                bd.insertNoteschooldetail3("d","f" ,"d" ,"d" , "d", 1, "d",2 ,"d" );
                bd.inseruserdetails3("d","d" , "df", 1, "d", "d", "d", 1,"d" );
                bd.insertNoteteacher3(1,"d" , "d", 1, "d", "ede", 1);
                bd.insertNoteslotlist3("d", 2, "d");
                bd.inserthostelmonitotlist3(1,2 ,2 , 2, "d",2 , "2") ;
                bd.insertNotedata3(1,1 , "d",1 , 1);
                bd.insertNoteclusterdetails3("f",2 , 1);
                bd.insertNoteclasslist3(1,"ed" ,2 , 2,2 );
                bd.insertNoteblockdetails3("d",7 );
                bd.insertmonitotdetail3("a",1 ,1 , 1, "h", 1, 1,1);
                bd.deleteData("phonel3");
                bd.deleteData("question_categoryl3");
                bd.deleteData("hostel_monitot_listl3");
                bd.deleteData("hostel_option_listl3");
                bd.deleteData("block_detailsl3");
                bd.deleteData("helplinel3");
                bd.deleteData("monitot_listl3");
                bd.deleteData("option_listl3");
                bd.deleteData("cluster_detailsl3");
                bd.deleteData("teacherl3");
                bd.deleteData("school_detaill3");
                bd.deleteData("datal3");
                bd.deleteData("class_listl3");
                bd.deleteData("slot_listl3");
                bd.deleteData("user_detailsl3");

                for (int i = 0; i < parentArray1.length(); i++) {
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
                    bd.inseruserdetails3(name, designation, dob, gender, email, contact, role, adhaar, block);
                    loopeddata.append(name + designation + dob + gender + email + contact + role + adhaar + block);
                }
                for (int i = 0; i < parentArray3.length(); i++) {
                    JSONObject finalobject = parentArray3.getJSONObject(i);
                    String mq_name = finalobject.getString("mq_name");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int opg_id = finalobject.getInt("opg_id");
                    String mq_name_regional = finalobject.getString("mq_name_regional");
                    int q_no = finalobject.getInt("q_no");
                    int question_type = finalobject.getInt("question_type");
                    int mq_id = finalobject.getInt("mq_id");
                    bd.insertmonitotdetail3(mq_name, is_image, input_type_id, opg_id, mq_name_regional, q_no, question_type, mq_id);
                    loopeddata.append(mq_name + is_image + input_type_id + opg_id + mq_name_regional);
                }
                for (int i = 0; i < parentArray13.length(); i++) {
                    JSONObject finalobject = parentArray13.getJSONObject(i);
                    int hmq_id = finalobject.getInt("hmq_id");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int hq_no = finalobject.getInt("hq_no");
                    String hmq_name_regional = finalobject.getString("hmq_name_regional");
                    int opg_id = finalobject.getInt("opg_id");
                    String hmq_name = finalobject.getString("hmq_name");
                    bd.inserthostelmonitotlist3(hmq_id, is_image, input_type_id, hq_no, hmq_name_regional, opg_id, hmq_name);
                    loopeddata.append(hmq_name + is_image + input_type_id + opg_id + hmq_name_regional);
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
                    bd.insertNoteteacher3(teacher_id, teachername, designation, gender, contact, role, school_id);
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
                    bd.insertNoteoptionlist3(opc_id, opc_name_regional, input_type_id, option_choices, opg_id, mq_id);
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
                    bd.insertNotehosteloptionlist3(hmq_id, opc_id, opc_name_regional, input_type_id, option_choices, opg_id);
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
                    bd.insertNoteschooldetail3(is_coed, school_category, school_name, cluster, dise_code, cluster_id, control_department, school_id, block);
                    loopeddata.append(school_category + "..." + control_department + "\n");
                }
                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int perm = finalobject.getInt("perm");
                    int level = finalobject.getInt("level");
                    bd.insertNotedata3(role, user_id, name, perm, level);
                    loopeddata.append(user_id + "..." + name + "\n");
                }
                for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");

                    bd.insertNotequestioncategory3(qc, qc_id);
                    loopeddata.append(qc + "..." + qc_id + "\n");
                }
                for (int i = 0; i < parentArray15.length(); i++) {
                    JSONObject finalobject = parentArray15.getJSONObject(i);
                    String block_name = finalobject.getString("block_name");
                    int block_id = finalobject.getInt("block_id");

                    bd.insertNoteblockdetails3(block_name, block_id);
                    loopeddata.append(block_id + "..." + block_name + "\n");
                }
                for (int i = 0; i < parentArray4.length(); i++) {
                    JSONObject finalobject = parentArray4.getJSONObject(i);
                    String number = finalobject.getString("number");
                    bd.insertNotephone3(number);
                    loopeddata.append(number + "\n");
                }

                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    String cluster_name = finalobject.getString("cluster_name");
                    int cluster_id = finalobject.getInt("cluster_id");
                    int block_id = finalobject.getInt("block_id");
                    bd.insertNoteclusterdetails3(cluster_name, cluster_id, block_id);
                    loopeddata.append(cluster_name + "..." + cluster_id + "\n");
                }
                for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");

                    bd.insertNotehelpline3(helpline_no, helpline_name);
                    loopeddata.append(helpline_no + "..." + helpline_name + "\n");
                }
                for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");
                    int school_id = finalobject.getInt("school_id");
                    bd.insertNoteclasslist3(class_id, class_name, student_count, class_value, school_id);                //dat.insertNotedata(1,2,"mf",3);
                    loopeddata.append(class_id + "..." + class_name + "\n");
                }


                for (int i = 0; i < parentArray2.length(); i++) {
                    JSONObject finalobject = parentArray2.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");
                    bd.insertNoteslotlist3(to_time, slot_id, from_time);
                    loopeddata.append(to_time + "..." + from_time + "\n");
                }

loopeddata.append("vvvv");
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
            dialog.cancel();
            Splash_Screen.savePreferences("userlogin","3");
            startActivity(intent);

            //   block_details.setText(s);
            // Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
    public class JSONTaskDistrict extends AsyncTask<String, String, String> {

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
                JSONArray parentArray15 = parentObject.getJSONArray("block_details");

                dd.insertNotephone3("a");
                dd.insertNotehelpline3("a","a" );
                dd.insertNotehosteloptionlist3(2,2 ,"a" , 2,"a" ,1 );
                dd.insertNoteoptionlist3(1, "a", 2, "s",1 , 1);
                dd.insertNotequestioncategory3("s", 1);
                dd.insertNoteschooldetail3("d","f" ,"d" ,"d" , "d", 1, "d",2 ,"d" );
                dd.inseruserdetails3("d","d" , "df", 1, "d", "d", "d", "",1 );
                dd.insertNoteteacher3(1,"d" , "d", 1, "d", "ede", 1);
                dd.insertNoteslotlist3("d", 2, "d");
                dd.inserthostelmonitotlist3(1,2 ,2 , 2, "d",2 , "2") ;
                dd.insertNotedata3(1,1 , "d",1 , 1);
                dd.insertNoteclusterdetails3("f",2 , 1);
                dd.insertNoteclasslist3(1,"ed" ,2 , 2,2 );
                dd.insertNoteblockdetails3("d",7 );
                dd.insertmonitotdetail3("a",1 ,1 , 1, "h", 1, 1,1);
                dd.deleteData("phonel3");
                dd.deleteData("question_categoryl3");
                dd.deleteData("hostel_monitot_listl3");
                dd.deleteData("hostel_option_listl3");
                dd.deleteData("block_detailsl3");
                dd.deleteData("helplinel3");
                dd.deleteData("monitot_listl3");
                dd.deleteData("option_listl3");
                dd.deleteData("cluster_detailsl3");
                dd.deleteData("teacherl3");
                dd.deleteData("school_detaill3");
                dd.deleteData("datal3");
                dd.deleteData("class_listl3");
                dd.deleteData("slot_listl3");
                dd.deleteData("user_detailsl3");

                for (int i = 0; i < parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    String name = finalobject.getString("name");
                    String designation = finalobject.getString("designation");
                    String dob = finalobject.getString("dob");
                    int gender = finalobject.getInt("gender");
                    String email = finalobject.getString("email");
                    String contact = finalobject.getString("contact");
                    String role = finalobject.getString("role");
                    Long adhaar = finalobject.getLong("adhaar");
                    String district = finalobject.getString("district");
                    dd.inseruserdetails3(name, designation, dob, gender, email, contact, role,district, adhaar);
                    loopeddata.append(name + designation + dob + gender + email + contact + role + adhaar + district);
                }
                for (int i = 0; i < parentArray3.length(); i++) {
                    JSONObject finalobject = parentArray3.getJSONObject(i);
                    String mq_name = finalobject.getString("mq_name");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int opg_id = finalobject.getInt("opg_id");
                    String mq_name_regional = finalobject.getString("mq_name_regional");
                    int q_no = finalobject.getInt("q_no");
                    int question_type = finalobject.getInt("question_type");
                    int mq_id = finalobject.getInt("mq_id");
                    dd.insertmonitotdetail3(mq_name, is_image, input_type_id, opg_id, mq_name_regional, q_no, question_type, mq_id);
                    loopeddata.append(mq_name + is_image + input_type_id + opg_id + mq_name_regional);
                }
                for (int i = 0; i < parentArray13.length(); i++) {
                    JSONObject finalobject = parentArray13.getJSONObject(i);
                    int hmq_id = finalobject.getInt("hmq_id");
                    int is_image = finalobject.getInt("is_image");
                    int input_type_id = finalobject.getInt("input_type_id");
                    int hq_no = finalobject.getInt("hq_no");
                    String hmq_name_regional = finalobject.getString("hmq_name_regional");
                    int opg_id = finalobject.getInt("opg_id");
                    String hmq_name = finalobject.getString("hmq_name");
                    dd.inserthostelmonitotlist3(hmq_id, is_image, input_type_id, hq_no, hmq_name_regional, opg_id, hmq_name);
                    loopeddata.append(hmq_name + is_image + input_type_id + opg_id + hmq_name_regional);
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
                    dd.insertNoteteacher3(teacher_id, teachername, designation, gender, contact, role, school_id);
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
                    dd.insertNoteoptionlist3(opc_id, opc_name_regional, input_type_id, option_choices, opg_id, mq_id);
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
                    dd.insertNotehosteloptionlist3(hmq_id, opc_id, opc_name_regional, input_type_id, option_choices, opg_id);
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
                    dd.insertNoteschooldetail3(is_coed, school_category, school_name, cluster, dise_code, cluster_id, control_department, school_id, block);
                    loopeddata.append(school_category + "..." + control_department + "\n");
                }
                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int perm = finalobject.getInt("perm");
                    int level = finalobject.getInt("level");
                    dd.insertNotedata3(role, user_id, name, perm, level);
                    loopeddata.append(user_id + "..." + name + "\n");
                }
                for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");

                    dd.insertNotequestioncategory3(qc, qc_id);
                    loopeddata.append(qc + "..." + qc_id + "\n");
                }
                for (int i = 0; i < parentArray15.length(); i++) {
                    JSONObject finalobject = parentArray15.getJSONObject(i);
                    String block_name = finalobject.getString("block_name");
                    int block_id = finalobject.getInt("block_id");

                    dd.insertNoteblockdetails3(block_name, block_id);
                    loopeddata.append(block_id + "..." + block_name + "\n");
                }
                for (int i = 0; i < parentArray4.length(); i++) {
                    JSONObject finalobject = parentArray4.getJSONObject(i);
                    String number = finalobject.getString("number");
                    dd.insertNotephone3(number);
                    loopeddata.append(number + "\n");
                }

                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    String cluster_name = finalobject.getString("cluster_name");
                    int cluster_id = finalobject.getInt("cluster_id");
                    int block_id = finalobject.getInt("block_id");
                    dd.insertNoteclusterdetails3(cluster_name, cluster_id, block_id);
                    loopeddata.append(cluster_name + "..." + cluster_id + "\n");
                }
                for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");

                    dd.insertNotehelpline3(helpline_no, helpline_name);
                    loopeddata.append(helpline_no + "..." + helpline_name + "\n");
                }
                for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");
                    int school_id = finalobject.getInt("school_id");
                    dd.insertNoteclasslist3(class_id, class_name, student_count, class_value, school_id);                //dat.insertNotedata(1,2,"mf",3);
                    loopeddata.append(class_id + "..." + class_name + "\n");
                }


                for (int i = 0; i < parentArray2.length(); i++) {
                    JSONObject finalobject = parentArray2.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");
                    dd.insertNoteslotlist3(to_time, slot_id, from_time);
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
            dialog.cancel();
            Splash_Screen.savePreferences("userlogin","2");
            startActivity(intent);
            //   block_details.setText(s);
            // Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
    public class JSONTaskCRP extends AsyncTask<String, String, String> {

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

                ud.insertNotephone4("a");
                ud.insertNotehelpline4("a","a" );
                ud.insertNoteoptionlist4(1, "a", 2, "s",1 , 1);
                ud.insertNotequestioncategory4("s", 1);
                ud.insertNoteschooldetail4("d","f" ,"d" ,"d" , "d", 1, "d",2 ,"d" );
                ud.inseruserdetails4("d","d" , "df", 1, "d", "d", "d", "",1 ,"yt");
                ud.insertNoteteacher4(1,"d" , "d", 1, "d", "ede", 1);
                ud.insertNoteslotlist4("d", 2, "d");
                ud.insertNotedata4(1,1 , "d",1);
                ud.insertNoteclusterdetails4("f",2 );
                ud.insertNoteclasslist4(1,"ed" ,2 , 2,2 );
                ud.insertmonitotdetail4("f", 1, 1, 1, "j", 1, 1, 1);
                ud.deleteData("phonel4");
                ud.deleteData("question_categoryl4");
                ud.deleteData("helplinel4");
                ud.deleteData("monitot_listl4");
                ud.deleteData("option_listl4");
                ud.deleteData("cluster_detailsl4");
                ud.deleteData("teacherl4");
                ud.deleteData("school_detaill4");
                ud.deleteData("datal4");
                ud.deleteData("class_listl4");
                ud.deleteData("slot_listl4");
                ud.deleteData("user_detailsl4");

                for (int i = 0; i <parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    String name = finalobject.getString("name");
                    String designation = finalobject.getString("designation");
                    String dob = finalobject.getString("dob");
                    int gender = finalobject.getInt("gender");
                    String email = finalobject.getString("email");
                    String cluster = finalobject.getString("cluster");
                    String contact = finalobject.getString("contact");
                    String role = finalobject.getString("role");
                    Long adhaar = finalobject.getLong("adhaar");
                    String block = finalobject.getString("block");
                    ud.inseruserdetails4( name, designation, dob, gender,  email, cluster,  contact,role, adhaar, block);
                    loopeddata.append(name+designation+ dob+ gender+  email+ cluster+ contact+role+ adhaar+ block);

                }
                //    Toast.makeText(LoginActivity.this,"C.R.P."+loopeddata.toString(),Toast.LENGTH_LONG).show();
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
                    ud.insertmonitotdetail4(mq_name,is_image,input_type_id,opg_id,mq_name_regional,q_no,question_type,mq_id);
                    loopeddata.append(mq_name+is_image+input_type_id+opg_id+mq_name_regional);
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
                    ud.insertNoteteacher4(teacher_id,teachername,designation,gender,contact,role,school_id);
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
                    ud.insertNoteoptionlist4(opc_id,opc_name_regional ,input_type_id ,option_choices , opg_id,mq_id );
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
                    ud.insertNoteschooldetail4(is_coed,school_category,school_name,cluster,dise_code,cluster_id,control_department,school_id,block);
                    loopeddata.append(school_category + "..." + control_department + "\n");
                }
                for (int i = 0; i < parentArray5.length(); i++) {
                    JSONObject finalobject = parentArray5.getJSONObject(i);
                    int role = finalobject.getInt("role");
                    int user_id = finalobject.getInt("user_id");
                    String name = finalobject.getString("name");
                    int level = finalobject.getInt("level");
                    ud.insertNotedata4(role,user_id,name,level);
                    loopeddata.append(user_id + "..." + name + "\n");
                }
                for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");

                    ud.insertNotequestioncategory4(qc,qc_id);
                    loopeddata.append(qc + "..." + qc_id + "\n");
                }
                for (int i = 0; i < parentArray4.length(); i++) {
                    JSONObject finalobject = parentArray4.getJSONObject(i);
                    String number = finalobject.getString("number");
                    ud.insertNotephone4(number);
                    loopeddata.append(number + "\n");
                }

                for (int i = 0; i < parentArray8.length(); i++) {
                    JSONObject finalobject = parentArray8.getJSONObject(i);
                    String cluster_name = finalobject.getString("cluster_name");
                    int cluster_id = finalobject.getInt("cluster_id");

                    ud.insertNoteclusterdetails4(cluster_name,cluster_id);
                    loopeddata.append(cluster_name + "..." + cluster_id + "\n");
                }
                for (int i = 0; i < parentArray9.length(); i++) {
                    JSONObject finalobject = parentArray9.getJSONObject(i);
                    String helpline_no = finalobject.getString("helpline_no");
                    String helpline_name = finalobject.getString("helpline_name");

                    ud.insertNotehelpline4(helpline_no,helpline_name);
                    loopeddata.append(helpline_no + "..." + helpline_name + "\n");
                }
                for (int i = 0; i < parentArray10.length(); i++) {
                    JSONObject finalobject = parentArray10.getJSONObject(i);
                    int class_id = finalobject.getInt("class_id");
                    String class_name = finalobject.getString("class_name");
                    int student_count = finalobject.getInt("student_count");
                    int class_value = finalobject.getInt("class_value");
                    int school_id = finalobject.getInt("school_id");
                    ud.insertNoteclasslist4(class_id,class_name,student_count,class_value,school_id);                //dat.insertNotedata(1,2,"mf",3);
                    loopeddata.append(class_id + "..." + class_name + "\n");
                }


                for (int i = 0; i < parentArray2.length(); i++) {
                    JSONObject finalobject = parentArray2.getJSONObject(i);
                    String to_time = finalobject.getString("to");
                    int slot_id = finalobject.getInt("slot_id");
                    String from_time = finalobject.getString("from");
                    ud.insertNoteslotlist4(to_time,slot_id,from_time);
                    loopeddata.append(to_time + "..." + from_time + "\n");
                }

  /*              for (int i = 0; i < parentArray12.length(); i++) {
                    JSONObject finalobject = parentArray12.getJSONObject(i);
                    String qc = finalobject.getString("qc");
                    int qc_id = finalobject.getInt("qc_id");

                    ud.insertNotequestioncategory4(qc,qc_id);
                    loopeddata.append(qc + "..." + qc_id + "\n");
                }
*/

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
            dialog.cancel();
            Splash_Screen.savePreferences("userlogin","4");
            startActivity(intent);
            // crpdetails.setText(s);
            // Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
        }
    }
 /*   private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            person = new Person();
            person.setName(etName.getText().toString());
            person.setCountry(etCountry.getText().toString());
            person.setTwitter(etTwitter.getText().toString());

            return POST(urls[0],person);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }
    public static String POST(String url, Person person){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";


            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", person.getName());
            jsonObject.accumulate("country", person.getCountry());
            jsonObject.accumulate("twitter", person.getTwitter());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();


            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }*/
}

