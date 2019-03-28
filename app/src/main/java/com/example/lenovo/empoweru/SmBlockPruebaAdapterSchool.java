package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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


public class SmBlockPruebaAdapterSchool extends RecyclerView.Adapter<SmBlockPruebaAdapterSchool.PruebaViewHolder> {
    private SM_Block_submit_tables_school bstt;
    List<String> block_names,school_names,cluster_names,dates,remarks,attendence_id,flag;
    AlertDialog.Builder builder1;
    String[] escrito;
ProgressDialog dialog,mydialog;
    SharedPreferences prefs;
    Context context;
    public SmBlockPruebaAdapterSchool(List<String> block_names, List<String> school_names, List<String> cluster_names
            , List<String> dates, List<String> remarks, List<String> attendence_id, List<String> flag) {

      this.block_names=block_names;
      this.school_names=school_names;
      this.cluster_names=cluster_names;
      this.dates=dates;
      this.remarks=remarks;
      this.attendence_id=attendence_id;
      this.flag=flag;

      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sm_block_school_status_item_row,parent,false);
        this.bstt=new SM_Block_submit_tables_school(v.getContext());

        context=v.getContext();
        dialog=new ProgressDialog(v.getContext());
      //  mydialog.dismiss();
       // SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        return new PruebaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
         String blockname=block_names.get(position);
         String schoolname=school_names.get(position);
         String clustername=cluster_names.get(position);
         String date= dates.get(position);
         String remak=remarks.get(position);

         String attendence_ids=attendence_id.get(position);
         String flags=flag.get(position);

         holder.bindProducto(blockname,schoolname,clustername,date,remak,attendence_ids,flags);
    }

    @Override
    public int getItemCount() {
        return block_names.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView block_names,school_names,cluster_names,dates,remarks,presents,absents,leaves;
        Button sync;ProgressBar pb;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message="",status="";
        double lattitudet,longitudet;
        String user_idt="",attendencestatust="",mq_idt="",op_id="",m_answert="",quesimg="",quesimgremark="";
        String extraimg="",extraimgcategor="";
        public PruebaViewHolder( View itemView) {
            super(itemView);
            block_names = (TextView) itemView.findViewById(R.id.block);
            school_names = (TextView) itemView.findViewById(R.id.school);
            cluster_names = (TextView) itemView.findViewById(R.id.cluster);
            dates = (TextView) itemView.findViewById(R.id.date);
            remarks = (TextView) itemView.findViewById(R.id.remacrk);
            sync = itemView.findViewById(R.id.sync);
            pb=itemView.findViewById(R.id.progressbar);
            pb.setVisibility(View.GONE);
  /*          sync.setOnClickListener(new View.OnClickListener() {
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

        public void bindProducto(String blockname, String schoolname, String clustername, final String date, String remak, final String attendence_id, String flag){

            if(Integer.parseInt(flag)==1)
            {pb.setVisibility(View.GONE);
                sync.setClickable(false);
                sync.setText("");
                sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sync.getLayoutParams();

                // Set TextView layout margin 25 pixels to all side
                // Left Top Right Bottom Margin
                lp.setMargins(0,5,15,0);

                // Apply the updated layout parameters to TextView
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
                      //  pb.setVisibility(View.VISIBLE);
                        Boolean isconnected = ConnectivityReceiver.isConnected();
                        if (isconnected) {

                            dialog.setCancelable(false);
                            dialog.setMessage("Syncing Data");
                            dialog.setProgress(500);
                            dialog.show();
                            Runnable progressRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    dialog.cancel();
                                }
                            };

                            Handler pdCanceller = new Handler();
                            pdCanceller.postDelayed(progressRunnable, 1500);

                         /*  dialog.setCancelable(false);
                              dialog.setMessage("Syncing Data");
                              dialog.setProgress(500);
                              dialog.show();*/
                            //Syncing part
                            // mydialog = progressAlertDialog.showProgressDialog(context, "some message");

                            Cursor res = bstt.getAllData11(Integer.parseInt(attendence_id));
                            Cursor rest = bstt.getAllData22(Integer.parseInt(attendence_id));
                            Cursor res4 = bstt.getAllData44(Integer.parseInt(attendence_id));
                            Cursor rest5 = bstt.getAllData55(Integer.parseInt(attendence_id));

                            while (res.moveToNext()) {
                                lattitudet = res.getDouble(1);
                                longitudet = res.getDouble(2);
                                accuracyt = res.getInt(3);
                                //  slot_idt=res.getInt(4);
                                marked_ont = res.getString(6);
                                datet = res.getString(7);
                                school_idt = res.getInt(8);
                                remarkt = res.getString(12);
                                marked_by_id = res.getInt(5);
                            }
                            while (rest.moveToNext()) {
                                mq_idt = mq_idt + rest.getInt(1) + ",";
                                op_id = op_id + rest.getInt(2) + ",";
                                m_answert = m_answert + rest.getString(3) + ",";
                            }
                            while (res4.moveToNext()) {
                                quesimg = quesimg + res4.getString(1) + ",";
                                quesimgremark = quesimgremark + res4.getString(2) + ",";

                            }
                            while (rest5.moveToNext()) {
                                extraimg = rest5.getString(2);
                                extraimgcategor = rest5.getString(1);

                            }
                            quesimg = quesimg.substring(0, quesimg.length() - 1);
                            quesimgremark = quesimgremark.substring(0, quesimgremark.length() - 1);
                            mq_idt = mq_idt.substring(0, mq_idt.length() - 1);
                            op_id = op_id.substring(0, op_id.length() - 1);
                            m_answert = m_answert.substring(0, m_answert.length() - 1);
                            //  Toast.makeText(view.getContext(),mq_idt+"\n"+op_id+"\n" +m_answert, Toast.LENGTH_LONG).show();
                            //   Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost(
                                    "http://smartschooldemo.empoweru.in/monitor/");


                            // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(16);
                            nameValuePair.add(new BasicNameValuePair("record_schoolid", String.valueOf(school_idt)));
                            nameValuePair.add(new BasicNameValuePair("record_lat", String.valueOf(lattitudet)));
                            nameValuePair.add(new BasicNameValuePair("record_longi", String.valueOf(longitudet)));
                            nameValuePair.add(new BasicNameValuePair("record_accuracy", String.valueOf(accuracyt)));
                            nameValuePair.add(new BasicNameValuePair("record_timstmp", marked_ont));
                            nameValuePair.add(new BasicNameValuePair("mark_type", String.valueOf(3)));
                            nameValuePair.add(new BasicNameValuePair("mcrp", String.valueOf(marked_by_id)));
                            nameValuePair.add(new BasicNameValuePair("record_date_time", datet));
                            nameValuePair.add(new BasicNameValuePair("mq_id1", mq_idt));
                            nameValuePair.add(new BasicNameValuePair("optionid1", op_id));
                            nameValuePair.add(new BasicNameValuePair("m_answer1", m_answert));
                            nameValuePair.add(new BasicNameValuePair("record_imgremark", quesimgremark));
                            nameValuePair.add(new BasicNameValuePair("record_image", quesimg));
                            nameValuePair.add(new BasicNameValuePair("record_categ_id", extraimgcategor));
                            nameValuePair.add(new BasicNameValuePair("record_extra_image", extraimg));
                            nameValuePair.add(new BasicNameValuePair("record_remark", remarkt));


                            // Url Encoding the POST parameters
                            try {
                                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                            } catch (UnsupportedEncodingException e) {
                                // writing error to Log
                                e.printStackTrace();
                            }

                            // Making HTTP Request
                            try {
                                HttpResponse response = httpClient.execute(httpPost);
                                String responseBody = EntityUtils.toString(response.getEntity());
                                JSONObject obj = new JSONObject(responseBody);
                                message = obj.getString("message");
                                status = obj.getString("status");
                                // writing response to log
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
                          /*  if (dialog != null) {
                                dialog.dismiss();
                            }*/

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
                                            pb.setVisibility(View.GONE);
                                            if(status.equals("Success")) {

                                               bstt.update(Integer.parseInt(attendence_id));
                                                sync.setClickable(false);
                                                sync.setText("");
                                                //sync.setBackgroundColor(Color.GREEN);

                                                sync.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
                                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sync.getLayoutParams();

                                                // Set TextView layout margin 25 pixels to all side
                                                // Left Top Right Bottom Margin
                                                lp.setMargins(0,5,15,0);

                                                // Apply the updated layout parameters to TextView
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
                });
            }

           block_names.setText(blockname);
           school_names.setText(schoolname);
           cluster_names.setText(clustername);
           dates.setText("Date: "+date);
           remarks.setText("Remark: "+remak);

        }

    }
}