package com.example.lenovo.empoweru;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by User on 2/28/2017.
 */

public class HMApplyLeave extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener, AdapterView.OnItemSelectedListener  {
    private RecyclerView recyclerview;
    List<String> dates=new ArrayList<>(),remarks=new ArrayList<>(),presents=new ArrayList<>(),
            absents=new ArrayList<>(),leaves=new ArrayList<>(),attendence_ids=new ArrayList<>(),
            flag=new ArrayList<>();
    private HM_submit_tables_teachers sbmit_details;
    HMPruebaAdapterTecher adapter;
    Button submit; int grievance_id_pos;
    ImageView fd,td;String toDate,fromDate;
    TextView fdate,tdate;private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;
    String myBase64Image="imbase64";
    private ImageButton imgbtn;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    FrameLayout flcross;TextView txtvwcross;LinearLayout fl;
    private HM_details5 hm_details5;
    private Spinner spinner1;
    List<String> grievance_list=new ArrayList<>();
    List<Integer> grievance_id_list=new ArrayList<>();
    private String grievance_name;  SharedPreferences pref;
    String userid, roleid;String reason;EditText remarkt;
    private String timestamp;
    HttpResponse response;String message,status;
    AlertDialog.Builder builder1;
    String[] escrito;Context context;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hm_apply_leave, container, false);
        hm_details5 = new HM_details5(getContext());
        context=view.getContext();
        remarkt=view.findViewById(R.id.remark);
        imgbtn=view.findViewById(R.id.imagebutton);
        submit=view.findViewById(R.id.submitx);
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
fdate=view.findViewById(R.id.from_date);
tdate=view.findViewById(R.id.to_date);
fd = view.findViewById(R.id.f_date);
        td = view.findViewById(R.id.t_date);
     fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date =year+"-"+month+"-"+day;
                fromDate=date;
                fdate.setText(date);
            }
        };
       td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year1 = cal1.get(Calendar.YEAR);
                int month1 = cal1.get(Calendar.MONTH);
                int day1 = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year1,month1,day1);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date1 =year+"-"+month+"-"+day;
                toDate=date1;
                tdate.setText(date1);
            }
        };
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
        spinner1=view.findViewById(R.id.spinner1);
        grievance_list.clear();
        grievance_id_list.clear();
        grievance_id_list.add(0);
        grievance_list.add("Select ");
        Cursor grievance = hm_details5.leave_data();
        while (grievance.moveToNext()) {
            //   Toast.makeText(getContext(),blocks.getString(0) ,Toast.LENGTH_LONG).show();
            grievance_list.add(grievance.getString(0));
            grievance_id_list.add(grievance.getInt(1));
        }

        ArrayAdapter adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,grievance_list.toArray());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        int initialSelectedPosition1=spinner1.getSelectedItemPosition();
        spinner1.setSelection(initialSelectedPosition1, false);
        spinner1.setSelection(0, false); //clear selection
        spinner1.setOnItemSelectedListener(this);
        Cursor user_details=hm_details5.data_data();
        user_details.moveToFirst();

        userid = String.valueOf(user_details.getInt(1));
        roleid = String.valueOf(user_details.getInt(0));

        SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        timestamp=datet.format(new Date());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reason = remarkt.getText().toString();
                Boolean isconnected = ConnectivityReceiver.isConnected();
                if (isconnected) {
                    HttpClient httpClient = new DefaultHttpClient();
                    // Creating HTTP Post
                    HttpPost httpPost = new HttpPost(
                            "http://smartschooldemo.empoweru.in/leave_request/");


                    // Syncing part
                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(8);
                    nameValuePair.add(new BasicNameValuePair("userid", userid));
                    nameValuePair.add(new BasicNameValuePair("roleid", roleid));
                    nameValuePair.add(new BasicNameValuePair("levid", String.valueOf(grievance_id_pos)));
                    nameValuePair.add(new BasicNameValuePair("fdate", fromDate));
                    nameValuePair.add(new BasicNameValuePair("tdate", toDate));
                    nameValuePair.add(new BasicNameValuePair("le_reason", reason));
                    nameValuePair.add(new BasicNameValuePair("timestamp", timestamp));
                    nameValuePair.add(new BasicNameValuePair("imagedd", myBase64Image));

                    try {
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                    } catch (UnsupportedEncodingException e) {
                        // writing error to Log
                        e.printStackTrace();
                    }

                    // Making HTTP Request
                    try {
                        response = httpClient.execute(httpPost);
                        String responseBody = EntityUtils.toString(response.getEntity());
                        JSONObject obj = new JSONObject(responseBody);
                        message = obj.getString("message");
                        status = obj.getString("status");
                        // Toast.makeText(view.getContext(), message + "\n", Toast.LENGTH_LONG).show();
                        // Log.d("Http Response:", response.toString());
                    } catch (ClientProtocolException e) {
                        // writing exception to log
                        e.printStackTrace();
                    } catch (IOException e) {
                        // writing exception to log
                        e.printStackTrace();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setCancelable(false);
                    builder1.setTitle(status);
                    builder1.setMessage(message);
                    builder1.setPositiveButton(
                            "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                    dialog.cancel();
                                    if (status.equals("Success")) {
                                        context.startActivity(new Intent(context,context.getClass()));
                                    }

                                }
                            });
                } else {
                    builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setCancelable(false);
                    builder1.setTitle("No Internet Connection");
                    builder1.setMessage("Do you want to apply leave via mobile network?");

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                    dialog.cancel();


                                }

                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                }
                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        return view;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.spinner1) {
            if(i!=0) {
                // Toast.makeText(getContext(),position ,Toast.LENGTH_LONG).show();
                grievance_name = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getContext(), grievance_name, Toast.LENGTH_LONG).show();
                grievance_id_pos = i;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
