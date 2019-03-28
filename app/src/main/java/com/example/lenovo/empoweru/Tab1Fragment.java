package com.example.lenovo.empoweru;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by User on 2/28/2017.
 */

public class Tab1Fragment extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerview;
    List<String> dates=new ArrayList<>(),remarks=new ArrayList<>(),presents=new ArrayList<>(),
            absents=new ArrayList<>(),leaves=new ArrayList<>(),attendence_ids=new ArrayList<>(),
            flag=new ArrayList<>();
    private HM_submit_tables_teachers sbmit_details;
    HMPruebaAdapterTecher adapter;
    Cursor submit;LinearLayout no_record;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tabbedteacher, container, false);

        recyclerview = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
      no_record=view.findViewById(R.id.no_record);
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(llm);
        sbmit_details=new HM_submit_tables_teachers(getContext());
        submit=sbmit_details.getAllData3();
       absents.clear();presents.clear();leaves.clear();
        dates.clear();attendence_ids.clear();flag.clear();remarks.clear();
        while (submit.moveToNext()) {

            absents.add(String.valueOf(submit.getInt(2)));
            presents.add(String.valueOf(submit.getInt(1)));
            leaves.add(String.valueOf(submit.getInt(3)));
            remarks.add(submit.getString(5));
            dates.add(submit.getString(4));
            attendence_ids.add(String.valueOf(submit.getInt(0)));
            flag.add(String.valueOf(submit.getInt(6)));
        }


        Collections.reverse(dates);
        Collections.reverse(remarks);
        Collections.reverse(presents);
        Collections.reverse(absents);
        Collections.reverse(leaves);
        Collections.reverse(attendence_ids);
        Collections.reverse(flag);
        adapter = new HMPruebaAdapterTecher(dates,remarks,presents,absents,leaves,attendence_ids,flag);
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
