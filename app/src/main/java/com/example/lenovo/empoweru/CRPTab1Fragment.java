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

public class CRPTab1Fragment extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerview;
    List<String> school_names=new ArrayList<>(),cluster_names=new ArrayList<>(),
            dates=new ArrayList<>(),remarks=new ArrayList<>(),presents=new ArrayList<>(),
            absents=new ArrayList<>(),leaves=new ArrayList<>(),attendence_ids=new ArrayList<>(),
            flag=new ArrayList<>();
    private CRP_submit_tables_teachers sbmit_details;
    CRPPruebaAdapterTecher adapter;
    Cursor submit;
    private LinearLayout no_record;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_crptabbedteacherq, container, false);

        recyclerview = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);

        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(llm);
        sbmit_details=new CRP_submit_tables_teachers(getContext());
        submit=sbmit_details.getAllData3();
        school_names.clear();cluster_names.clear();absents.clear();presents.clear();leaves.clear();
        dates.clear();attendence_ids.clear();flag.clear();remarks.clear();
        while (submit.moveToNext()) {

            school_names.add(submit.getString(1));
            cluster_names.add(submit.getString(2));
            absents.add(String.valueOf(submit.getInt(4)));
            presents.add(String.valueOf(submit.getInt(3)));
            leaves.add(String.valueOf(submit.getInt(5)));
            remarks.add(submit.getString(7));
            dates.add(submit.getString(6));
            attendence_ids.add(String.valueOf(submit.getInt(0)));
            flag.add(String.valueOf(submit.getInt(8)));
        }

        Collections.reverse(school_names);
        Collections.reverse(cluster_names);
        Collections.reverse(dates);
        Collections.reverse(remarks);
        Collections.reverse(presents);
        Collections.reverse(absents);
        Collections.reverse(leaves);
        Collections.reverse(attendence_ids);
        Collections.reverse(flag);
        adapter = new CRPPruebaAdapterTecher(school_names,cluster_names,dates,remarks,presents,absents,leaves,attendence_ids,flag);
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
