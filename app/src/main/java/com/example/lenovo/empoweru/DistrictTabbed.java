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

public class DistrictTabbed extends AppCompatActivity {
    public static fragmentTest instance;
    private DistrictTab1Fragment fragmentOne;
    private DistrictTab2Fragment fragmentTwo;
    private DistrictTab3Fragment fragmentThree;
    private DistrictTab4Fragment fragmentFour;
    private TabLayout allTabs;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_tabbed);
        back=(ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DistrictTabbed.this,District_nav_d.class));
            }
        });
        instance=getInstance();
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }
    public static fragmentTest getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupTabLayout() {
        fragmentOne = new DistrictTab1Fragment();
        fragmentTwo = new DistrictTab2Fragment();
        fragmentThree=new DistrictTab3Fragment();
        fragmentFour= new DistrictTab4Fragment();
        allTabs.addTab(allTabs.newTab().setText("TEACHER'S RECORD"),true);
        allTabs.addTab(allTabs.newTab().setText("TEACHER'S ATTENDANCE"));
        allTabs.addTab(allTabs.newTab().setText("STUDENT'S RECORD"));
        allTabs.addTab(allTabs.newTab().setText("STUDENT'S ATTENDANCE"));
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