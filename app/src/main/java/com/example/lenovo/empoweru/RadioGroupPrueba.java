package com.example.lenovo.empoweru;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.empoweru.leve4.user_details4;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RadioGroupPrueba extends android.support.v4.app.Fragment implements  AdapterView.OnClickListener,AdapterView.OnItemSelectedListener {

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
    private Uri imageUri;ImageButton imgbtn;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabbedteacher4, container, false);

        rvPrueba = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
        btnCalcular = (Button) view.findViewById(R.id.submitx);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(view.getContext());
        rvPrueba.setLayoutManager(llm);
        hstc=new HM_submit_tables_classes(view.getContext());
        hmd= new HM_details5(view.getContext());
        remark = view.findViewById(R.id.remark);
        imgbtn=view.findViewById(R.id.imagebutton);


        dialog=new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 1000);

        SimpleDateFormat sdfddate = new SimpleDateFormat("yyyyMMdd_HHmmss");
        SimpleDateFormat sdfdtime = new SimpleDateFormat("HH:mm");
        date=sdfddate.format(new Date());
        time=sdfdtime.format(new Date());
        Cursor slotid=hmd.getSlot_id(time);
        slotid.moveToFirst();
        slot_id=slotid.getInt(1);
        Cursor crp_id=hmd.data_data();
        crp_id.moveToFirst();
        taken_by_id=crp_id.getInt(1);

        int i = 0;
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto(view);
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
                Toast.makeText(getActivity(),"length="+ nullc,Toast.LENGTH_LONG).show();

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
                                time, date, school_id, myBase64Image, remarks, 0);
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
                        hstc.insertsubmittable3(attend_id, present, absent, total, 0, remarks, date);
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
                        Toast.makeText(getActivity(), "Resultado : " + resultado + "\n" + test, Toast.LENGTH_LONG).show();
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



    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            latitude =  loc.getLatitude();
            longitude= loc.getLongitude();
            accuracy=Math.round(loc.getAccuracy());
            Text = "My current location is: " + "Latitude = "
                    + loc.getLatitude() + "Longitude = " +loc.getLongitude()+"////" +loc.getAccuracy();
            Toast.makeText(getActivity(), Text, Toast.LENGTH_SHORT)
                    .show();

            dialog.cancel();
            Log.d("TAG", "Starting..");
        }

        @Override
        public void onProviderDisabled(String provider) {
            //  pd.dismiss();
            Toast.makeText(getActivity(), "Gps Disabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getActivity(), "Gps Enabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    private void prepareListData() {

        StringBuffer buffer = new StringBuffer();
        classes=hmd.class_list_data();
        class_num.clear();
        class_ids.clear();

        nos.clear();

     /*  while (classes.moveToNext()) {

            class_num.add(classes.getString(1));
            nos.add(String.valueOf(classes.getInt(2)));
            class_ids.add(classes.getInt(0));


        }*/
        class_ids.add(1);
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
        nos.add("10");
        dupliescrito = new String[class_num.size()];
        for (int i = 0; i < class_num.size(); i++) {
            dupliescrito[i] = nos.get(i);// to compare presenties vs total
            //  dupliescrito[i]= String.valueOf(crs.getInt(2))  ;
        }
        adapter = new BlockPruebaAdapterStudent(class_num, nos);
        rvPrueba.setAdapter(adapter);


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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.imgview);
                    ContentResolver cr = getActivity().getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        // imageView.setImageBitmap(bitmap);
                        myBase64Image = encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
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

}