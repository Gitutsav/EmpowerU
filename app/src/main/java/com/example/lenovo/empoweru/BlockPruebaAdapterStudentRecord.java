package com.example.lenovo.empoweru;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class BlockPruebaAdapterStudentRecord extends RecyclerView.Adapter<BlockPruebaAdapterStudentRecord.PruebaViewHolder> {

    List<String> block_names,school_names,cluster_names,dates,remarks,presents,absents,leaves;
    AlertDialog.Builder builder1;
    String[] escrito;

    public BlockPruebaAdapterStudentRecord(List<String> block_names, List<String> school_names, List<String> cluster_names
            , List<String> dates, List<String> remarks, List<String> present, List<String> absent
            , List<String> leave) {
      this.absents=absent;
      this.presents=present;
      this.leaves=leave;
      this.block_names=block_names;
      this.school_names=school_names;
      this.cluster_names=cluster_names;
      this.dates=dates;
      this.remarks=remarks;

      //escrito = new String[lista.size()];
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_teacher_status_item_row,parent,false);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PruebaViewHolder holder, int position) {
         String blockname=block_names.get(position);
         String schoolname=school_names.get(position);
         String clustername=cluster_names.get(position);
         String date= dates.get(position);
         String remak=remarks.get(position);
         String leave=leaves.get(position);
         String present=presents.get(position);
         String absent=absents.get(position);

         holder.bindProducto(blockname,schoolname,clustername,date,remak,present,absent,leave);
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
        Button sync;

        public PruebaViewHolder( View itemView) {
            super(itemView);
            block_names = (TextView) itemView.findViewById(R.id.block);
            school_names = (TextView) itemView.findViewById(R.id.school);
            cluster_names = (TextView) itemView.findViewById(R.id.cluster);
            dates = (TextView) itemView.findViewById(R.id.date);
            remarks = (TextView) itemView.findViewById(R.id.remacrk);
            absents = (TextView) itemView.findViewById(R.id.absent);
            presents = (TextView) itemView.findViewById(R.id.present);
            leaves = (TextView) itemView.findViewById(R.id.leave);
            sync = itemView.findViewById(R.id.sync);

            sync.setOnClickListener(new View.OnClickListener() {
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
            });
        }

        public void bindProducto(String blockname, String schoolname, String clustername, String date, String remak, String present, String absent, String leave){
           block_names.setText(blockname);
           school_names.setText(schoolname);
           cluster_names.setText(clustername);
           dates.setText("Date: "+date);
           remarks.setText("Remark: "+remak);
           absents.setText(absent);
           leaves.setText(leave);
           presents.setText(present);
        }

    }
}
