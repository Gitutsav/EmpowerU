package com.example.lenovo.empoweru;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.empoweru.leve4.user_details4;

public class Helpline extends AppCompatActivity {
    private HM_details5 hmd;private Block_details3 bd;
    private District_details2 dd;
    private user_details4 ud;
    TextView t1, t2;
    Cursor helpline;
    ImageButton back;String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        t1 = (TextView) findViewById(R.id.textView33);
        t2 = (TextView) findViewById(R.id.textView44);
        hmd = new HM_details5(getApplicationContext());
        ud=new user_details4(getApplicationContext());
        bd=new Block_details3(getApplicationContext());
        dd=new District_details2(getApplicationContext());
        back=(ImageButton)findViewById(R.id.back);
        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        level= prefs.getString("levell", ""); //0 is the default value.
        //Toast.makeText(getApplicationContext(),level ,Toast.LENGTH_SHORT ).show();
        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level.equals("5")) {
                    startActivity(new Intent(Helpline.this,Dashboard.class));
                }
                else if(level.equals("4")){
                    startActivity(new Intent(Helpline.this,CRP_nav_d.class));
                }
                else if(level.equals("3")){
                    startActivity(new Intent(Helpline.this,BLOCK_nav_d.class));
                }
                else if(level.equals("2")){
                    startActivity(new Intent(Helpline.this,District_nav_d.class));
                }

            }
        });
if(level.equals("5")) {
    helpline = hmd.helpline_data();
}
else if(level.equals("4")){
    helpline = ud.helpline_data();
}
else if(level.equals("3")){
    helpline = bd.helpline_data();
}
else if(level.equals("2")){
    helpline = dd.helpline_data();
}
        helpline.moveToFirst();
        String name = helpline.getString(1);
        String phn = helpline.getString(0);
        t1.setText(name);
        t2.setText(phn);
        t2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String phone_no = t2.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(phone_no));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(Helpline.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
    }
}
