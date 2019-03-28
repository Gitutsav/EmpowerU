package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class HmPruebaAdapterMyLeave extends RecyclerView.Adapter<HmPruebaAdapterMyLeave.PruebaViewHolder> {
    private Block_submit_tables_teachers bstt;
    List<String> req_ids,req_ons,lts,fds,tds,reasons,statuss;    AlertDialog.Builder builder1;
    String[] escrito;
ProgressDialog dialog;Context context;
    public HmPruebaAdapterMyLeave(List<String> req_idsh , List<String> req_onh,
                                  List<String> lth
            , List<String> fdh,List<String> tdh,List<String> reasonsh, List<String> statussh) {
    this.req_ids=req_idsh;
    this.req_ons=req_onh;
    this.lts=lth;
    this.fds=fdh;
    this.tds=tdh;
    this.reasons=reasonsh;
      this.statuss=statussh;
      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hm_my_leave_status_item_row,parent,false);
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

         holder.bindProducto(req_id,req_on,lt,fd,td,reason,status);
    }

    @Override
    public int getItemCount() {
        return req_ids.size();
    }

    public String[] getEscrito() {
        return escrito;
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{


        TextView req_idt,req_ont,ltt,frt,tdt,reasont,statust;
        Button sync;
        int accuracyt,slot_idt,taken_by_idt,school_idt,marked_by_id,marked_type=1;
        String remarkt,datett,marked_ont,datet;String message,status;
        double lattitudet,longitudet;
        String user_idt="",attendencestatust="";
        public PruebaViewHolder( View itemView) {
            super(itemView);
            req_idt = (TextView) itemView.findViewById(R.id.req_id);
            req_ont = (TextView) itemView.findViewById(R.id.req_on);
            ltt = (TextView) itemView.findViewById(R.id.lt);
            frt = (TextView) itemView.findViewById(R.id.fd);
            tdt = (TextView) itemView.findViewById(R.id.td);
            reasont = (TextView) itemView.findViewById(R.id.reason);
            statust = (TextView) itemView.findViewById(R.id.status);

        }

        public void bindProducto(String req_id, String req_on, String lt, String fd, String td,
                                 String reason, String status){


           req_idt.setText("Req. Id: "+req_id);
           req_ont.setText("Req. On: "+req_on);
           ltt.setText("LT: "+lt);
           frt.setText("Fr: "+fd);
           tdt.setText("To: "+td);
            reasont.setText("Reason: "+reason);
           // statust.setText(status);
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

        }

    }
}
