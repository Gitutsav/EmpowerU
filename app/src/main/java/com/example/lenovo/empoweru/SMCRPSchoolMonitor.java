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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.empoweru.leve4.user_details4;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.util.ArrayUtils;
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
import com.nostra13.universalimageloader.utils.L;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


public class SMCRPSchoolMonitor extends Fragment implements AdapterView.OnClickListener,AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    private user_details4 te;
    TextView abcd;
    LocationManager locationManager;
    Double latitude=0.0, longitude=0.0;int slot_id;
    Cursor res;
    String Text;
    Cursor blocks,clusters,schools,teachers;
    HashMap<String, List<String>> listDataChild;
    Spinner spinner1,spinner2,spinner3,spinner0,spinner4;
    int accuracy=0;
    List<String> blocks_list=new ArrayList<>();
    List<Integer> block_id_list=   new ArrayList<>();
    List<String> issue_list=new ArrayList<>();
    List<Integer> issue_id_list=   new ArrayList<>();
    List<String> monitor_list=new ArrayList<>();
    List<Integer> monitor_id_list=   new ArrayList<>();
    List<String> cluster_list=new ArrayList<>();
    List<String> school_list=new ArrayList<>();
    String block_name="block",cluster_name="cluster",school_name="school";
    List<String> priceList = new ArrayList<String>();
    List<String> priceList1 = new ArrayList<String>();
    ImageButton imgbtn;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView; String myBase64Image="imbase64";
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    List<Integer> cluster_id_list= new ArrayList<>();
    List<Integer> teacher_ids=new ArrayList<>();
    List<Integer> present_status=new ArrayList<>();
    List<String> counter=new ArrayList<>();

    public static final List<Integer> school_id_list=  new ArrayList<>();

    FrameLayout fl;
    private ProgressDialog dialog;
    private SM_crp_submit_tables_school bstt;
    private user_details4 bd;
    private String time,date;int taken_by_id;
    int school_id;
    EditText remark;
    Button submit,btntst; AlertDialog.Builder builder1;
    private radrecadapter rrd;
    private RecyclerView packageRecyclerView;
    private SharedPreferences loginData;
    private PackageModel pm;
    List<String> teachers_listrrd=new ArrayList<>();
    List<Integer> teacher_idsrrd=new ArrayList<>();
    List<Integer> opg_id_list=new ArrayList<>();
    List<String> teachers_listrrd2=new ArrayList<>();
    List<Integer> teacher_idsrrd2=new ArrayList<>();
    List<Integer> opg_id_list2=new ArrayList<>();
    List<String> m_answer=new ArrayList<>();
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
    private int monitor_id_pos;
    String monitor_name;
    private List<Integer> isimage=new ArrayList<>();
    private List<Integer> isimage2=new ArrayList<>();
    private List<String> opc_id=new ArrayList<>();
    private LinearLayout flcross;
    private int issue_id_pos;
    private String issue_name;
    private TextView txtvwcross;
    private int no_of_images=0;
    List<String> myimages=new ArrayList<>();
    List<String> imgremark=new ArrayList<>();
String smcdate;EditText remarkltpic;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.sm_crp_school_monitor, container, false);
        //  Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.detach(currentFragment);
        //fragmentTransaction.attach(currentFragment);
        //fragmentTransaction.commit();
        init();
        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        rrd=new radrecadapter(getContext());
        // View view1=inflater.inflate(R.layout.smuse_in_radrec,container );
        remarkltpic=view.findViewById(R.id.remarkltpic);
        fl=view.findViewById(R.id.framelayout);
        fl.setVisibility(View.GONE);
        flcross=view.findViewById(R.id.framelayoutcross);
        flcross.setVisibility(View.GONE);
        imgbtn=view.findViewById(R.id.imagebutton);
        loginData = getActivity().getSharedPreferences("presenties", Context.MODE_PRIVATE);
        pm=new PackageModel(SMCRPSchoolMonitor.this);
        packageRecyclerView = (RecyclerView) view.findViewById(R.id.package_lst);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        packageRecyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(packageRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        packageRecyclerView.addItemDecoration(dividerItemDecoration);
        remark=view.findViewById(R.id.remark);
        submit=view.findViewById(R.id.submitx);
        bd=new user_details4(getContext());
        bstt=new SM_crp_submit_tables_school(getContext());
        dialog=new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Fetching Location , please wait.");
        dialog.show();
        txtvwcross=view.findViewById(R.id.txtvwcross);
        txtvwcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flcross.setVisibility(View.GONE);
                fl.setVisibility(View.VISIBLE);
            }
        });
        btntst=view.findViewById(R.id.buttontest);
     /*   final Timer t = new Timer();
       t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 200); // after 2 second (or 2000 miliseconds), the task will be active.

*/
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        Criteria criteria = new Criteria();
        expListView=(ExpandableListView) view.findViewById(R.id.lvExp);
        te= new user_details4(getContext());
        res=te.school_detail_data();
       // blocks=te.block_details_data();
        spinner0=view.findViewById(R.id.spinner0);
     //   spinner1=view.findViewById(R.id.spinner1);
        spinner2=view.findViewById(R.id.spinner2);
        spinner3=view.findViewById(R.id.spinner3);
        spinner4=view.findViewById(R.id.spinner4);
       // spinner2.setEnabled(false);
        spinner3.setEnabled(false);


      /*  values.put("lat", lat);
        values.put("longi",longi);
        values.put("accuracy",accuracy);
        values.put("slot_id",slot_id);
        values.put("taken_by_id",taken_by);
        values.put("taken_on_time",taken_on);
        values.put("datet",datet);
        values.put("school_id",school_id);
        values.put("school_name",school_name );
        values.put("block_name",block_name);
        values.put("cluster_name",cluster_name);
        values.put("remark",remark);
        values.put("flag",flag);
        */
        SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat timet = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datew = new SimpleDateFormat("dd/MM/yyyy_HH:mm");
        datewt =datew.format(new Date());
        time=timet.format(new Date());
        date=datet.format(new Date());
        SimpleDateFormat sdfdtime = new SimpleDateFormat("HH:mm");
        times=sdfdtime.format(new Date());
        Cursor slotid=bd.getSlot_id(times);
        slotid.moveToFirst();
        slot_id=slotid.getInt(1);
        Cursor block_id=bd.data_data();
        block_id.moveToFirst();
        taken_by_id=block_id.getInt(1);
        Cursor school_detail=bd.school_detail_data();
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


        btntst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   response();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getActivity().getSharedPreferences("SMCdate", MODE_PRIVATE);
                smcdate = prefs.getString("smcdate", "nullp");
                if (cluster_name.equals("cluster")
                        || school_name.equals("school")
                        || smcdate.equals("nullp")) {
                    builder1 = new AlertDialog.Builder(getContext());
                    builder1.setTitle("Can't submit!!");
                    builder1.setMessage("Enter all fields");
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* Fragment SMBlockSchoolRecord = new SMBlockSchoolRecord();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_container, SMBlockSchoolRecord); // give your fragment container id in first parameter
                                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                    transaction.commit();
                                    // startActivity(new Intent(getContext(),BlockTab1Fragment.class));*/
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } else {
                    bstt.insersubmittable1(latitude, longitude, accuracy, slot_id, taken_by_id, date, time, school_id, cluster_name, school_name, remarkltpic.getText().toString(), 0);
                    // int i=teacher_ids.size();
                    Cursor cur = bstt.getAllData1();
                    cur.moveToLast();
                    int attend_id = cur.getInt(0);
                    int absent = 0, present = 0, leave = 0;
                    res.moveToFirst();
                    clear();
                    response();
                    bstt.insertsubmittable5(attend_id, issue_id_pos, myBase64Image, remarkltpic.getText().toString());
                 //   Toast.makeText(view.getContext(), present_status.toString() + "\n" + counter.toString()
                 //           + "\n" + m_answer.toString() + "\n" + opc_id.toString() + no_of_images, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < m_answer.size(); i++) {
                        if (m_answer.get(i).equals("Date")) {

                            bstt.insertsubmittable2(attend_id, teacher_idsrrd.get(i + 1), Integer.parseInt(opc_id.get(i)), smcdate);

                        } else {
                            bstt.insertsubmittable2(attend_id, teacher_idsrrd.get(i + 1), Integer.parseInt(opc_id.get(i)), m_answer.get(i));

                        }


                        //a = a + String.valueOf(present_status.get(i) + "---" + counter.get(i - 1) + "\n");

                    }
                    bstt.insertsubmittable3(attend_id, cluster_name, school_name, remarkltpic.getText().toString(), datewt, 0);
                    for (int l = 0; l < no_of_images; l++) {
                        bstt.insertsubmittable4(attend_id, myBase64Image, "img_remark" + String.valueOf(l));
                    }
                    builder1 = new AlertDialog.Builder(getContext());
                    builder1.setTitle("Preliminary Attendence Done");
                    builder1.setMessage("Please sync to complete the process");
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    cluster_name="cluster";
                                    school_name="school";
                                    //smcdate="nullp";
                                   // editorsmcdate.putString("smcdate", datesmc);
                                   // editorsmcdate.apply();
                                    Fragment SMCRPSchoolRecord = new SMCRPSchoolRecord();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_container, SMCRPSchoolRecord); // give your fragment container id in first parameter
                                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                    transaction.commit();
                                    // startActivity(new Intent(getContext(),BlockTab1Fragment.class));
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

    /*    builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
*/

            }
        });

        monitor_id_list.clear();
        monitor_list.clear();
        monitor_id_list.add(0);
        monitor_list.add("Select Monitor");

        monitor_id_list.add(1);
        monitor_id_list.add(2);
        monitor_list.add("Regular");
        monitor_list.add("Periodic");
        issue_id_list.clear();
        issue_list.clear();
        issue_list.add("Select Issue");
        issue_id_list.add(0);
        Cursor issue=bd.question_category_data();
        while (issue.moveToNext()) {
            //   Toast.makeText(getContext(),blocks.getString(0) ,Toast.LENGTH_LONG).show();
            issue_list.add(issue.getString(0));
            issue_id_list.add(issue.getInt(1));
        }
        ArrayAdapter adapter4 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,issue_list.toArray());
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        int initialSelectedPosition4=spinner4.getSelectedItemPosition();
        spinner4.setSelection(initialSelectedPosition4, false);
        spinner4.setSelection(0, false); //clear selection
        spinner4.setOnItemSelectedListener(this);

        ArrayAdapter adapter0 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,monitor_list.toArray());
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner0.setAdapter(adapter0);
        int initialSelectedPosition0=spinner0.getSelectedItemPosition();
        spinner0.setSelection(initialSelectedPosition0, false);
        spinner0.setSelection(0, false); //clear selection
        spinner0.setOnItemSelectedListener(this);

        //Performing action onItemSelected and onNothing selected

        clusters = bd.cluster_details_data();
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
    public void clear(){

        opc_id.clear();
        m_answer.clear();
    }
    public void response() {
        String presenties= loginData.getString("presenttt", "No name defined");

        String joinedMinusBrackets = presenties.substring( 1, presenties.length() - 1);

        // String.split()
        String[] resplit = joinedMinusBrackets.split( ", ");
        String[] resplit_m_answer = joinedMinusBrackets.split( ", ");
        //      Toast.makeText(view.getContext(), Arrays.toString(resplit)+teacher_ids.size()+teacher_idsrrd.size(),Toast.LENGTH_SHORT ).show();
        present_status.clear();
        present_status= getIntegerArray(new ArrayList<String>(Arrays.asList(resplit)));
        opc_id.clear();
        m_answer.clear();
        //   String a="";
        for (int i=0;i<present_status.size();i++) {

            if ((i >= 1 && i <= (present_status.size() - 2))) {
                for (int j = 0; j < counter.size(); j++) {
                    if (present_status.get(i)==Integer.parseInt((counter.get(j).substring(2, 5)))) {
                        m_answer.add(counter.get(j).substring(5));
                        opc_id.add(counter.get(j).substring(0, 2));
                    }
                    //a = a + String.valueOf(present_status.get(i) + "---" + counter.get(i - 1) + "\n");
                }
            }
        }
        //   Toast.makeText(view.getContext(), present_status.toString()+"\n"+counter.toString()
        //    +"\n"+m_answer.toString()+"\n"+opc_id.toString()+no_of_images,Toast.LENGTH_SHORT ).show();
        // Toast.makeText(view.getContext(), a,Toast.LENGTH_SHORT ).show();
    }
    public void action(){
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
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //  Toast.makeText(getContext(),country[position] ,Toast.LENGTH_LONG).show();

        int block_id_pos,cluster_id_pos,schools_id_pos;
        Spinner spinner = (Spinner) arg0;
        if (spinner.getId() == R.id.spinner4) {
            if(position!=0) {
                issue_name = arg0.getItemAtPosition(position).toString();
                // Toast.makeText(getContext(), block_name, Toast.LENGTH_LONG).show();
                issue_id_pos = position ;
                //Toast.makeText(getContext(),issue_name+" "+issue_id_pos, Toast.LENGTH_LONG).show();
                fl.setVisibility(View.VISIBLE);
            }
            if(position==0) {
                fl.setVisibility(View.GONE);
            }

        }

        if (spinner.getId() == R.id.spinner0) {
            // Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
            monitor_name = arg0.getItemAtPosition(position).toString();
            // Toast.makeText(getContext(), block_name, Toast.LENGTH_LONG).show();
            monitor_id_pos = position ;

        /*    if(spinner2.isEnabled()==false) {
                spinner2.setEnabled(true);
            }
            insert_into_cluster_list(block_id_pos);*/
            if(position!=0) {
                SMradrecadapter recyclerViewAdapter = new
                        SMradrecadapter(getPackages(monitor_id_pos), getActivity());
                recyclerViewAdapter.getItemId(2);
                recyclerViewAdapter.notifyItemInserted(2);

                packageRecyclerView.setAdapter(recyclerViewAdapter);
                packageRecyclerView.setNestedScrollingEnabled(false);
            }
        }

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
            int schools_id=school_id_list.get(schools_id_pos);
            school_id=schools_id;
           // radrecadapter recyclerViewAdapter = new
          //          radrecadapter(getPackages(schools_id_pos),getActivity());
          //  packageRecyclerView.setAdapter(recyclerViewAdapter);
          //  packageRecyclerView.setNestedScrollingEnabled(false);
            //  packageRecyclerView.scrollToPosition(8);
            //  prepareListData(schools_id_pos);
            //  Toast.makeText(getContext(), school_name+"\n"+block_name+"\n"+cluster_name, Toast.LENGTH_SHORT)
            //      .show();

        }

    }

    private void insert_into_cluster_list(int block_id_pos) {
        int  block_id=block_id_list.get(block_id_pos);
       // clusters=te.block_cluster(block_id);
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


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public static int checkSelfPermission(String camera) {
        return 0;
    }


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

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            latitude =  loc.getLatitude();
            longitude= loc.getLongitude();
            accuracy=Math.round(loc.getAccuracy());
            Text = "My current location is: " + "Latitude = "
                    + loc.getLatitude() + "Longitude = " +loc.getLongitude()+"////" +loc.getAccuracy();
            //   Toast.makeText(getContext(), Text, Toast.LENGTH_SHORT)
            //         .show();

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
        teachers=te.block_cluster_school_teacher(schools_id);

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
        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

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
    private List<PackageModel> getPackages(Integer monitor_id_pos){
        List<PackageModel> modelList = new ArrayList<PackageModel>();
        int k=0;
        List<String> priceList = new ArrayList<String>();
        priceList.clear();counter.clear();
        // priceList.add("PRESENT");
        //priceList.add("ABSENT");
        //priceList.add("LEAVE");
        //    int schools_id=school_id_list.get(schools_id_pos);
        //  school_id=schools_id;
        //  Toast.makeText(getContext(),school_list.toString()+"" ,Toast.LENGTH_LONG).show();
        // Toast.makeText(getContext(),school_id_list.toString()+"" ,Toast.LENGTH_LONG).show();
        teacher_ids.clear();
        present_status.clear();
        //listDataHeader.clear();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,List<String>>();

        StringBuffer buffer = new StringBuffer();
        teachers=te.monitot_list();
        teachers_listrrd.clear();
        teacher_idsrrd.clear();
        opg_id_list.clear();
        teachers_listrrd.add("emptty");
        teacher_idsrrd.add(0);
        opg_id_list.add(0);
        isimage.clear();
        isimage.add(2);
        teachers_listrrd2.clear();
        teacher_idsrrd2.clear();
        opg_id_list2.clear();
        teachers_listrrd2.add("emptty");
        teacher_idsrrd2.add(0);
        opg_id_list2.add(0);
        isimage2.clear();
        isimage2.add(2);
        //  no_of_teachers=res.getColumnCount();
        no_of_images=0;
        if(monitor_id_pos==2) {
            while (teachers.moveToNext()) {
                // buffer.append("Date of Joining:"+ res.getString(0));

                teachers_listrrd.add(teachers.getString(0));
                teacher_idsrrd.add(teachers.getInt(7));
                isimage.add(teachers.getInt(1));
                opg_id_list.add(teachers.getInt(3));

                if(teachers.getInt(1) == 0){
                    no_of_images++;
                }

            }
        }

        else if(monitor_id_pos==1){
            while (teachers.moveToNext()) {
                // buffer.append("Date of Joining:"+ res.getString(0));
                if((teachers.getInt(6)==1)){
                    teachers_listrrd.add(teachers.getString(0));
                    teacher_idsrrd.add(teachers.getInt(7));
                    isimage.add(teachers.getInt(1));
                    opg_id_list.add(teachers.getInt(3));
                    if(teachers.getInt(1) == 0){
                        no_of_images++;
                    }
                }
                else {
                    teachers_listrrd2.add(teachers.getString(0));
                    teacher_idsrrd2.add(teachers.getInt(7));
                    isimage2.add(teachers.getInt(1));
                    opg_id_list2.add(teachers.getInt(3));
                }
            }
        }
        teachers_listrrd.add("emptylast");
        teacher_idsrrd.add(10);
        opg_id_list.add(10);
        isimage.add(10);
        teachers_listrrd2.add("emptylast");
        teacher_idsrrd2.add(10);
        opg_id_list2.add(10);
        isimage2.add(10);
        StringBuffer loopedData=new StringBuffer();
        //  loopedData.append(opg_id_list.toString()+"\n");
        //  Toast.makeText(getActivity(),teachers_listrrd.size()+"" ,Toast.LENGTH_SHORT ).show();
        for(int i=0;i<teachers_listrrd.size();i++){
            Cursor option=te.option_list_data();
            //priceList.clear();
            int g=0;

            priceList = new ArrayList<String>();
            while (option.moveToNext()) {
                //option.getPosition();
                // buffer.append("Date of Joining:"+ res.getString(0));
                //loopedData.append(opg_id_list.get(i)+" "+option.getInt(4)+"/");
                if(((opg_id_list.get(i))==option.getInt(4))&&((teacher_idsrrd.get(i))==option.getInt(5))){

                    String s = String.format("%02d", option.getInt(0));
                    String s2 = String.format("%03d", (i*10)+(g+1));
                    counter.add(s+s2+(option.getString(3)));
                    priceList.add(option.getString(3) + String.valueOf(isimage.get(i)));

                    //    priceList.add(option.getString(3));

                    g++;

                }

            }
            loopedData.append(priceList.toString()+"\n" );
            loopedData.append("_____________________");
            priceList1.clear();
           /* priceList1.add("PRESENT");
            priceList1.add("ABSENT");
            priceList1.add("LEAVE");
            priceList1.add("Pool");*/
            //  priceList1=priceList;
            //   teachers_listrrd.add("emptylast");
            //  teacher_idsrrd.add(10);
            if(i==(teachers_listrrd.size()-1)) {
                priceList = new ArrayList<String>();
                priceList.add("last");
                priceList.add("last");
                priceList.add("last");
            }
            modelList.add(new PackageModel(teachers_listrrd.get(i), priceList));
            //  Toast.makeText(getApplicationContext(),teachers_list.size()+"/"+priceList.size() ,Toast.LENGTH_SHORT ).show();
            // Toast.makeText(getContext(),priceList.toString() ,Toast.LENGTH_LONG ).show();

        }
//        Toast.makeText(getContext(),loopedData.toString() ,Toast.LENGTH_LONG ).show();


        //modelList.add(new PackageModel(teachers_listrrd.get(teachers_listrrd.size()-1), priceList));
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

        //   updateLocationUI();
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null ) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (!prefs.getBoolean("firstTime", false)) {
                // <---- run your one time code here
                // Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude() + ", " +
                //         "Lng: " + mCurrentLocation.getLongitude()+"acc"+mCurrentLocation.getAccuracy(), Toast.LENGTH_SHORT).show();
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

                        //   updateLocationUI();
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
