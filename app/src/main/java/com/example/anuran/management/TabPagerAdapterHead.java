package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapterHead extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPagerAdapterHead(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Amc tab1 = new Amc();
                return tab1;
            case 1:
                IssuesHead tab2 = new IssuesHead();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}