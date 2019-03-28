package com.example.lenovo.empoweru;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Block_profile extends AppCompatActivity {
    private Block_details3 ud;
    String nameq;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button navigation;
    TextView doj,school,name,degree,dob,gender,proquali,noa,email,cluster,designation,contact,workingstatus,village,appointedfor,panchayat,adhaar,block;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_profile);


        adhaar=(TextView) findViewById(R.id.aadhar);
        dob=(TextView)findViewById(R.id.dob1);
        designation=(TextView)findViewById(R.id.designation);

        gender=(TextView)findViewById(R.id.gender);
        name=(TextView)findViewById(R.id.name);

        contact=(TextView)findViewById(R.id.contact);

        email=(TextView)findViewById(R.id.email);

        block=(TextView)findViewById(R.id.block);
        ud=new Block_details3(this);
        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Block_profile.this,BLOCK_nav_d.class));



            }
        });
        Cursor res = ud.user_detail_data();
        res.moveToFirst();
        //Toast.makeText(getApplicationContext(),res.getCount() +"\n"+res.getColumnCount(),Toast.LENGTH_SHORT ).show();
      //  res.moveToPosition(0);

        name.setText( res.getString(0));
        dob.setText("Date of Birth:"+ res.getString(2));

        if(res.getInt(3)==1){
            gender.setText("Gender :"+"Male");
        }
        else {
            gender.setText("Gender :" + "Male");
        }

        email.setText("Email:"+ res.getString(4));
        designation.setText("Designation :"+ res.getString(1));
        contact.setText("Contact :"+ res.getString(5));
        adhaar.setText("Adhaar:"+ res.getString(7));
        block.setText("Block :"+ res.getString(8));


    }


}
