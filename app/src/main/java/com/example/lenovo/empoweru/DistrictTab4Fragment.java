package com.example.lenovo.empoweru;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
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
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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


public class DistrictTab4Fragment extends Fragment implements  AdapterView.OnClickListener,AdapterView.OnItemSelectedListener{
    private static final String TAG = "BlockTab4Fragment";
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
    FrameLayout fl,flcross;TextView txtvwcross;
    ImageView im;
    int school_id;    Cursor blocks,clusters,schools,class_numbers,tot_students;
    String block_name="block",cluster_name="cluster",school_name="school";
    String myBase64Image="imbase64";
    Spinner spinner1,spinner2,spinner3;
    public static final List<Integer> school_id_list=  new ArrayList<>();
    List<String> blocks_list=new ArrayList<>();
    List<Integer> block_id_list=   new ArrayList<>();
    List<Integer> cluster_id_list= new ArrayList<>();
    List<Integer> class_ids=new ArrayList<>();
    List<String> cluster_list=new ArrayList<>();
    List<String> school_list=new ArrayList<>();
    private District_details2 te;
    private Cursor classes;
    private Uri imageUri;ImageButton imgbtn;
    private final static int TAKE_PICTURE=101;
    private District_submit_tables_classes bstc;
    private String time,date;
    private int slot_id;
    private int taken_by_id;
    private EditText remark;
    String remarks="remark";
    AlertDialog.Builder builder1;
    private ProgressDialog dialog;
    LocationManager locationManager;
    private String mLastUpdateTime;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

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
        View view = inflater.inflate(R.layout.fragment_district_tab4, container, false);
        init();
        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        rvPrueba = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
        btnCalcular = (Button) view.findViewById(R.id.submitx);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        rvPrueba.setLayoutManager(llm);
        bstc=new District_submit_tables_classes(getContext());
        te= new District_details2(getContext());
        blocks=te.block_details_data();
        remark = view.findViewById(R.id.remark);
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
        imgbtn=view.findViewById(R.id.imagebutton);
        spinner1=view.findViewById(R.id.spinner1);
        spinner2=view.findViewById(R.id.spinner2);
        spinner3=view.findViewById(R.id.spinner3);
        spinner2.setEnabled(false);
        spinner3.setEnabled(false);

        block_id_list.clear();
        blocks_list.clear();
        dialog=new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
    /*    final Timer t = new Timer();
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
        Cursor slotid=te.getSlot_id(times);
        slotid.moveToFirst();
        slot_id=slotid.getInt(1);
        Cursor block_id=te.data_data();
        block_id.moveToFirst();
        taken_by_id=block_id.getInt(1);
        block_id_list.add(0);
        blocks_list.add("Select Block");
        while (blocks.moveToNext()) {
            //   Toast.makeText(getContext(),blocks.getString(0) ,Toast.LENGTH_LONG).show();
            blocks_list.add(blocks.getString(0));
            block_id_list.add(blocks.getInt(1));
        }

        ArrayAdapter adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,blocks_list.toArray());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        int initialSelectedPosition1=spinner1.getSelectedItemPosition();
        spinner1.setSelection(initialSelectedPosition1, false);
        spinner1.setSelection(0, false); //clear selection
        spinner1.setOnItemSelectedListener(this);
        int i = 0;
 imgbtn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
        // takePhoto(view);
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

   //     adapter = new BlockPruebaAdapterStudent(class_num, nos);
     //   rvPrueba.setAdapter(adapter);
     //   rvPrueba.setNestedScrollingEnabled(false);
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
                //Toast.makeText(getContext(),"length="+ nullc,Toast.LENGTH_LONG).show();

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
                        bstc.insersubmittable1(latitude, longitude, accuracy, slot_id, taken_by_id,
                                date, time, school_id, block_name, cluster_name,
                                school_name, myBase64Image, remarks, 0);
                        // Cursor cur =  bstc.getLastAttendenceId();
                        int attend_id = 0;
                        Cursor curx = bstc.getAllData1();
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
                            bstc.insertsubmittable2(attend_id, class_ids.get(i), Integer.parseInt(escrito[i]), 0);
                            present = present + Integer.parseInt(escrito[i]);
                            total = total + Integer.parseInt(nos.get(i));
                        }
                        absent = total - present;
                        bstc.insertsubmittable3(attend_id, present, absent, block_name,
                                cluster_name, school_name, total, 0, remarks, date);
                        builder1 = new AlertDialog.Builder(getContext());
                        builder1.setTitle("Preliminary Attendence Done");
                        builder1.setMessage("Please sync to complete the process");
                        builder1.setCancelable(false);

                        builder1.setPositiveButton(
                                "ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Fragment DistrictTab3Fragment = new DistrictTab3Fragment();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        transaction.replace(R.id.frame_container,DistrictTab3Fragment ); // give your fragment container id in first parameter
                                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                        transaction.commit();
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                      //  Toast.makeText(getContext(), "Resultado : " + resultado + "\n" + test, Toast.LENGTH_LONG).show();
                    }    else {
                        builder1 = new AlertDialog.Builder(getContext());
                        builder1.setTitle("Please Attach Pic");
                        builder1.setMessage("Can't submit attendence without attaching pic");
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
                    else {
                        for (int i = 0; i < escrito.length; i++) {
                            resultado += Integer.parseInt(escrito[i]);
                            if (escrito[i] != null) {
                                test = test + escrito[i] + " ";
                            } else {
                                test = test + "_" + " ";
                            }
                        }
                        builder1 = new AlertDialog.Builder(getContext());
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
                     //   Toast.makeText(getContext(), "present cannot be greater than total" + "\n" + test, Toast.LENGTH_LONG).show();
                    }
                } else if (nullc > 0) {
                   builder1 = new AlertDialog.Builder(getContext());
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
                   // Toast.makeText(getContext(), "Enter values cannot be null" + "\n" + test, Toast.LENGTH_LONG).show();


                }}





        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //  Toast.makeText(getContext(),country[position] ,Toast.LENGTH_LONG).show();

        int block_id_pos,cluster_id_pos,schools_id_pos;
        Spinner spinner = (Spinner) arg0;
        if (spinner.getId() == R.id.spinner1) {
            // Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
            block_name = arg0.getItemAtPosition(position).toString();
            // Toast.makeText(getContext(), block_name, Toast.LENGTH_LONG).show();
            block_id_pos = position ;
            if(spinner2.isEnabled()==false) {
                spinner2.setEnabled(true);
            }
            insert_into_cluster_list(block_id_pos);

        } else if (spinner.getId() == R.id.spinner2) {


            cluster_name = arg0.getItemAtPosition(position).toString();
            //Toast.makeText(getContext(), cluster_name, Toast.LENGTH_LONG).show();
            if(spinner3.isEnabled()==false) {
                spinner3.setEnabled(true);
            }
            cluster_id_pos = position ;
            insert_into_school_list(cluster_id_pos);

        } else if (spinner.getId() == R.id.spinner3) {

            // if(block_name.equals("block")|| cluster_name.equals("cluster")){spinner.setEnabled(false);}
            //  else{
            //Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
            school_name = arg0.getItemAtPosition(position).toString();

            schools_id_pos = position ;

            prepareListData(schools_id_pos);
            //  Toast.makeText(getContext(), school_name+"\n"+block_name+"\n"+cluster_name, Toast.LENGTH_SHORT)
            //      .show();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void insert_into_cluster_list(int block_id_pos) {
        int  block_id=block_id_list.get(block_id_pos);
        clusters=te.block_cluster(block_id);
        cluster_id_list.clear();
        cluster_list.clear();
        cluster_id_list.add(0);
        cluster_list.add("Select Cluster");
        while (clusters.moveToNext()) {
            // Toast.makeText(getContext(),clusters.getString(0) ,Toast.LENGTH_LONG).show();
            cluster_list.add(clusters.getString(0));
            cluster_id_list.add(clusters.getInt(1));
        }
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,cluster_list.toArray());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        int initialSelectedPosition2=spinner2.getSelectedItemPosition();
        spinner2.setSelection(initialSelectedPosition2, false);
        spinner2.setSelection(0, false); //clear selection
        spinner2.setOnItemSelectedListener(this);

    }
    public void insert_into_school_list(int cluster_id_pos) {

        int cluster_id=cluster_id_list.get(cluster_id_pos);
        schools=te.block_cluster_school(cluster_id);
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


    private void prepareListData(Integer schools_id_pos) {
        // Toast.makeText(getContext(), String.valueOf(schools_id_pos), Toast.LENGTH_LONG).show();
        //  Toast.makeText(getContext(),school_id_list.size()+"" ,Toast.LENGTH_LONG).show();
        int schools_id=school_id_list.get(schools_id_pos);
        school_id=schools_id;
        //  adapter = new BlockPruebaAdapterStudent(class_num, nos);
        //        rvPrueba.setAdapter(adapter);
        // Toast.makeText(getContext(),school_id_list.toString()+"" ,Toast.LENGTH_LONG).show();
       // teacher_ids.clear();
       // present_status.clear();

       // listDataChild = new HashMap<String,List<String>>();

        StringBuffer buffer = new StringBuffer();
        classes=te.block_cluster_school_classes(schools_id);
class_num.clear();
class_ids.clear();

nos.clear();

        while (classes.moveToNext()) {

            class_num.add(classes.getString(1));
            nos.add(String.valueOf(classes.getInt(2)));
            class_ids.add(classes.getInt(0));


        }
   /*     class_ids.add(1);
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
            dupliescrito[i] = nos.get(i);
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

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
    @SuppressLint("WrongConstant")
  /*  @Override
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
                        bitmap = MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                //   ImageView imgvw=new ImageView(getContext());
                 //  ImageButton imgbtn =new ImageButton(getContext());
                    Bitmap converetdImage = getResizedBitmap(bitmap, 500);
                   ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageview);
                   imageView.setImageBitmap(converetdImage);
                   fl.setVisibility(View.GONE);
                   flcross.setVisibility(View.VISIBLE);

                   //fl.removeAllViews();
                //   fl.addView(imgvw);
                  // fl.addView(imgbtn);
                         myBase64Image = encodeToBase64(converetdImage, Bitmap.CompressFormat.JPEG, 100);
                     //   Bitmap myBitmapAgain = decodeBase64(myBase64Image);
                        //int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap)/1024;

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
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
         //   Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
              //      "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
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