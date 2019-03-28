package com.example.lenovo.empoweru;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
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


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class Tab4Fragment extends android.support.v4.app.Fragment implements  AdapterView.OnClickListener,AdapterView.OnItemSelectedListener {

    private Button button;
    private String encoded_string = "", image_name;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;
    private Button btnTEST;
    android.support.v7.widget.RecyclerView rvPrueba;
    Button btnCalcular;
    Cursor crs;
    Double latitude=0.0, longitude=0.0;
    String Text;int accuracy;
    String[] dupliescrito;
    // List<String> class_num = Arrays.asList("Item 1","Item 2","Item 3");
    List<String> class_num = new ArrayList<>();
    List<String> nos = new ArrayList<>();
    BlockPruebaAdapterStudent adapter;
    boolean bandera = true;

    ImageView im;
    int school_id;    Cursor blocks,clusters,schools,class_numbers,tot_students;
    String cluster_name="cluster",school_name="school";
    String myBase64Image="imbase64";
    Spinner spinner1,spinner2,spinner3;
    public static final List<Integer> school_id_list=  new ArrayList<>();
    List<String> cluster_list=new ArrayList<>();
    List<Integer> cluster_id_list= new ArrayList<>();
    List<Integer> class_ids=new ArrayList<>();
    List<String> school_list=new ArrayList<>();
    private Block_details3 te;
    private Cursor classes;
    FrameLayout fl,flcross;TextView txtvwcross;
    private Uri imageUri;
    private final static int TAKE_PICTURE=101;
    private CRP_submit_tables_classes bstc;
    private String time,date;
    private int slot_id;
    private int taken_by_id;
    private EditText remark;
    String remarks="remark";
    AlertDialog.Builder builder1;
    private ProgressDialog dialog;
    private user_details4 ud;Cursor cluster;
    private HM_submit_tables_classes hstc;
    private HM_details5 hmd;
    LocationManager locationManager;
    ImageButton imgbtn;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
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
    private String times,datewt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabbedteacher4, container, false);
        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        rvPrueba = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
        btnCalcular = (Button) view.findViewById(R.id.submitx);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(view.getContext());
        rvPrueba.setLayoutManager(llm);
        hstc=new HM_submit_tables_classes(view.getContext());
        hmd= new HM_details5(view.getContext());
        remark = view.findViewById(R.id.remark);
        imgbtn=view.findViewById(R.id.imagebutton);

fl=view.findViewById(R.id.framelayout);
        flcross=view.findViewById(R.id.framelayoutcross);
        flcross.setVisibility(View.GONE);
        txtvwcross=view.findViewById(R.id.txtvwcross);
        txtvwcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flcross.setVisibility(View.GONE);
                fl.setVisibility(View.VISIBLE);
            }
        });
        dialog=new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
      /*  final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 1000);
*/
        SimpleDateFormat sdfdtime = new SimpleDateFormat("HH:mm");
        times=sdfdtime.format(new Date());
        SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat timet = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datew = new SimpleDateFormat("dd/MM/yyyy_HH:mm");
        datewt =datew.format(new Date());
        time=timet.format(new Date());
        date=datet.format(new Date());
        Cursor slotid=hmd.getSlot_id(times);
        slotid.moveToFirst();
        slot_id=slotid.getInt(1);
        Cursor crp_id=hmd.data_data();
        crp_id.moveToFirst();
        taken_by_id=crp_id.getInt(1);
        Cursor schoolid=hmd.school_detail_data();
        schoolid.moveToFirst();
        school_id=schoolid.getInt(6);
        int i = 0;
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //takePhoto(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });

         prepareListData();
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remarks=remark.getText().toString();
                String[] escrito = adapter.getPresenties_list();

                int absent=0,present=0,total=0;
                String test = "";
                int resultado = 0;
                int count = 0;
                int nullc = 0;

                for (int i = 0; i < escrito.length; i++) {

                    if (TextUtils.isEmpty(escrito[i])) {
                        bandera = false;
                        ++nullc;
                    }

                }
                //Toast.makeText(getActivity(),"length="+ nullc,Toast.LENGTH_LONG).show();

                if (nullc == 0) {
                    for (int i = 0; i < escrito.length; i++) {
                        if (Integer.parseInt(escrito[i]) > Integer.parseInt(dupliescrito[i])) {
                            ++count;
                        }
                    }
                    if (count == 0) {  if(!myBase64Image.equals("imbase64")) {

                        for (int i = 0; i < escrito.length; i++) {
                            resultado += Integer.parseInt(escrito[i]);
                            if (escrito[i] != null) {
                                test = test + escrito[i] + " ";
                            } else {
                                test = test + "_" + " ";
                            }
                        }
                        hstc.insersubmittable1(latitude, longitude, accuracy, slot_id, taken_by_id,
                                date, time, school_id, myBase64Image, remarks, 0);
                        // Cursor cur =  bstc.getLastAttendenceId();
                        int attend_id = 0;
                        Cursor curx = hstc.getAllData1();
//cur.moveToPosition(3);
//if (cur != null && cur.getCount() != 0){
                        /// cursor cant take out table whose size is more than 1 mb
                        curx.moveToLast();
                        //Toast.makeText(getContext(),"Null Cursor"+""+curx.getColumnCount()+curx.getCount(), Toast.LENGTH_LONG).show();
                        // Toast.makeText(getContext(),""+curx.getInt(0), Toast.LENGTH_LONG).show();
                        attend_id=curx.getInt(0);
                        //  }
                        //                     else {

//}

// +cur.getInt(0)
                        // Toast.makeText(getContext(),""+class_ids.size(), Toast.LENGTH_LONG).show();
                        for (int i = 0; i < class_ids.size(); i++) {
                            hstc.insertsubmittable2(attend_id, class_ids.get(i), Integer.parseInt(escrito[i]), 0);
                            present = present + Integer.parseInt(escrito[i]);
                            total = total + Integer.parseInt(nos.get(i));
                        }
                        absent = total - present;
                        hstc.insertsubmittable3(attend_id, present, absent, total, 0, remarks, datewt);
                        builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Preliminary Attendence Done");
                        builder1.setMessage("Please sync to complete the process");
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
                      //  Toast.makeText(getActivity(), "Resultado : " + resultado + "\n" + test, Toast.LENGTH_LONG).show();
                    }    else {
                        builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Please Attach Pic");
                        builder1.setMessage("Can't submit attendence without attaching pic");
                        builder1.setCancelable(false);

                        builder1.setPositiveButton(
                                "ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        android.support.v4.app.Fragment Tab3Fragment = new Tab3Fragment();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        transaction.replace(R.id.frame_container,Tab3Fragment ); // give your fragment container id in first parameter
                                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                        transaction.commit();

                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }
                    }
                    else {
                        for (int i = 0; i < escrito.length; i++) {
                            resultado += Integer.parseInt(escrito[i]);
                            if (escrito[i] != null) {
                                test = test + escrito[i] + " ";
                            } else {
                                test = test + "_" + " ";
                            }
                        }
                        builder1 = new AlertDialog.Builder(getActivity());
                        // builder1.setTitle("Preliminary Attendence Done");
                        builder1.setMessage("present cannot be greater than total");
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
                       // Toast.makeText(getActivity(), "present cannot be greater than total" + "\n" + test, Toast.LENGTH_LONG).show();
                    }
                } else if (nullc > 0) {
                    builder1 = new AlertDialog.Builder(getActivity());
                    // builder1.setTitle("Preliminary Attendence Done");
                    builder1.setMessage("Enter values cannot be null");
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
                   // Toast.makeText(getActivity(), "Enter values cannot be null" + "\n" + test, Toast.LENGTH_LONG).show();


                }}





        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }






    private void prepareListData() {

        StringBuffer buffer = new StringBuffer();
        classes=hmd.class_list_data();
        class_num.clear();
        class_ids.clear();

        nos.clear();

       while (classes.moveToNext()) {

            class_num.add(classes.getString(1));
            nos.add(String.valueOf(classes.getInt(2)));
            class_ids.add(classes.getInt(0));


        }
       /* class_ids.add(1);
        class_ids.add(2);
        class_ids.add(3);
        class_ids.add(4);
        class_num.add("1st");
        class_num.add("2nd");
        class_num.add("3rd");
        class_num.add("4th");
        nos.add("12");
        nos.add("0");
        nos.add("35");
        nos.add("10");*/
        dupliescrito = new String[class_num.size()];
        for (int i = 0; i < class_num.size(); i++) {
            dupliescrito[i] = nos.get(i);// to compare presenties vs total
            //  dupliescrito[i]= String.valueOf(crs.getInt(2))  ;
        }
        adapter = new BlockPruebaAdapterStudent(class_num, nos);
        rvPrueba.setAdapter(adapter);
        rvPrueba.setNestedScrollingEnabled(false);


    }

    @Override
    public void onClick(View view) {

    }
    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        //   float sizeinmb=photo.length()/(1024*1024);


        imageUri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".my.package.name.provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
       // imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
  /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);

                    ContentResolver cr = getActivity().getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        Bitmap converetdImage = getResizedBitmap(bitmap, 500);
                        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
                        imageView.setImageBitmap(converetdImage);
                        fl.setVisibility(View.GONE);
                        flcross.setVisibility(View.VISIBLE);
                        // imageView.setImageBitmap(bitmap);
                        myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 100);
                        //   Bitmap myBitmapAgain = decodeBase64(myBase64Image);
                        //int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap)/1024;

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }
    */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width+80+80, height+10, true);
    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    /*    public void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
             //   imageView.setImageBitmap(photo);

                Bitmap converetdImage = getResizedBitmap(photo, 500);
                ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
                imageView.setImageBitmap(photo);
                fl.setVisibility(View.GONE);
                flcross.setVisibility(View.VISIBLE);
                // imageView.setImageBitmap(bitmap);
                myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 100);
            }
        }
    */
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
                    //    Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
                    //          "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
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

        //    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        if (!prefs.getBoolean("firstTimet", false)) {
        //  updateLocationUI();
        //     SharedPreferences.Editor editor = prefs.edit();
        //     editor.putBoolean("firstTimet", true);
        //     editor.commit();
        // }
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null ) {
          /*  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (!prefs.getBoolean("firstTime", false)) {
                // <---- run your one time code here*/
          //  Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
           //         "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
            if( dialog != null)
                dialog.dismiss();
            latitude= mCurrentLocation.getLatitude();
            longitude=mCurrentLocation.getLongitude();
            accuracy= (int) mCurrentLocation.getAccuracy();
            // mark first time has runned.
             /*   SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();
            }
            else{
                if( dialog != null)
                    dialog.dismiss();

            }*/

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
            case CAMERA_REQUEST:
                if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    //   imageView.setImageBitmap(photo);

                    Bitmap converetdImage = getResizedBitmap(photo, 500);
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
                    imageView.setImageBitmap(photo);
                    fl.setVisibility(View.GONE);
                    flcross.setVisibility(View.VISIBLE);
                    // imageView.setImageBitmap(bitmap);
                    myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 100);
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

        //     updateLocationUI();
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