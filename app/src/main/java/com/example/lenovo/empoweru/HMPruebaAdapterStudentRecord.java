package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class HMPruebaAdapterStudentRecord extends RecyclerView.Adapter<HMPruebaAdapterStudentRecord.PruebaViewHolder> {

    List<String> school_names,cluster_names,dates,remarks,presents,absents,leaves,attendence_id,flag;
    AlertDialog.Builder builder1;
    String[] escrito;
    private HM_submit_tables_classes hstc;
    ProgressDialog dialog;
    private Context context;

    public HMPruebaAdapterStudentRecord(List<String> dates, List<String> remarks, List<String> present, List<String> absent
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hm_student_status_item_row,parent,false);
        hstc=new HM_submit_tables_classes(v.getContext());
        context=v.getContext();
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

    @Override
    public int getItemCount() {
        return absents.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView school_names,cluster_names,dates,remarks,presents,absents,leaves;
        Button sync;
        HttpResponse response;String message,status;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet,myimage;
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
            /*sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder1 = new AlertDialog.Builder(view.getContext());

                    builder1.setCancelable(false);
                    Boolean isconnected= ConnectivityReceiver.isConnected();
                    if(isconnected){
                        builder1.setTitle("Synced Successfully");
                        builder1.setPositiveButton(
                                "ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                        dialog.cancel();
                                        sync.setClickable(false);
                                        sync.setText("Done");
                                        sync.setBackgroundColor(44);


                                    }
                                });
                    }
                    else{
                        builder1.setTitle("No Internet Connection");
                        builder1.setMessage("Do you want to update attendance via mobile network?");

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                    dialog.cancel();
                                    sync.setClickable(false);
                                    sync.setText("Done");
                                    sync.setBackgroundColor(44);


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
            });*/
        }

        public void bindProducto(String date, String remak, String present, String absent, String leave, final String attendence_id, String flag){
            if(Integer.parseInt(flag)==1)
            {
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
            else if(Integer.parseInt(flag)==0){
                sync.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setCancelable(false);
                        Boolean isconnected= ConnectivityReceiver.isConnected();
                        if(isconnected){
                            dialog.setCancelable(false);
                            dialog.setMessage("Syncing Data");
                            dialog.show();

                            // Syncing part
                            Cursor res = hstc.getAllData11(Integer.parseInt(attendence_id));
                            Cursor rest=hstc.getAllData22(Integer.parseInt(attendence_id));
                            while (res.moveToNext()) {
                                lattitudet=res.getDouble(1);
                                longitudet=res.getDouble(2);
                                accuracyt=res.getInt(3);
                                slot_idt=res.getInt(4);
                                marked_ont=res.getString(6);
                                datet=res.getString(7);
                                school_idt=res.getInt(8);
                                remarkt=res.getString(10);
                                marked_by_id=res.getInt(5);
                                myimage=res.getString(9);
                            }
                            while (rest.moveToNext()){
                                user_idt=user_idt+rest.getInt(1)+",";
                                attendencestatust=attendencestatust+rest.getInt(2)+",";
                            }
                            user_idt=user_idt.substring(0, user_idt.length() - 1);
                            attendencestatust=attendencestatust.substring(0,attendencestatust.length()-1 );
                       //     Toast.makeText(view.getContext(),""+lattitudet+"\n"+longitudet+"\n"+accuracyt+"\n"
                        //       +slot_idt+"\n"+marked_ont+"\n"+datet+"\n"+school_idt+"\n"+remarkt+"\n"+marked_by_id+"\n" , Toast.LENGTH_LONG).show();
                       //      Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost(
                                    "http://smartschooldemo.empoweru.in/student_attendance/");



                            // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(13);
                            nameValuePair.add(new BasicNameValuePair("school_id", String.valueOf(school_idt)));
                            nameValuePair.add(new BasicNameValuePair("lattitude", String.valueOf(lattitudet)));
                            nameValuePair.add(new BasicNameValuePair("longnitude", String.valueOf(longitudet)));
                            nameValuePair.add(new BasicNameValuePair("accuracy", String.valueOf(accuracyt)));
                            nameValuePair.add(new BasicNameValuePair("markedon",marked_ont));
                            nameValuePair.add(new BasicNameValuePair("markedtype", String.valueOf(1)));
                            nameValuePair.add(new BasicNameValuePair("marked_by_id", String.valueOf(marked_by_id)));
                            nameValuePair.add(new BasicNameValuePair("slot_id", String.valueOf(slot_idt)));
                            nameValuePair.add(new BasicNameValuePair("remark",remarkt));
                            nameValuePair.add(new BasicNameValuePair("date", String.valueOf(datet)));
                            nameValuePair.add(new BasicNameValuePair("my_image", myimage));
                            nameValuePair.add(new BasicNameValuePair("class_id1", user_idt));
                            nameValuePair.add(new BasicNameValuePair("attendence_count1",attendencestatust));

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
                                message=obj.getString("message");
                                status=obj.getString("status");
                                  Toast.makeText(view.getContext(),message+"\n"+status,Toast.LENGTH_LONG ).show();
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



                            if(dialog!=null){
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
                                            if(status.equals("Success")) {
                                                hstc.update(Integer.parseInt(attendence_id));
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
                        }
                        else
                        {
                            builder1.setTitle("No Internet Connection");
                            builder1.setMessage("Do you want to update attendance via mobile network?");

                            builder1.setPositiveButton(
                                    "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();
                                            sync.setClickable(false);
                                            sync.setText("");
                                            sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                                            final int sdk = android.os.Build.VERSION.SDK_INT;
                                            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                                sync.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.checked) );
                                            } else {
                                                sync.setBackground(ContextCompat.getDrawable(context, R.drawable.checked));
                                            }


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

           dates.setText("Date: "+date);
           remarks.setText("Remark: "+remak);
            absents.setText("A:"+absent);
            leaves.setText("T:"+leave);
            presents.setText("P:"+present);
        }

    }
}
