package com.example.lenovo.empoweru;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;



public class Profiledetails extends AppCompatActivity {
private HM_details5 hmd;
TextView doj,school,name,degree,dob,gender,proquali,noa,email,cluster,designation,contact,workingstatus,village,appointedfor,panchayat,adhaar,block;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetails);
       // pd=findViewById(R.id.profiledetails);
doj=(TextView)findViewById(R.id.doj);
school=(TextView)findViewById(R.id.schoolname);
adhaar=(TextView)findViewById(R.id.aadhar);
dob=(TextView)findViewById(R.id.dob1);
designation=(TextView)findViewById(R.id.designation);
noa=(TextView)findViewById(R.id.noa);
appointedfor=(TextView)findViewById(R.id.appointedfor);
cluster=(TextView)findViewById(R.id.cluster);
workingstatus=(TextView)findViewById(R.id.working);
gender=(TextView)findViewById(R.id.gender);
name=(TextView)findViewById(R.id.name);
proquali=(TextView)findViewById(R.id.proquali);
contact=(TextView)findViewById(R.id.contact);
village=(TextView)findViewById(R.id.village);
panchayat=(TextView)findViewById(R.id.panchayat);
appointedfor=(TextView)findViewById(R.id.appointedfor);
email=(TextView)findViewById(R.id.email);
degree=(TextView)findViewById(R.id.degree);
block=(TextView)findViewById(R.id.block);
        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(Profiledetails.this,Dashboard.class));



            }
        });
       hmd=new HM_details5(this);
        Cursor res = hmd.user_detail_data();

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Date of Joining:"+ res.getString(0));
            doj.setText("Date of Joining:"+ res.getString(0));
            buffer.append("School :"+ res.getString(1));
            school.setText("School :"+ res.getString(1));
          buffer.append("Name :"+ res.getString(2));
          name.setText( res.getString(2));
            buffer.append("Degree :"+ res.getString(3));
            degree.setText("Degree :"+ res.getString(3));
            buffer.append("Date of Birth:"+ res.getString(4));
            dob.setText("Date of Birth:"+ res.getString(4));
            buffer.append("Gender :"+ res.getInt(5));
            if(res.getInt(4)==1){
                gender.setText("Gender :"+"Male");
            }
            else {
                gender.setText("Gender :" + "Male");
            }
           // gender.setText("Gender :"+ res.getInt(5));

            buffer.append("Pro_Quali :"+ res.getString(6));
            proquali.setText("Pro_Quali :"+ res.getString(6));
            buffer.append("Noa :"+ res.getString(7));
            noa.setText("Noa :"+ res.getString(7));
            buffer.append("Email:"+ res.getString(8));
            email.setText("Email:"+ res.getString(8));
            buffer.append("Cluster :"+ res.getString(9));
            cluster.setText("Cluster :"+ res.getString(9));
            buffer.append("Designation :"+ res.getString(10));
            designation.setText("Designation :"+ res.getString(10));
            buffer.append("Contact :"+ res.getString(11));
            contact.setText("Contact :"+ res.getString(11));
            buffer.append("Working_Status:"+ res.getString(12));
            workingstatus.setText("Working_Status:"+ res.getString(12));
            buffer.append("Village :"+ res.getString(13));
            village.setText("Village :"+ res.getString(13));
            buffer.append("Appointed_for :"+ res.getString(14));
            appointedfor.setText("Appointed_for :"+ res.getString(14));
            buffer.append("Panchayat :"+ res.getString(15));
            panchayat.setText("Panchayat :"+ res.getString(15));
            buffer.append("Adhaar:"+ res.getString(16));
            adhaar.setText("Adhaar:"+ res.getString(16));
            buffer.append("Block :"+ res.getString(17));
            block.setText("Block :"+ res.getString(17));
           }

        // Show all data
       String showMessage=buffer.toString();
      // email.setText(showMessage+"22");

    }
}
