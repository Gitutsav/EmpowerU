package com.example.lenovo.empoweru;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.view.View.GONE;

public class LocAlert extends AppCompatActivity {
    LocationManager locationManager;
    Double latitude, longitude;
    int accuracy;
    String Text;
    DialogInterface dialogue;
    AlertDialog.Builder builder1;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_alert);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        Criteria criteria = new Criteria();
        criteria = new Criteria();
        dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
        builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(false);

      /*  builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogue=dialog;
                        dialogue.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogue.cancel();
                    }
                });
*/
       // AlertDialog alert11 = builder1.create();
       // alert11.show();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
        }else{
            criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        }
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


        }
        locationManager.requestLocationUpdates(provider,
                (10*60*1000), 10, mlocListener);
    }
    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            latitude =  loc.getLatitude();
            longitude= loc.getLongitude();
            accuracy=Math.round(loc.getAccuracy());
            Text = "My current location is: " + "Latitude = "
                    + loc.getLatitude() + "Longitude = " +loc.getLongitude()+"////" +loc.getAccuracy();
            Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT)
                    .show();
         //   latlong.setText(Text);
            //tam.insertable1(latitude,longitude,accuracy,slot_id,user_id,date,currentDateandTime,school_id,remark,0);
           // pd.dismiss();
           // pb.setVisibility(GONE);
dialog.cancel();

            Log.d("TAG", "Starting..");
        }

        @Override
        public void onProviderDisabled(String provider) {
         //   pd.dismiss();
            Toast.makeText(getApplicationContext(), "Gps Disabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "Gps Enabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

}
