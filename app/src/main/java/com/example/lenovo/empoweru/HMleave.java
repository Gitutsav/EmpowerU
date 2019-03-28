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

public class HMleave extends AppCompatActivity {
    public static HMleave instance;
    private HMApplyLeave fragmentOne;
    private HMMyLeaves fragmentTwo;
    private HMLeaveRequest fragmentThree;
    private TabLayout allTabs;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmleave);
        back=(ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HMleave.this,Dashboard.class));
            }
        });
        instance=this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }
    public static HMleave getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupTabLayout() {
        fragmentOne = new HMApplyLeave();
        fragmentTwo = new HMMyLeaves();
        fragmentThree=new HMLeaveRequest();

        allTabs.addTab(allTabs.newTab().setText("Apply Leave"),true);
        allTabs.addTab(allTabs.newTab().setText("My Leaves"));
        allTabs.addTab(allTabs.newTab().setText("Leave Request"));
        allTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        allTabs.setTabMode(TabLayout.MODE_FIXED);
        allTabs.setSelectedTabIndicatorHeight(5);
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