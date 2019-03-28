package com.example.lenovo.empoweru;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.DividerItemDecoration;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;


public class radrec extends AppCompatActivity {
    private RecyclerView packageRecyclerView;
    private HM_details5 hmd;
    List<String> teachers_list =new ArrayList<>();
    List<Integer> teacher_ids= new ArrayList<>();
Button submit;
private PackageModel pm;
public radrecadapter rrd;
    private SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radrec);
        hmd=new HM_details5(getApplicationContext());
        submit=(Button) findViewById(R.id.submit);
        rrd=new radrecadapter(radrec.this);
        loginData = getSharedPreferences("presenties", Context.MODE_PRIVATE);
        pm=new PackageModel(radrec.this);
        packageRecyclerView = (RecyclerView) findViewById(R.id.package_lst);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        packageRecyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(packageRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        packageRecyclerView.addItemDecoration(dividerItemDecoration);
       radrecadapter recyclerViewAdapter = new
               radrecadapter(getPackages(),this);
        packageRecyclerView.setAdapter(recyclerViewAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String presenties= loginData.getString("presenttt", "No name defined");
                String joinedMinusBrackets = presenties.substring( 1, presenties.length() - 1);

                // String.split()
                String[] resplit = joinedMinusBrackets.split( ", ");
                Toast.makeText(view.getContext(),Arrays.toString(resplit),Toast.LENGTH_SHORT ).show();
                //Toast.makeText(radrec.this,presenties+pm.getPresenties() ,Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private List<PackageModel> getPackages(){
        List<PackageModel> modelList = new ArrayList<PackageModel>();

      List<String> priceList = new ArrayList<String>();
        priceList.clear();
        priceList.add("PRESENT");
        priceList.add("ABSENT");
        priceList.add("LEAVE");

        Cursor teacher=hmd.teacher_data();
        teacher=hmd.teacher_data();
           teachers_list.clear();
           teacher_ids.clear();
           teachers_list.add("emptty");
           teacher_ids.add(0);
        while (teacher.moveToNext()) {
            //listDataHeader.add(teacher.getString(1));
            teachers_list.add(teacher.getString(1));
            teacher_ids.add(teacher.getInt(0));
          //  present_status.add(1);
        }
Toast.makeText(getApplicationContext(),teachers_list.size()+"" ,Toast.LENGTH_SHORT ).show();
        for(int i=0;i<teachers_list.size();i++){
            modelList.add(new PackageModel(teachers_list.get(i), priceList));
          //  Toast.makeText(getApplicationContext(),teachers_list.size()+"/"+priceList.size() ,Toast.LENGTH_SHORT ).show();

        }

        return modelList;
    }

}