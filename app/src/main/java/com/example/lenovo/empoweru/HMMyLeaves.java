package com.example.lenovo.empoweru;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class HMMyLeaves extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener {


    private RecyclerView recyclerview;
    List<String> req_id = new ArrayList<>(), req_on = new ArrayList<>(), lt = new ArrayList<>(),
            fd = new ArrayList<>(), td = new ArrayList<>(),
            reason = new ArrayList<>(), status = new ArrayList<>();
    private Block_submit_tables_teachers sbmit_details;
    HmPruebaAdapterMyLeave adapter;
    Cursor submit;
    private ProgressDialog dialog;
    AlertDialog.Builder builder1;
    private HM_details5 hm_details5;
    private String userid;
    SwipeRefreshLayout swipeRefreshLayout; Boolean isconnected;
    private LinearLayout retry;
    private Context context;
    private LinearLayout nonet;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hm_my_leaves, container, false);

        recyclerview = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.rvPrueba);
        context=view.getContext();
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(llm);
        isconnected = ConnectivityReceiver.isConnected();
        dialog = new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading your leaves , please wait..");
        dialog.show();

        hm_details5=new HM_details5(getContext());
        Cursor user_details=hm_details5.data_data();
        builder1 = new AlertDialog.Builder(view.getContext());
        user_details.moveToFirst();
        userid = String.valueOf(user_details.getInt(1));
        retry=view.findViewById(R.id.letret);
        nonet=(LinearLayout)view.findViewById(R.id.no_net);
        nonet.setVisibility(View.GONE);
        retry=(LinearLayout) view.findViewById(R.id.letret);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context,context.getClass()));
                HMMyLeaves Tab1Fragment = new HMMyLeaves();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,Tab1Fragment );
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack

                transaction.detach(Tab1Fragment);
                transaction.attach(Tab1Fragment);

                transaction.commit();
            }
        });


        if (isconnected) {
            //dialog.cancel();
            // Intent intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
            //startActivity(intent);
            // OnLogin();
            nonet.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            new JSONTaskHMg().execute("http://smartschooldemo.empoweru.in/My_leave/?Auid="+userid);
        } else if (isconnected == false) {
            dialog.cancel();
            builder1 = new AlertDialog.Builder(view.getContext());
            builder1.setTitle("Check Your Internet Connection!!");
            //builder1.setMessage("Connect tO N");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // startActivity(new Intent(view.getContext(),BlockTabbedteacher.class));
                            dialog.cancel();
                            nonet.setVisibility(View.VISIBLE);
                            recyclerview.setVisibility(View.GONE);
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();


        }


        return view;
    }
 

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }


    public class JSONTaskHMg extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream, "iso-8859-1"));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //return buffer.toString();
                String finalJson = buffer.toString();
                StringBuffer loopeddata = new StringBuffer();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray1 = parentObject.getJSONArray("myleave");


          req_id.clear();
          req_on.clear();;
          lt.clear();
          fd.clear();
          td.clear();
          reason.clear();
          status.clear();



                for (int i = 0; i < parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    req_id.add(String.valueOf(finalobject.getInt("leave_request_id")));
                    req_on.add(String.valueOf(finalobject.getString("req_on")));
                    lt.add(finalobject.getString("leave_type"));
                    fd.add(finalobject.getString("from_date"));
                    td.add(finalobject.getString("to_date"));
                    reason.add(finalobject.getString("reason"));
                    status.add(finalobject.getString("status1"));
                    loopeddata.append(finalobject.getString("status1"));

                }

                return loopeddata.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {

                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.cancel();
            Collections.reverse(req_id);
            Collections.reverse(req_on);
            Collections.reverse(lt);
            Collections.reverse(fd);
            Collections.reverse(td);
            Collections.reverse(reason);
            Collections.reverse(status);
            adapter = new HmPruebaAdapterMyLeave(req_id,req_on,lt,fd,td,reason,status);
            //  adapter.notifyDataSetChanged();

            recyclerview.setAdapter(adapter);

            //  cast.setText(s);
            //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}