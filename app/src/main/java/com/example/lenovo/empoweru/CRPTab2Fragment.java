package com.example.lenovo.empoweru;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import static android.content.ContentValues.TAG;


/**
 * Created by User on 2/28/2017.
 */

public class CRPTab2Fragment extends Fragment implements AdapterView.OnClickListener, AdapterView.OnItemSelectedListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    private Block_details3 te;
    TextView abcd;
    LocationManager locationManager;
    Double latitude = 0.0, longitude = 0.0;
    int slot_id;
    Cursor res;
    String Text;
    Cursor blocks, clusters, schools, teachers;
    HashMap<String, List<String>> listDataChild;
    Spinner spinner1, spinner2, spinner3;
    int accuracy = 0;

    List<String> blocks_list = new ArrayList<>();
    List<Integer> block_id_list = new ArrayList<>();
    List<String> cluster_list = new ArrayList<>();
    List<String> school_list = new ArrayList<>();
    String block_name = "block", cluster_name = "cluster", school_name = "school";


    List<Integer> cluster_id_list = new ArrayList<>();
    List<Integer> teacher_ids = new ArrayList<>();
    List<Integer> present_status = new ArrayList<>();

    public static final List<Integer> school_id_list = new ArrayList<>();

    FrameLayout fl;
    private ProgressDialog dialog;
    private CRP_submit_tables_teachers cstt;
    private user_details4 ud;
    private String time, date;
    int taken_by_id;
    int school_id;
    EditText remark;
    Button submit;
    AlertDialog.Builder builder1;
    private radrecadapter rrd;
    private RecyclerView packageRecyclerView;
    private SharedPreferences loginData;
    private PackageModel pm;
    List<String> teachers_listrrd = new ArrayList<>();
    List<Integer> teacher_idsrrd = new ArrayList<>();
    private GPSTracker gPSTracker;
    private Handler handler;
    // location last updated time
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
    private String datewt;
    private String times;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crptabbedteacherqtab2, container, false);

        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        rrd = new radrecadapter(getContext());

        gPSTracker = new GPSTracker(getActivity());
        loginData = getActivity().getSharedPreferences("presenties", Context.MODE_PRIVATE);
        pm = new PackageModel(CRPTab2Fragment.this);
        packageRecyclerView = (RecyclerView) view.findViewById(R.id.package_lst);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        packageRecyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(packageRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        packageRecyclerView.addItemDecoration(dividerItemDecoration);
        remark = view.findViewById(R.id.remark);
        submit = view.findViewById(R.id.submitx);
        ud = new user_details4(getContext());
        clusters = ud.cluster_details_data();
        cstt = new CRP_submit_tables_teachers(getContext());
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
        final Timer t = new Timer();
        // t.schedule(new TimerTask() {
        //   public void run() {
        //        dialog.dismiss(); // when the task active then close the dialog
        //        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
        //   }
        // }, 200); // after 2 second (or 2000 miliseconds), the task will be active.

        // locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //    LocationListener mlocListener = new CRPTab2Fragment.MyLocationListener();
        Criteria criteria = new Criteria();
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        res = ud.school_detail_data();


        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);

        spinner3.setEnabled(false);
        SimpleDateFormat sdfdtime = new SimpleDateFormat("HH:mm");
        times=sdfdtime.format(new Date());
        SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat timet = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datew = new SimpleDateFormat("dd/MM/yyyy_HH:mm");
        datewt =datew.format(new Date());
        time=timet.format(new Date());
        date=datet.format(new Date());

        time = timet.format(new Date());
        date = datet.format(new Date());
        Cursor slotid = ud.getSlot_id(times);
        slotid.moveToFirst();
        slot_id = slotid.getInt(1);
        Cursor cluster_id = ud.data_data();
        cluster_id.moveToFirst();
        taken_by_id = cluster_id.getInt(1);
        Cursor school_detail = ud.school_detail_data();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cstt.insersubmittable1(latitude, longitude, accuracy, slot_id, taken_by_id, date, time, school_id, cluster_name, school_name, remark.getText().toString(), 0);
                // int i=teacher_ids.size();
                Cursor cur = cstt.getAllData1();
                cur.moveToLast();
                int attend_id = cur.getInt(0);
                int absent = 0, present = 0, leave = 0;
                res.moveToFirst();
                String presenties = loginData.getString("presenttt", "No name defined");
                String joinedMinusBrackets = presenties.substring(1, presenties.length() - 1);

                // String.split()
                String[] resplit = joinedMinusBrackets.split(", ");
            //   Toast.makeText(view.getContext(), Arrays.toString(resplit) + teacher_ids.size() + teacher_idsrrd.size(), Toast.LENGTH_SHORT).show();
                present_status = getIntegerArray(new ArrayList<String>(Arrays.asList(resplit)));
                for (int i = 0; i < teacher_idsrrd.size() - 1; i++) {
                    int teacher_id = res.getInt(0);
                    // array[i] = cursor.getString(0);
                    cstt.insertsubmittable2(attend_id, teacher_idsrrd.get(i + 1), present_status.get(i + 1), 0);
                    if (present_status.get(i + 1) == 1) {
                        ++present;
                    } else if (present_status.get(i + 1) == 2) {
                        ++absent;
                    } else if (present_status.get(i + 1) == 3) {
                        ++leave;
                    }

                }
                cstt.insertsubmittable3(attend_id, present, absent, cluster_name, school_name, leave, 0, remark.getText().toString(),datewt);
                builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("Preliminary Attendence Done");
                builder1.setMessage("Please sync to complete the process");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Fragment CRPTab1Fragment = new CRPTab1Fragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_container, CRPTab1Fragment); // give your fragment container id in first parameter
                                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                transaction.commit();
                                // startActivity(new Intent(getContext(),BlockTab1Fragment.class));
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        //  clusters.moveToLast();
        //  Toast.makeText(getContext(),clusters.getCount()+""+clusters.getColumnCount() +" "+clusters.getString(0),Toast.LENGTH_LONG).show();
        cluster_id_list.clear();
        cluster_list.clear();
        cluster_id_list.add(0);
        cluster_list.add("Select Cluster");
        while (clusters.moveToNext()) {
            //   Toast.makeText(getContext(),blocks.getString(0) ,Toast.LENGTH_LONG).show();
            cluster_list.add(clusters.getString(0));
            cluster_id_list.add(clusters.getInt(1));
        }

        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, cluster_list.toArray());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        int initialSelectedPosition2 = spinner2.getSelectedItemPosition();
        spinner2.setSelection(initialSelectedPosition2, false);
        spinner2.setSelection(0, false); //clear selection
        spinner2.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //  Toast.makeText(getContext(),country[position] ,Toast.LENGTH_LONG).show();

        int block_id_pos,cluster_id_pos,schools_id_pos;
        Spinner spinner = (Spinner) arg0;
        if (spinner.getId() == R.id.spinner2) {
            // Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
            cluster_name = arg0.getItemAtPosition(position).toString();
            // Toast.makeText(getContext(), block_name, Toast.LENGTH_LONG).show();
            cluster_id_pos = position ;
            if(spinner3.isEnabled()==false) {
                spinner3.setEnabled(true);
            }
            insert_into_school_list(cluster_id_pos);

        }else if (spinner.getId() == R.id.spinner3) {

            // if(block_name.equals("block")|| cluster_name.equals("cluster")){spinner.setEnabled(false);}
            //  else{
            //Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
            school_name = arg0.getItemAtPosition(position).toString();

            schools_id_pos = position ;
            radrecadapter recyclerViewAdapter = new
                    radrecadapter(getPackages(schools_id_pos),getActivity());
            packageRecyclerView.setAdapter(recyclerViewAdapter);
            packageRecyclerView.setNestedScrollingEnabled(false);
          //  packageRecyclerView.scrollToPosition(8);
          //  prepareListData(schools_id_pos);
            //  Toast.makeText(getContext(), school_name+"\n"+block_name+"\n"+cluster_name, Toast.LENGTH_SHORT)
            //      .show();

        }

    }


    public void insert_into_school_list(int cluster_id_pos) {

        int cluster_id=cluster_id_list.get(cluster_id_pos);
        schools=ud.block_cluster_school(cluster_id);
        school_list.clear();
        school_id_list.clear();
        school_list.add("Select School");
        school_id_list.add(0);
        while (schools.moveToNext()) {
            // Toast.makeText(getContext(),clusters.getString(0) ,Toast.LENGTH_LONG).show();
            school_list.add(schools.getString(2));
            school_id_list.add(schools.getInt(7));
        }
        //  Toast.makeText(getContext(), String.valueOf(school_id_list.size()), Toast.LENGTH_LONG).show();
        ArrayAdapter adapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,school_list.toArray());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        int initialSelectedPosition3=spinner3.getSelectedItemPosition();
        spinner3.setSelection(initialSelectedPosition3, false);
        spinner3.setSelection(0, false); //clear selection
        spinner3.setOnItemSelectedListener(this);

    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    private void prepareListData(Integer schools_id_pos) {
        // Toast.makeText(getContext(), String.valueOf(schools_id_pos), Toast.LENGTH_LONG).show();
        //  Toast.makeText(getContext(),school_id_list.size()+"" ,Toast.LENGTH_LONG).show();
        int schools_id=school_id_list.get(schools_id_pos);
        school_id=schools_id;
        //  Toast.makeText(getContext(),school_list.toString()+"" ,Toast.LENGTH_LONG).show();
        // Toast.makeText(getContext(),school_id_list.toString()+"" ,Toast.LENGTH_LONG).show();
        teacher_ids.clear();
        present_status.clear();
        //listDataHeader.clear();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,List<String>>();

        StringBuffer buffer = new StringBuffer();
        teachers=ud.block_cluster_school_teacher(schools_id);

        //  no_of_teachers=res.getColumnCount();
        while (teachers.moveToNext()) {
            // buffer.append("Date of Joining:"+ res.getString(0));
            listDataHeader.add(teachers.getString(1));
            teacher_ids.add(teachers.getInt(0));
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
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listAdapter = new com.example.lenovo.empoweru.ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

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
    private List<PackageModel> getPackages(Integer schools_id_pos){
        List<PackageModel> modelList = new ArrayList<PackageModel>();

        List<String> priceList = new ArrayList<String>();
        priceList.clear();
        priceList.add("PRESENT");
        priceList.add("ABSENT");
        priceList.add("LEAVE");





        //  no_of_teachers=res.getColumnCount();

        int schools_id=school_id_list.get(schools_id_pos);
        school_id=schools_id;
        //  Toast.makeText(getContext(),school_list.toString()+"" ,Toast.LENGTH_LONG).show();
        // Toast.makeText(getContext(),school_id_list.toString()+"" ,Toast.LENGTH_LONG).show();
        teacher_ids.clear();
        present_status.clear();
        //listDataHeader.clear();


        teachers=ud.block_cluster_school_teacher(schools_id);
        teachers_listrrd.clear();
        teacher_idsrrd.clear();
        teachers_listrrd.add("emptty");
        teacher_idsrrd.add(0);
        //  no_of_teachers=res.getColumnCount();
        while (teachers.moveToNext()) {
            // buffer.append("Date of Joining:"+ res.getString(0));
            teachers_listrrd.add(teachers.getString(1));
            teacher_idsrrd.add(teachers.getInt(0));
        }


        Toast.makeText(getActivity(),teachers_listrrd.size()+"" ,Toast.LENGTH_SHORT ).show();
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

            //    updateLocationUI();
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
   //     SharedPreferences prefst = PreferenceManager.getDefaultSharedPreferences(getActivity());
      //  if (!prefst.getBoolean("firstTimet", false)) {
          //  updateLocationUI();
        //    SharedPreferences.Editor editort = prefst.edit();
        //    editort.putBoolean("firstTimet", true);
        //    editort.commit();
       // }
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null ) {
         /*   SharedPreferences prefst = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (!prefst.getBoolean("firstTimet", false)) {
                // <---- run your one time code here*/
             //   Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
               //         "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
                if( dialog != null)
                    dialog.dismiss();
                latitude= mCurrentLocation.getLatitude();
                longitude=mCurrentLocation.getLongitude();
                accuracy= (int) mCurrentLocation.getAccuracy();
                // mark first time has runned.
        /*        SharedPreferences.Editor editort = prefst.edit();
                editort.putBoolean("firstTimet", true);
                editort.commit();
            }
            else{
                if( dialog != null)
                    dialog.dismiss();

            }*/

        }
/*        if (mCurrentLocation != null) {
            Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
                    "Lng: " + mCurrentLocation.getLongitude() + "acc" + mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
            if (dialog != null)
                dialog.dismiss();
            latitude = mCurrentLocation.getLatitude();
            longitude = mCurrentLocation.getLongitude();
            accuracy = (int) mCurrentLocation.getAccuracy();
        }*/
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

                       // updateLocationUI();
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

                      //  updateLocationUI();
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

    //    updateLocationUI();
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
