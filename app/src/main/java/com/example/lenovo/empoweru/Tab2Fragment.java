package com.example.lenovo.empoweru;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.empoweru.leve4.user_details4;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;


/**
 * Created by User on 2/28/2017.
 */

public class Tab2Fragment extends Fragment implements AdapterView.OnClickListener,AdapterView.OnItemSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    private Block_details3 te;
    TextView abcd;
    LocationManager locationManager;
    Double latitude=0.0, longitude=0.0;int slot_id;
    Cursor res;
    String Text;
    Cursor teachers;
    HashMap<String, List<String>> listDataChild;
    Spinner spinner1,spinner2,spinner3;
    int accuracy=0;
    List<String> blocks_list=new ArrayList<>();
    List<Integer> block_id_list=   new ArrayList<>();
    List<String> cluster_list=new ArrayList<>();
    List<String> school_list=new ArrayList<>();
    String block_name="block",cluster_name="cluster",school_name="school";


    List<Integer> teachers_list= new ArrayList<>();
    List<Integer> teacher_ids=new ArrayList<>();
    List<Integer> present_status=new ArrayList<>();

    public static final List<Integer> school_id_list=  new ArrayList<>();

    FrameLayout fl;
    private ProgressDialog dialog;
    private HM_submit_tables_teachers hstt;
    private user_details4 ud;
    private String time,date;int taken_by_id;
    int school_id;
    EditText remark;
    Button submit; AlertDialog.Builder builder1;
    private HM_details5 hmd;
    private radrecadapter rrd;
    private RecyclerView packageRecyclerView;
    private SharedPreferences loginData;
    private PackageModel pm;
    List<String> teachers_listrrd=new ArrayList<>();
    List<Integer> teacher_idsrrd=new ArrayList<>();
    private String datewt;
    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    int count=0;
    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;
    private String times;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tabbedteacher2, container, false);
        init();
        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        rrd=new radrecadapter(getContext());
        loginData = getActivity().getSharedPreferences("presenties", Context.MODE_PRIVATE);
        pm=new PackageModel(Tab2Fragment.this);
        packageRecyclerView = (RecyclerView) view.findViewById(R.id.package_lst);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        packageRecyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(packageRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        packageRecyclerView.addItemDecoration(dividerItemDecoration);
        radrecadapter recyclerViewAdapter = new
                radrecadapter(getPackages(),getActivity());
        packageRecyclerView.setAdapter(recyclerViewAdapter);
        packageRecyclerView.setNestedScrollingEnabled(false);
        remark=view.findViewById(R.id.remark);
        submit=view.findViewById(R.id.submitx);
        hstt=new HM_submit_tables_teachers(getContext());
        hmd=new HM_details5(getContext());
        dialog=new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
  /*      final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 200); // after 2 second (or 2000 miliseconds), the task will be active.
*/
        //locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //LocationListener mlocListener = new Tab2Fragment.MyLocationListener();
        //Criteria criteria = new Criteria();
      // expListView=(ExpandableListView) view.findViewById(R.id.lvExp);
        prepareListData();
        SimpleDateFormat sdfdtime = new SimpleDateFormat("HH:mm");
        times=sdfdtime.format(new Date());
        SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date=datet.format(new Date());
        SimpleDateFormat timet = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datew = new SimpleDateFormat("dd/MM/yyyy_HH:mm");
        datewt =datew.format(new Date());
        time=timet.format(new Date());

        Cursor slotid=hmd.getSlot_id(times);
        slotid.moveToFirst();
        slot_id=slotid.getInt(1);
        Cursor cluster_id=hmd.data_data();
        cluster_id.moveToFirst();
        taken_by_id=cluster_id.getInt(1);
        Cursor schoolid=hmd.school_detail_data();
        schoolid.moveToFirst();
        school_id=schoolid.getInt(6);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hstt.insersubmittable1(latitude,longitude ,accuracy ,slot_id ,taken_by_id , date, time, school_id, remark.getText().toString(), 0);
                // int i=teacher_ids.size();
                Cursor cur =  hstt.getAllData1();
                cur.moveToLast();
                int attend_id=cur.getInt(0);
                int absent=0,present=0,leave=0;
                String presenties= loginData.getString("presenttt", "No name defined");
                String joinedMinusBrackets = presenties.substring( 1, presenties.length() - 1);

                // String.split()
                String[] resplit = joinedMinusBrackets.split( ", ");
             //   Toast.makeText(view.getContext(), Arrays.toString(resplit),Toast.LENGTH_SHORT ).show();
                present_status= getIntegerArray(new ArrayList<String>(Arrays.asList(resplit)));
                for(int i = 0;i<teacher_ids.size();i++){

                    // array[i] = cursor.getString(0);
                    hstt.insertsubmittable2(attend_id, teacher_idsrrd.get(i+1), present_status.get(i+1), 0);
                    if(present_status.get(i+1)==1){++present;}
                    else if (present_status.get(i+1)==2){++absent;}
                    else if(present_status.get(i+1)==3){++leave;}

                }
                hstt.insertsubmittable3(attend_id,present,absent,leave,0,remark.getText().toString(),datewt);
                builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("Preliminary Attendence Done");
                builder1.setMessage("Please sync to complete the process");
                builder1.setCancelable(false);
                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Tab1Fragment Tab1Fragment = new Tab1Fragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_container,Tab1Fragment );
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// give your fragment container id in first parameter
                               transaction.addToBackStack(null);  // if written, this transaction will be added to backstack

                                transaction.detach(Tab1Fragment);
                                transaction.attach(Tab1Fragment);

                                transaction.commit();
                                // startActivity(new Intent(getContext(),BlockTab1Fragment.class));
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


/*        criteria = new Criteria();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
        }else{
            criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        }
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


        }
        locationManager.requestLocationUpdates(provider,
                (2000), 10, mlocListener);

*/

        return view;
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
 /*   public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            latitude =  loc.getLatitude();
            longitude= loc.getLongitude();
            accuracy=Math.round(loc.getAccuracy());
            Text = "My current location is: " + "Latitude = "
                    + loc.getLatitude() + "Longitude = " +loc.getLongitude()+"////" +loc.getAccuracy();
       //     Toast.makeText(getContext(), Text, Toast.LENGTH_SHORT)
         //           .show();

            dialog.cancel();
            Log.d("TAG", "Starting..");
        }

        @Override
        public void onProviderDisabled(String provider) {
            //  pd.dismiss();
            Toast.makeText(getContext(), "Gps Disabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getContext(), "Gps Enabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
*/

    private void prepareListData() {

        teacher_ids.clear();
        present_status.clear();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,List<String>>();
        listDataHeader.clear();
        listDataHeader.add("Select Teacher");
        StringBuffer buffer = new StringBuffer();
        Cursor teacher=hmd.teacher_data();
        teacher=hmd.teacher_data();
    //    Toast.makeText(getContext(),teacher.getCount()+"  "+teacher.getColumnCount()  , Toast.LENGTH_LONG).show();
        while (teacher.moveToNext()) {
            listDataHeader.add(teacher.getString(1));
            teachers_list.add(teacher.getInt(0));
            teacher_ids.add(teacher.getInt(0));
            present_status.add(1);
        }
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data


        listAdapter = new com.example.lenovo.empoweru.ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
    /*    expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }



        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                return false;
            }
        });
        */

    }
    private List<PackageModel> getPackages(){
        List<PackageModel> modelList = new ArrayList<PackageModel>();

        List<String> priceList = new ArrayList<String>();
        priceList.clear();
        priceList.add("PRESENT");
        priceList.add("ABSENT");
        priceList.add("LEAVE");

      hmd=new HM_details5(getActivity());
        Cursor teacher=hmd.teacher_data();
        teachers_listrrd.clear();
        teacher_idsrrd.clear();
        teachers_listrrd.add("emptty");
        teacher_idsrrd.add(0);
        while (teacher.moveToNext()) {
            //listDataHeader.add(teacher.getString(1));
            teachers_listrrd.add(teacher.getString(1));
            teacher_idsrrd.add(teacher.getInt(0));
            //  present_status.add(1);
        }
       // Toast.makeText(getActivity(),teachers_listrrd.size()+"" ,Toast.LENGTH_SHORT ).show();
        for(int i=0;i<teachers_listrrd.size();i++){
            modelList.add(new PackageModel(teachers_listrrd.get(i), priceList));
            //  Toast.makeText(getApplicationContext(),teachers_list.size()+"/"+priceList.size() ,Toast.LENGTH_SHORT ).show();

        }

        return modelList;
    }


    @Override
    public void onClick(View view) {
        //  Intent intent = getActivity().getIntent();
        //    getActivity().finish();
        // startActivity(intent);
    }
    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mSettingsClient = LocationServices.getSettingsClient(getActivity());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                if (mCurrentLocation != null ) {
                    if( dialog != null)
                        dialog.dismiss();
                    latitude= mCurrentLocation.getLatitude();
                    longitude=mCurrentLocation.getLongitude();
                    accuracy= (int) mCurrentLocation.getAccuracy();}
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

              //  updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

      //  updateLocationUI();
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null ) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (!prefs.getBoolean("firstTime", false)) {
                // <---- run your one time code here
             //   Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
             //           "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
                latitude= mCurrentLocation.getLatitude();
                longitude=mCurrentLocation.getLongitude();
                accuracy= (int) mCurrentLocation.getAccuracy();
                // mark first time has runned.
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();
            }
            else{
                dialog.cancel();
            }

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }
    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");


                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                   //     updateLocationUI();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    //    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                // Log.e(TAG, errorMessage);

                                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }

                    //    updateLocationUI();
                    }
                });
    }

    public void startLocationButtonClick() {
        // Requesting ACCESS_FINE_LOCATION using Dexter library
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        //Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

     //   updateLocationUI();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onPause() {
        super.onPause();

        if (mRequestingLocationUpdates) {
            // pausing location updates
            // stopLocationUpdates();
        }
    }
}
