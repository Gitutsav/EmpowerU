package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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


public class HmPruebaAdapterGrievanceRecord extends RecyclerView.Adapter<HmPruebaAdapterGrievanceRecord.PruebaViewHolder> {
    private Block_submit_tables_teachers bstt;
    List<String> grievances,grievance_ids,grievance_types,dates,statuss;    AlertDialog.Builder builder1;
    String[] escrito;Context context;
ProgressDialog dialog;
    public HmPruebaAdapterGrievanceRecord(List<String> grievancesh , List<String> grievance_idsh,
                                          List<String> grievance_typesh
            , List<String> datesh, List<String> statussh) {
      this.grievances=grievancesh;
      this.grievance_ids=grievance_idsh;
      this.grievance_types=grievance_typesh;
      this.dates=datesh;
      this.statuss=statussh;
      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hm_grievance_record_status_item_row,parent,false);
        this.bstt=new Block_submit_tables_teachers(v.getContext());
        context=v.getContext();
        return new PruebaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
String grievance= grievances.get(position);
String grievance_id=grievance_ids.get(position);
String grievance_type=grievance_types.get(position);
String date= dates.get(position);
String status= statuss.get(position);

         holder.bindProducto(grievance,grievance_id,grievance_type,date,status);
    }

    @Override
    public int getItemCount() {
        return grievances.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView block_names,school_names,cluster_names,dates,statust,presents,absents,leaves;
        Button sync;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message,status;
        double lattitudet,longitudet;
        String user_idt="",attendencestatust="";
        public PruebaViewHolder( View itemView) {
            super(itemView);
            block_names = (TextView) itemView.findViewById(R.id.grievance);
            school_names = (TextView) itemView.findViewById(R.id.grievance_id);
            cluster_names = (TextView) itemView.findViewById(R.id.grievance_type);
            dates = (TextView) itemView.findViewById(R.id.date);
            statust = (TextView) itemView.findViewById(R.id.status);


        }

        public void bindProducto(String blockname, String schoolname, String clustername, String date, String remak){


           block_names.setText("Grievance: "+blockname);
           school_names.setText("Grievance Id: "+schoolname);
           cluster_names.setText("Grievance Type: "+clustername);
           dates.setText("Req. On: "+date);
          // remarks.setText(remak);
            if(remak.equals("Cancel")){
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallcancel) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallcancel));
                }
                //   statust.setBackgroundColor(Color.RED);
                statust.setText(remak);
            }
            else if(remak.equals("Approved")){
                statust.setBackgroundColor(Color.GREEN);
                statust.setText(remak);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallapproved) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallapproved));
                }
            }
            else if(remak.equals("Pending")){
                statust.setBackgroundColor(Color.YELLOW);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    statust.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.mybordersmallpending) );
                } else {
                    statust.setBackground(ContextCompat.getDrawable(context, R.drawable.mybordersmallpending));
                }
                statust.setText(remak);
            }

        }

    }
}
