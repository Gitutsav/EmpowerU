package com.example.lenovo.empoweru;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

public class Tabbedteacher extends AppCompatActivity {

     public static fragmentTest instance;
     private Tab1Fragment fragmentOne;
     private Tab2Fragment fragmentTwo;
     private Tab3Fragment fragmentThree;
     private Tab4Fragment fragmentFour;
     private TabLayout allTabs;
     ImageButton back;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_tabbedteacher);
          back=(ImageButton) findViewById(R.id.back);
          back.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    startActivity(new Intent(Tabbedteacher.this,Dashboard.class));
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
          fragmentOne = new Tab1Fragment();
          fragmentTwo = new Tab2Fragment();
          fragmentThree=new Tab3Fragment();
          fragmentFour= new Tab4Fragment();
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