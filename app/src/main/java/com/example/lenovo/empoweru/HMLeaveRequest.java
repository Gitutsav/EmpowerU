package com.example.lenovo.empoweru;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 2/28/2017.
 */

public class HMLeaveRequest extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerview;
    List<String> dates=new ArrayList<>(),remarks=new ArrayList<>(),presents=new ArrayList<>(),
            absents=new ArrayList<>(),leaves=new ArrayList<>(),attendence_ids=new ArrayList<>(),
            flag=new ArrayList<>();
    private HM_submit_tables_teachers sbmit_details;
    HMPruebaAdapterTecher adapter;
    Cursor submit;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.hm_my_leaves, container, false);

        return view;
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}
