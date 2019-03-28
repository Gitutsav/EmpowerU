package com.example.lenovo.empoweru;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class BlockPruebaAdapterLeaveRecord extends RecyclerView.Adapter<BlockPruebaAdapterLeaveRecord.PruebaViewHolder> {
    private Block_submit_tables_teachers bstt;
    List<String> req_ids,req_ons,lts,fds,tds,reasons,statuss,names,statusids,leave_req_ids;
    AlertDialog.Builder builder1;
    String[] escrito;
ProgressDialog dialog,candialog,appdialog;Context context;
    public BlockPruebaAdapterLeaveRecord(List<String> req_idsh , List<String> req_onh,
                                         List<String> lth
            , List<String> fdh, List<String> tdh, List<String> reasonsh, List<String> statussh,
                                         List<String> nameh,List<String> statusidh,List<String> leave_req_idh) {
      this.req_ids=req_idsh;
      this.req_ons=req_onh;
      this.lts=lth;
      this.fds=fdh;
      this.tds=tdh;
      this.reasons=reasonsh;
      this.statuss=statussh;
      this.names=nameh;
      this.statusids=statusidh;
      this.leave_req_ids=leave_req_idh;
      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_leave_record_status_item_row,parent,false);
        dialog=new ProgressDialog(v.getContext());
        appdialog=new ProgressDialog(v.getContext());
        candialog=new ProgressDialog(v.getContext());
        this.bstt=new Block_submit_tables_teachers(v.getContext());
     context=v.getContext();

        return new PruebaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
String req_id=req_ids.get(position);
String req_on=req_ons.get(position);
String lt=lts.get(position);
String fd=fds.get(position);
String td=tds.get(position);
String reason=reasons.get(position);
String status= statuss.get(position);
String name=names.get(position);
String statusid=statusids.get(position);
String leave_req_id=leave_req_ids.get(position);
         holder.bindProducto(req_id,req_on,lt,fd,td,reason,status,name,statusid,leave_req_id);
    }

    @Override
    public int getItemCount() {
        return req_ids.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView req_idt,req_ont,ltt,frt,tdt,reasont,statust,namet;Button approved,cancelled;
        Button sync; Block_details3 hm_details5;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message,status;
        String status1;
        double lattitudet,longitudet;String userid;
        String user_idt="",attendencestatust="";HttpResponse response;
        public PruebaViewHolder( View itemView) {
            super(itemView);
            req_idt = (TextView) itemView.findViewById(R.id.req_id);
            req_ont = (TextView) itemView.findViewById(R.id.req_on);
            ltt = (TextView) itemView.findViewById(R.id.lt);
            frt = (TextView) itemView.findViewById(R.id.fd);
            tdt = (TextView) itemView.findViewById(R.id.td);
            reasont = (TextView) itemView.findViewById(R.id.reason);
            statust = (TextView) itemView.findViewById(R.id.status);
            namet=itemView.findViewById(R.id.name);
            approved=itemView.findViewById(R.id.approved);
            cancelled=itemView.findViewById(R.id.cancelled);
            hm_details5=new Block_details3(itemView.getContext());

//550367886180

        }

        @SuppressLint("NewApi")
        public void bindProducto(String req_id, String req_on, String lt, String fd, String td,
                                 String reason, String status, String name, String statusid, final String leave_req_id){

            Cursor user_details=hm_details5.data_data();
            user_details.moveToFirst();
            userid = String.valueOf(user_details.getInt(1));
            if (Integer.parseInt(statusid) == 1) {
                //sync.setText("Done");
               // sync.setBackgroundColor(44);
                approved.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setCancelable(false);
                        Boolean isconnected = ConnectivityReceiver.isConnected();
                       /* candialog.setCancelable(false);
                        candialog.setMessage("Syncing Data");
                        candialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        candialog.setIndeterminate(true);
                        candialog.show();*/
                        if (isconnected) {
                            appdialog.setCancelable(false);
                            appdialog.setMessage("Syncing Data");
                            appdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            appdialog.setIndeterminate(true);
                            appdialog.show();

//Toast.makeText(view.getContext(),""+lattitudet+"\n"+longitudet+"\n"+accuracyt+"\n"
                            //     +slot_idt+"\n"+marked_ont+"\n"+datet+"\n"+school_idt+"\n"+remarkt+"\n"+marked_by_id+"\n" , Toast.LENGTH_LONG).show();
                            // Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost("http://smartschooldemo.empoweru.in/leave_approve/");


                                    // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
                            nameValuePair.add(new BasicNameValuePair("leave_req", leave_req_id));
                            nameValuePair.add(new BasicNameValuePair("status", String.valueOf(1)));
                            nameValuePair.add(new BasicNameValuePair("userid", userid));


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
                                status1 = obj.getString("status");
                                // Toast.makeText(view.getContext(),message+"\n"+status1,Toast.LENGTH_LONG ).show();
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


                        /*    if (appdialog != null) {
                                appdialog.dismiss();
                            }
*/
                            builder1.setTitle(status1);
                            builder1.setMessage(message);
                            builder1.setPositiveButton(
                                    "ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();
                                          //  if (status1.equals("Success")) {
                                               // hstt.update(Integer.parseInt(attendence_id));
                                                //sync.setClickable(false);
                                              //  sync.setText("Done");
                                                //sync.setBackgroundColor(44);
                                                approved.setVisibility(View.GONE);

                                            context.startActivity(new Intent(context,context.getClass()));


                                           // }

                                        }
                                    });
                        } else {
                            builder1.setTitle("No Internet Connection");
                            builder1.setMessage("Check your internet connection");

                            builder1.setPositiveButton(
                                    "Ok",
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
                                           // sync.setClickable(false);
                                            //sync.setText("Done");
                                           // sync.setBackgroundColor(44);
                                            //}
                                        }

                                    });


                        }
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });


                cancelled.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setCancelable(false);
                        Boolean isconnected = ConnectivityReceiver.isConnected();
                     /*  candialog.setCancelable(false);
                        candialog.setMessage("Syncing Data");
                        candialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        candialog.setIndeterminate(true);
                        candialog.show();*/
                        if (isconnected) {
                            candialog.setCancelable(false);
                            candialog.setMessage("Syncing Data");
                            candialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            candialog.setIndeterminate(true);
                            candialog.show();

//Toast.makeText(view.getContext(),""+lattitudet+"\n"+longitudet+"\n"+accuracyt+"\n"
                            //     +slot_idt+"\n"+marked_ont+"\n"+datet+"\n"+school_idt+"\n"+remarkt+"\n"+marked_by_id+"\n" , Toast.LENGTH_LONG).show();
                            // Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost("http://smartschooldemo.empoweru.in/leave_approve/");


                            // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
                            nameValuePair.add(new BasicNameValuePair("leave_req", leave_req_id));
                            nameValuePair.add(new BasicNameValuePair("status", String.valueOf(2)));
                            nameValuePair.add(new BasicNameValuePair("userid", userid));


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
                                status1 = obj.getString("status");
                               // Toast.makeText(view.getContext(),message+"\n"+status1,Toast.LENGTH_LONG ).show();
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


                        /*    if (candialog != null) {
                                candialog.dismiss();
                            }*/

                            builder1.setTitle(status1);
                            builder1.setMessage(message);
                            builder1.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();
                                         //   if (status1.equals("Success")) {
                                                // hstt.update(Integer.parseInt(attendence_id));
                                              //  sync.setClickable(false);
                                             //   sync.setText("Done");
                                              //  sync.setBackgroundColor(44);
                                                cancelled.setVisibility(View.GONE);
                                                approved.setVisibility(View.GONE);
                                            context.startActivity(new Intent(context,context.getClass()));
                                          //  }

                                        }
                                    });
                        } else {
                            builder1.setTitle("No Internet Connection");
                            builder1.setMessage("Check you internet connection!");

                            builder1.setPositiveButton(
                                    "OK",
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
                                           // sync.setClickable(false);
                                           // sync.setText("Done");
                                           // sync.setBackgroundColor(44);
                                            //}
                                        }

                                    });

                        }
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });

            } else if (Integer.parseInt(statusid) == 2) {
                approved.setVisibility(View.GONE);

                cancelled.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder1 = new AlertDialog.Builder(view.getContext());
                        builder1.setCancelable(false);
                        Boolean isconnected = ConnectivityReceiver.isConnected();
                      /*  candialog.setCancelable(false);
                        candialog.setMessage("Syncing Data");
                        candialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        candialog.setIndeterminate(true);
                        candialog.show();*/
                        if (isconnected) {
                            candialog.setCancelable(false);
                            candialog.setMessage("Syncing Data");
                            candialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            candialog.setIndeterminate(true);
                            candialog.show();

//Toast.makeText(view.getContext(),""+lattitudet+"\n"+longitudet+"\n"+accuracyt+"\n"
                            //     +slot_idt+"\n"+marked_ont+"\n"+datet+"\n"+school_idt+"\n"+remarkt+"\n"+marked_by_id+"\n" , Toast.LENGTH_LONG).show();
                            // Toast.makeText(view.getContext(),attendencestatust+"\n"+user_idt ,Toast.LENGTH_LONG ).show();
                            HttpClient httpClient = new DefaultHttpClient();
                            // Creating HTTP Post
                            HttpPost httpPost = new HttpPost("http://smartschooldemo.empoweru.in/leave_approve/");


                            // Syncing part
                            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
                            nameValuePair.add(new BasicNameValuePair("leave_req", leave_req_id));
                            nameValuePair.add(new BasicNameValuePair("status", String.valueOf(2)));
                            nameValuePair.add(new BasicNameValuePair("userid", userid));


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
                                status1 = obj.getString("status");
                               // Toast.makeText(view.getContext(),message+"\n"+status1,Toast.LENGTH_LONG ).show();
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


                         /*   if (candialog != null) {
                                candialog.dismiss();
                            }*/

                            builder1.setTitle(status1);
                            builder1.setMessage(message);
                            builder1.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                            dialog.cancel();
                                          //  if (status1.equals("Success")) {
                                                // hstt.update(Integer.parseInt(attendence_id));
                                                //sync.setClickable(false);
                                               // sync.setText("Done");
                                                //sync.setBackgroundColor(44);
                                                cancelled.setVisibility(View.GONE);
                                           // }
                                            context.startActivity(new Intent(context,context.getClass()));

                                        }
                                    });
                        } else {
                            builder1.setTitle("No Internet Connection");
                            builder1.setMessage("Check you internet connection!");

                            builder1.setPositiveButton(
                                    "OK",
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
                                          //  sync.setClickable(false);
                                            //sync.setText("Done");
                                           // sync.setBackgroundColor(44);
                                            //}
                                        }

                                    });


                        }
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
                    }
            else if (Integer.parseInt(statusid) == 3) {
                cancelled.setVisibility(View.GONE);
                approved.setVisibility(View.GONE);
            }



           req_idt.setText("Req. Id: "+req_id);
           req_ont.setText("Req. On: "+req_on);
           ltt.setText("LT: "+lt);
           frt.setText(fd);
           tdt.setText(td);
            reasont.setText("Reason: "+reason);
            if(status.equals("Cancel")){
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallcancel) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallcancel));
                }
             //   statust.setBackgroundColor(Color.RED);
                statust.setText(status);
            }
           else if(status.equals("Approved")){
                statust.setBackgroundColor(Color.GREEN);
                statust.setText(status);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallapproved) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallapproved));
                }
            }
           else if(status.equals("Pending")){
                statust.setBackgroundColor(Color.YELLOW);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallpending) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallpending));
                }
                statust.setText(status);
            }

            //statust.setText(status);
            namet.setText("Name: "+name);

        }

    }
}
