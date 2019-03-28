package com.example.lenovo.empoweru;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SMDistrict extends AppCompatActivity {
    public static SMDistrict instance;
    private SMDistrictHostelRecord fragmentThree;
    private SMDistrictHostelMonitor fragmentFour;
    private SMDistrictSchoolRecord fragmentOne;
    private SMDistrictSchoolMonitor fragmentTwo;
    private TabLayout allTabs;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smdistrict);
        back=(ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SMDistrict.this,District_nav_d.class));
            }
        });
        instance=this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }
    public static SMDistrict getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupTabLayout() {
        fragmentOne = new SMDistrictSchoolRecord();
        fragmentTwo = new SMDistrictSchoolMonitor();
        fragmentThree=new SMDistrictHostelRecord();
        fragmentFour= new SMDistrictHostelMonitor();
        allTabs.addTab(allTabs.newTab().setText("SCHOOL RECORDS"),true);
        allTabs.addTab(allTabs.newTab().setText("SCHOOL MONITOR"));
        allTabs.addTab(allTabs.newTab().setText("HOSTEL RECORDS"));
        allTabs.addTab(allTabs.newTab().setText("HOSTEL MONITOR"));
    }
    private void bindWidgetsWithAnEvent()
    {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(fragmentOne);
                break;
            case 1 :
                replaceFragment(fragmentTwo);
                break;
            case 2 :
                replaceFragment(fragmentThree);
                break;
            case 3 :
                replaceFragment(fragmentFour);
                break;
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }
}