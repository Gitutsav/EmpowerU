package com.example.lenovo.empoweru;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SMDistrictSchoolRecord extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener{

    private RecyclerView recyclerview;
    List<String> block_names=new ArrayList<>(),school_names=new ArrayList<>(),cluster_names=new ArrayList<>(),
            dates=new ArrayList<>(),remarks=new ArrayList<>(),presents=new ArrayList<>(),
            absents=new ArrayList<>(),leaves=new ArrayList<>(),attendence_ids=new ArrayList<>(),
            flag=new ArrayList<>();
private SM_District_submit_tables_school sbmit_details;
SmDistrictPruebaAdapterSchool adapter;
Cursor submit;
    private LinearLayout no_record;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.sm_district_school_record, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.rvPrueba);

        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(llm);
        sbmit_details=new SM_District_submit_tables_school(getContext());
        submit=sbmit_details.getAllData3();
        block_names.clear();school_names.clear();cluster_names.clear();absents.clear();presents.clear();leaves.clear();
        dates.clear();attendence_ids.clear();flag.clear();remarks.clear();
        while (submit.moveToNext()) {
            block_names.add(submit.getString(2));
            school_names.add(submit.getString(1));
            cluster_names.add(submit.getString(3));
            remarks.add(submit.getString(5));
             dates.add(submit.getString(4));
            attendence_ids.add(String.valueOf(submit.getInt(0)));
            flag.add(String.valueOf(submit.getInt(6)));
        }
        Collections.reverse(block_names);
        Collections.reverse(school_names);
        Collections.reverse(cluster_names);
        Collections.reverse(dates);
        Collections.reverse(remarks);
        Collections.reverse(attendence_ids);
        Collections.reverse(flag);
        adapter = new SmDistrictPruebaAdapterSchool(block_names,school_names,cluster_names,dates,remarks,attendence_ids,flag);
      //  adapter.notifyDataSetChanged();
        no_record=view.findViewById(R.id.no_record);
        if(adapter.getItemCount()==0){
            no_record.setVisibility(View.VISIBLE);
        }
        else{
            no_record.setVisibility(View.GONE);
            recyclerview.setAdapter(adapter);}
        return view;
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}
