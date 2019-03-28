package com.example.lenovo.empoweru;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class HMPruebaAdapterTecher extends RecyclerView.Adapter<HMPruebaAdapterTecher.PruebaViewHolder> {
    int checkPermissionk;
    List<String> dates,remarks,presents,absents,leaves,attendence_id,flag;
    AlertDialog.Builder builder1;
    String[] escrito;
    private HM_submit_tables_teachers hstt;
    private HM_details5 hmd;
    private sendingSMS sms;
    SharedPreferences loginData; SharedPreferences loginData2;
    ProgressDialog dialog,dialogphone;
    EditText etPhone, etMessage;
    Button btnSendSMS;
    String msg,phn;
    String status;
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private final String SENT = "SMS_SENT";
    private final String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    private Context context;

    public HMPruebaAdapterTecher( List<String> dates, List<String> remarks, List<String> present, List<String> absent
            , List<String> leave,List<String> attendence_id,List flag) {
      this.absents=absent;
      this.presents=present;
      this.leaves=leave;
      this.dates=dates;
      this.remarks=remarks;
      this.attendence_id=attendence_id;
      this.flag=flag;

      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hm_teacher_status_item_row,parent,false);
        hstt=new HM_submit_tables_teachers(v.getContext());
        context=v.getContext();
        hmd=new HM_details5(v.getContext());
        /*sms=new sendingSMS(v.getContext());
     //   loginData = v.getContext().getSharedPreferences("phone_details", Context.MODE_PRIVATE);
       //  loginData2 = v.getContext().getSharedPreferences("sms_status", Context.MODE_PRIVATE);
      //  checkPermissionk = ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.SEND_SMS);
        Cursor phone = hmd.phone_data();
        phone.moveToFirst();
        String num = phone.getString(0);
        String msg = "KANDHAMAL *1*694*2*4*5578,5582,5585,5587,5590,5592,5595*1,1,1,1,1,1,1*26.5121413*80.2243174*23.150999069213867*2018-06-21*1*2018-06-21 10:28*All present";
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("phn", num);
        editor.putString("msg", msg);
        editor.apply();*/
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {


         String date= dates.get(position);
         String remak=remarks.get(position);
         String leave=leaves.get(position);
         String present=presents.get(position);
         String absent=absents.get(position);
         String attendence_ids=attendence_id.get(position);
         String flags = flag.get(position);

         holder.bindProducto(date,remak,present,absent,leave,attendence_ids,flags);
    }
    private boolean checkPermission(String permission) {

        return checkPermissionk==PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public int getItemCount() {
        return absents.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView block_names,school_names,cluster_names,dates,remarks,presents,absents,leaves;
        Button sync;
        HttpResponse response;String message,status;
int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
String remarkt,datett,marked_ont,datet;
double lattitudet,longitudet;
String user_idt="",attendencestatust="";
        public PruebaViewHolder( View itemView) {
            super(itemView);


            dates = (TextView) itemView.findViewById(R.id.date);
            remarks = (TextView) itemView.findViewById(R.id.remacrk);
            absents = (TextView) itemView.findViewById(R.id.absent);
            presents = (TextView) itemView.findViewById(R.id.present);
            leaves = (TextView) itemView.findViewById(R.id.leave);
            sync = itemView.findViewById(R.id.sync);
            dialog=new ProgressDialog(itemView.getContext());
            dialogphone=new ProgressDialog(itemView.getContext());

        }

        public void bindProducto(final String date, final String remak, String present, String absent, String leave, final String attendence_id, String flag) {
            if (Integer.parseInt(flag) == 1) {
                sync.setClickable(false);
                sync.setText("");
                sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sync.getLayoutParams();
                lp.setMargins(0,13,20,0);
                sync.setLayoutParams(lp);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    sync.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.checked) );
                } else {
                    sync.setBackground(ContextCompat.getDrawable(context, R.drawable.checked));
                }
            } else if (Integer.parseInt(flag) == 0) {
                sync.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setCancelable(false);
                        Boolean isconnected = ConnectivityReceiver.isConnected();
                        if (isconnected) {
                            dialog.setCancelable(false);
                            dialog.setMessage("Syncing Data");
                            dialog.show();
                            Cursor res = hstt.getAllData11(Integer.parseInt(attendence_id));
                            Cursor rest = hstt.getAllData2(Integer.parseInt(attendence_id));
                            while (res.moveToNext()) {
                                lattitudet = res.getDouble(1);
                                longitudet = res.getDouble(2);
                                accuracyt = res.getInt(3);
                                slot_idt = res.getInt(4);
                                marked_ont = res.getString(6);
                                datet = res.getString(7);
                                school_idt = res.getInt(8);
                                remarkt = res.getString(9);
                                marked_by_id = res.getInt(5);
                            }
                            while (rest.moveToNext()) {
                                user_idt = user_idt + rest.getInt(1) + ",";
                                attendencestatust = attendencestatust + rest.getInt(2) + ",";
                            }
                            user_idt = user_idt.substring(0, user_idt.length() - 1);
                            attendencestatust = attendencestatust.substring(0, attendencestatust.length() - 1);
//Toast.makeText(view.getContext(),""+lattitudet+"\n"+longitudet+"\n"+accuracyt+"\n"
                            //     +slot_idt+"\n"+marked_ont+"\n"+datet+"\n"+school_idt+"\n"+remarkt+"\n"+marked_by_id+"\n" , Toast.LENGTH_LONG).show();
                            // Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost("http://smartschooldemo.empoweru.in/teacher_attendance/");


                            // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(12);
                            nameValuePair.add(new BasicNameValuePair("school_id", String.valueOf(school_idt)));
                            nameValuePair.add(new BasicNameValuePair("lattitude", String.valueOf(lattitudet)));
                            nameValuePair.add(new BasicNameValuePair("longnitude", String.valueOf(longitudet)));
                            nameValuePair.add(new BasicNameValuePair("accuracy", String.valueOf(accuracyt)));
                            nameValuePair.add(new BasicNameValuePair("markedon", marked_ont));
                            nameValuePair.add(new BasicNameValuePair("markedtype", String.valueOf(1)));
                            nameValuePair.add(new BasicNameValuePair("marked_by_id", String.valueOf(marked_by_id)));
                            nameValuePair.add(new BasicNameValuePair("slot_id", String.valueOf(slot_idt)));
                            nameValuePair.add(new BasicNameValuePair("remark", remarkt));
                            nameValuePair.add(new BasicNameValuePair("date", String.valueOf(datet)));
                            nameValuePair.add(new BasicNameValuePair("user_id1", user_idt));
                            nameValuePair.add(new BasicNameValuePair("attendencestatus1", attendencestatust));

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
                                //  Toast.makeText(view.getContext(),message+"\n"+status,Toast.LENGTH_LONG ).show();
                                Log.d("Http Response:", response.toString());
                            } catch (ClientProtocolException e) {
                                // writing exception to log
                                e.printStackTrace();
                            } catch (IOException e) {
                                // writing exception to log
                                e.printStackTrace();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            if (dialog != null) {
                                dialog.dismiss();
                            }

                            builder1.setTitle(status);
                            builder1.setMessage(message);
                            builder1.setPositiveButton(
                                    "ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();
                                            if (status.equals("Success")) {
                                                hstt.update(Integer.parseInt(attendence_id));
                                                sync.setClickable(false);
                                                sync.setText("");
                                                sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sync.getLayoutParams();
                                                lp.setMargins(0,13,20,0);
                                                sync.setLayoutParams(lp);
                                                final int sdk = android.os.Build.VERSION.SDK_INT;
                                                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                                    sync.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.checked) );
                                                } else {
                                                    sync.setBackground(ContextCompat.getDrawable(context, R.drawable.checked));
                                                }
                                            }

                                        }
                                    });
                        } else {
                            builder1.setTitle("No Internet Connection");
                            builder1.setMessage("Do you want to update attendance via mobile network?");

                            builder1.setPositiveButton(
                                    "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();

                                           /* sms.sendsms();
                                            String status = loginData2.getString("msg", "");
                                            if (status.equals("SMS sent successfully!")) {
                                                hstt.update(Integer.parseInt(attendence_id));
                                                dialogphone.setMessage(status);
                                                dialogphone.show();*/
                                            sync.setClickable(false);
                                            sync.setText("");
                                            sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                                            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sync.getLayoutParams();
                                            lp.setMargins(0,13,20,0);
                                            sync.setLayoutParams(lp);
                                            final int sdk = android.os.Build.VERSION.SDK_INT;
                                            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                                sync.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.checked) );
                                            } else {
                                                sync.setBackground(ContextCompat.getDrawable(context, R.drawable.checked));
                                            }
                                            //}
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
            }

            dates.setText("Date: " + date);
            remarks.setText("Remark: " + remak);
            absents.setText("A:"+absent);
            leaves.setText("L:"+leave);
            presents.setText("P:"+present);
        }
    }

    public class sendingSMS extends AppCompatActivity {


        public sendingSMS(Context context) {
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sending_sms);

            etPhone = (EditText) findViewById(R.id.phone);
            etMessage = (EditText) findViewById(R.id.omessage);

            btnSendSMS = (Button) findViewById(R.id.btnSendSMS);

            sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);
            deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED), 0);



            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefs = getSharedPreferences("phone_details", MODE_PRIVATE);
                    // String restoredText = prefs.getString("text", null);
                    //   if (restoredText != null) {
                    String messagem= prefs.getString("phn", "No name defined");//"No name defined" is the default value.
                    String telNrm  = prefs.getString("msg", "");



                    if (ContextCompat.checkSelfPermission(com.example.lenovo.empoweru.HMPruebaAdapterTecher.sendingSMS.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(com.example.lenovo.empoweru.HMPruebaAdapterTecher.sendingSMS.this, new String [] {Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    }
                    else
                    {
                        android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();

                        //phone - Recipient's phone number
                        //address - Service Center Address (null for default)
                        //message - SMS message to be sent
                        //piSent - Pending intent to be invoked when the message is sent
                        //piDelivered - Pending intent to be invoked when the message is delivered to the recipient
                        sms.sendTextMessage(telNrm, null, messagem, sentPI, deliveredPI);
                    }

                }
            });
        }
        public void sendsms()
        {
            // SharedPreferences prefs = getSharedPreferences("phone_details", MODE_PRIVATE);
            // String restoredText = prefs.getString("text", null);
            //   if (restoredText != null) {
            // String messagem= prefs.getString("phn", "No name defined");//"No name defined" is the default value.
            // String telNrm  = prefs.getString("msg", "");
            String num = "7521079555";
            String msg = "KANDHAMAL *1*694*2*4*5578,5582,5585,5587,5590,5592,5595*1,1,1,1,1,1,1*26.5121413*80.2243174*23.150999069213867*2018-06-21*1*2018-06-21 10:28*All present";


            if (ContextCompat.checkSelfPermission(com.example.lenovo.empoweru.HMPruebaAdapterTecher.sendingSMS.this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(com.example.lenovo.empoweru.HMPruebaAdapterTecher.sendingSMS.this, new String [] {Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
            else
            {
                android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();

                //phone - Recipient's phone number
                //address - Service Center Address (null for default)
                //message - SMS message to be sent
                //piSent - Pending intent to be invoked when the message is sent
                //piDelivered - Pending intent to be invoked when the message is delivered to the recipient
                sms.sendTextMessage(num, null, msg, sentPI, deliveredPI);
            }
        }
        @Override
        protected void onPause() {
            super.onPause();

            unregisterReceiver(smsSentReceiver);
            unregisterReceiver(smsDeliveredReceiver);
        }

        @Override
        protected void onResume() {
            super.onResume();

            //The deliveredPI PendingIntent does not fire in the Android emulator.
            //You have to test the application on a real device to view it.
            //However, the sentPI PendingIntent works on both, the emulator as well as on a real device.

            smsSentReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    switch (getResultCode())
                    {
                        case Activity.RESULT_OK:
                            Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                            status="SMS sent successfully!";
                            break;

                        //Something went wrong and there's no way to tell what, why or how.
                        case android.telephony.SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            Toast.makeText(context, "Generic failure!", Toast.LENGTH_SHORT).show();
                            status="Generic failure!";
                            break;

                        //Your device simply has no cell reception. You're probably in the middle of
                        //nowhere, somewhere inside, underground, or up in space.
                        //Certainly away from any cell phone tower.
                        case android.telephony.SmsManager.RESULT_ERROR_NO_SERVICE:
                            Toast.makeText(context, "No service!", Toast.LENGTH_SHORT).show();
                            status="No service!";
                            break;

                        //Something went wrong in the SMS stack, while doing something with a protocol
                        //description unit (PDU) (most likely putting it together for transmission).
                        case android.telephony.SmsManager.RESULT_ERROR_NULL_PDU:
                            Toast.makeText(context, "Null PDU!", Toast.LENGTH_SHORT).show();
                            status="Null PDU!";
                            break;

                        //You switched your device into airplane mode, which tells your device exactly
                        //"turn all radios off" (cell, wifi, Bluetooth, NFC, ...).
                        case android.telephony.SmsManager.RESULT_ERROR_RADIO_OFF:
                            Toast.makeText(context, "Radio off!", Toast.LENGTH_SHORT).show();
                            status="Radio off!";
                            break;

                    }
                    SharedPreferences loginData = getSharedPreferences("sms_status", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginData.edit();
                    editor.putString("status",status );
                    editor.apply();

                }
            };

            smsDeliveredReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    switch(getResultCode())
                    {
                        case Activity.RESULT_OK:
                            Toast.makeText(context, "SMS delivered!", Toast.LENGTH_SHORT).show();
                            break;

                        case Activity.RESULT_CANCELED:
                            Toast.makeText(context, "SMS not delivered!", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            };

            //register the BroadCastReceivers to listen for a specific broadcast
            //if they "hear" that broadcast, it will activate their onReceive() method
            registerReceiver(smsSentReceiver, new IntentFilter(SENT));
            registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
        }


    }

}
