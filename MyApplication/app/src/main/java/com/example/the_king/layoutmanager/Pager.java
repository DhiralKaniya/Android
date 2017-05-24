package com.example.the_king.layoutmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by The_King on 5/13/2017.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabcount;
    public Pager(FragmentManager fm,int tabcount) {
        super(fm);
        this.tabcount = tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new tab_view1();
            case 1 :
                return  new tab_view2();
            case 2 :
                return new tabl_view3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabcount;
    }
}
