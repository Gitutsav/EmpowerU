package com.example.lenovo.empoweru;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BlockTab1Fragment tab1 = new BlockTab1Fragment();
                return tab1;
            case 1:
                BlockTab2Fragment tab2 = new BlockTab2Fragment();
                return tab2;
            case 2:
                BlockTab3Fragment tab3 = new BlockTab3Fragment();
                return tab3;
            case 3:
                BlockTab4Fragment tab4 = new BlockTab4Fragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}