package com.example.the_king.layoutmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;


public class Gallery_Fragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery,container,false);
        //tab layout initialization
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        //view pages initialization
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //tab initialization
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_view1_caption));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_view2_caption));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_view3_caption));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //pager initialization

        Pager pager = new Pager(getFragmentManager(),tabLayout.getTabCount());
        //assigning adapter to the view pager
        viewPager.setAdapter(pager);
        //set click listern of tab
        tabLayout.setOnTabSelectedListener(this);
        //set on change of view pager
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position){
                viewPager.setCurrentItem(position);
            }
        });
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.select();
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
