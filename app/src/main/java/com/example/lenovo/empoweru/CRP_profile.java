package com.example.lenovo.empoweru;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.empoweru.leve4.user_details4;

public class CRP_profile extends AppCompatActivity {
    private user_details4 ud;  String nameq;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button navigation;
    TextView doj,school,name,degree,dob,gender,proquali,noa,email,cluster,designation,contact,workingstatus,village,appointedfor,panchayat,adhaar,block;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crp_profile);

     /*   getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String nameq = loginData.getString("level", "");
        int pw = loginData.getInt("idName",0);



   //     Toast.makeText(CRP_profile.this,nameq+"C.R.P."+"\n"+pw,Toast.LENGTH_LONG).show();

        // pd=findViewById(R.id.profiledetails);

        adhaar=(TextView) findViewById(R.id.aadhar);
        dob=(TextView)findViewById(R.id.dob1);
        designation=(TextView)findViewById(R.id.designation);


        cluster=(TextView)findViewById(R.id.cluster);

        gender=(TextView)findViewById(R.id.gender);
        name=(TextView)findViewById(R.id.name);

        contact=(TextView)findViewById(R.id.contact);

        email=(TextView)findViewById(R.id.email);

        block=(TextView)findViewById(R.id.block);
        ud=new user_details4(this);
        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CRP_profile.this,CRP_nav_d.class));



            }
        });
        Cursor res = ud.user_detail_data();
        res.moveToFirst();
        StringBuffer buffer = new StringBuffer();
     //   while (res.moveToNext()) {

            buffer.append("Name :"+ res.getString(0));
            name.setText( res.getString(0));
            buffer.append("Date of Birth:"+ res.getString(2));
            dob.setText("Date of Birth:"+ res.getString(2));
            buffer.append("Gender :"+ res.getInt(3));
            if(res.getInt(3)==1){
                gender.setText("Gender :"+"Male");
            }
            else {
                gender.setText("Gender :" + "Male");
            }
            // gender.setText("Gender :"+ res.getInt(5));


            buffer.append("Email:"+ res.getString(4));
            email.setText("Email:"+ res.getString(4));
            buffer.append("Cluster :"+ res.getString(5));
            cluster.setText("Cluster :"+ res.getString(5));
            buffer.append("Designation :"+ res.getString(1));
            designation.setText("Designation :"+ res.getString(1));
            buffer.append("Contact :"+ res.getString(6));
            contact.setText("Contact :"+ res.getString(6));


            buffer.append("Adhaar:"+ res.getString(8));
            adhaar.setText("Adhaar:"+ res.getString(8));
            buffer.append("Block :"+ res.getString(9));
            block.setText("Block :"+ res.getString(9));
   //     }

        // Show all data
        String showMessage=buffer.toString();
        // email.setText(showMessage+"22");

    }


}
