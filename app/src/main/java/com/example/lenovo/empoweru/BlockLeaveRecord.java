package com.example.lenovo.empoweru;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class BlockLeaveRecord extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageButton back;

    private RecyclerView recyclerview;
    List<String> req_id = new ArrayList<>(), req_on = new ArrayList<>(), lt = new ArrayList<>(),
            fd = new ArrayList<>(), td = new ArrayList<>(),nam=new ArrayList<>(),
            reason = new ArrayList<>(), status = new ArrayList<>(),
            statusid= new ArrayList<>(), leave_req_id= new ArrayList<>();
    private Block_submit_tables_teachers sbmit_details;
    BlockPruebaAdapterLeaveRecord adapter;
    Cursor submit;
    private ProgressDialog dialog;
    AlertDialog.Builder builder1;
    private Block_details3 hm_details5;
    private String userid;
    private SwipeRefreshLayout swipeRefreshLayout;
    Boolean isconnected;LinearLayout nonet;LinearLayout retry;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_leave_record);
        back=(ImageButton) findViewById(R.id.back);
        nonet=(LinearLayout)findViewById(R.id.no_net);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BlockLeaveRecord.this,BLOCK_nav_d.class));
            }
        });
        recyclerview = (android.support.v7.widget.RecyclerView) findViewById(R.id.rvPrueba);
        nonet.setVisibility(View.GONE);
retry=(LinearLayout) findViewById(R.id.letret);
retry.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(BlockLeaveRecord.this,BlockLeaveRecord.class));
    }
});
        android.support.v7.widget.LinearLayoutManager llm = new android.support.v7.widget.LinearLayoutManager(this);
        recyclerview.setLayoutManager(llm);
        isconnected = ConnectivityReceiver.isConnected();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        //swipeRefreshLayout.setOnRefreshListener(BlockLeaveRecord.this);
    /*    swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_green_dark,

                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);*/
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isconnected = ConnectivityReceiver.isConnected();
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                if (isconnected) {
                    //dialog.cancel();
                    // Intent intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
                    //startActivity(intent);
                    // OnLogin();
                    //  JSONTaskBl.execute("http://smartschooldemo.empoweru.in/leaveRecord/?userid=232");
                    nonet.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);
                    new JSONTaskBl().execute("http://smartschooldemo.empoweru.in/leaveRecord/?userid="+userid);
                } else if (isconnected == false) {

                    dialog.cancel();
                    builder1 = new AlertDialog.Builder(BlockLeaveRecord.this);
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
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Leave Records , please wait..");
        dialog.show();
        hm_details5=new Block_details3(this);
       Cursor user_details=hm_details5.data_data();
        user_details.moveToFirst();
        userid = String.valueOf(user_details.getInt(1));
        if (isconnected) {
            //dialog.cancel();
            // Intent intent = new Intent(LoginActivity.this, BLOCK_nav_d.class);
            //startActivity(intent);
            // OnLogin();
         //  JSONTaskBl.execute("http://smartschooldemo.empoweru.in/leaveRecord/?userid=232");
            nonet.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
           new JSONTaskBl().execute("http://smartschooldemo.empoweru.in/leaveRecord/?userid="+userid);
        } else if (isconnected == false) {
            dialog.cancel();
            builder1 = new AlertDialog.Builder(this);
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
    }

    @Override
    public void onRefresh() {
       // Toast.makeText(this, "swipe", Toast.LENGTH_LONG).show();
    }

    public class JSONTaskBl extends AsyncTask<String, String, String> {

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
                nam.clear();
                statusid.clear();
                leave_req_id.clear();




                for (int i = 0; i < parentArray1.length(); i++) {
                    JSONObject finalobject = parentArray1.getJSONObject(i);
                    req_id.add(String.valueOf(finalobject.getInt("leave_request_id")));
                    req_on.add(String.valueOf(finalobject.getString("req_on")));
                    lt.add(finalobject.getString("leave_type"));
                    fd.add(finalobject.getString("from_date"));
                    td.add(finalobject.getString("to_date"));
                    reason.add(finalobject.getString("reason"));
                    status.add(finalobject.getString("status1"));
                    nam.add(finalobject.getString("name"));
                    statusid.add(String.valueOf(finalobject.getInt("statusid")));
                    leave_req_id.add(String.valueOf(finalobject.getInt("leave_request_id")));
                    loopeddata.append(finalobject.getString("status"));

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
            Collections.reverse(nam);
            Collections.reverse(statusid);
            Collections.reverse(leave_req_id);
            adapter = new BlockPruebaAdapterLeaveRecord(req_id,req_on,lt,fd,td,reason,status,nam,statusid,leave_req_id);
            //  adapter.notifyDataSetChanged();

            recyclerview.setAdapter(adapter);

            //  cast.setText(s);
            //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }


    }
}
