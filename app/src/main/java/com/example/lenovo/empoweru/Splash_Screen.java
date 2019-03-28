package com.example.lenovo.empoweru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {
    static SharedPreferences sharedPreferences;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                 WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Splash_Screen.this);
        setContentView(R.layout.activity_splash__screen);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(android.os.Build.VERSION.SDK_INT >= 23) {
            if(checkAndRequestPermissions()) {
                callToRun();
            }


        }else{
            callToRun();
        }
    }

    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        int locationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    private void callToRun() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (Splash_Screen.getPreferences("userlogin","").equalsIgnoreCase("5")){
                    Intent i=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);
                    finish();
                }
              else  if (Splash_Screen.getPreferences("userlogin","").equalsIgnoreCase("4")){
                    Intent i=new Intent(getApplicationContext(),CRP_nav_d.class);
                    startActivity(i);
                    finish();
                }
                else  if (Splash_Screen.getPreferences("userlogin","").equalsIgnoreCase("3")){
                    Intent i=new Intent(getApplicationContext(),BLOCK_nav_d.class);
                    startActivity(i);
                    finish();
                }
                else  if (Splash_Screen.getPreferences("userlogin","").equalsIgnoreCase("2")){
                    Intent i=new Intent(getApplicationContext(),District_nav_d.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }


        };
        Timer t = new Timer();
        t.schedule(task, 1000);
    }

    public static void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getPreferences(String key, String val) {
        String value = sharedPreferences.getString(key, val);
        return value;
    }

}
