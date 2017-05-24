package com.example.the_king.layoutmanager;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class view_Fragment extends Fragment {
    private ProductDAO myproduct;
    private View view;
    private static RecyclerView myrecords;
    private RecyclerView.LayoutManager LayoutManager;
    private static RecyclerView.Adapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_view,container,false);
        myproduct = new ProductDAO();
        fillRecord(view);
        return view;
    }
    public void fillRecord(View view){
        Log.i("Status","View Fragment");
        myrecords = (RecyclerView) view.findViewById(R.id.displayrecord);
        LayoutManager = new LinearLayoutManager(getActivity());
        myrecords.setHasFixedSize(true);
        myrecords.setLayoutManager(LayoutManager);
        myrecords.setItemAnimator(new DefaultItemAnimator());
        try {
            ArrayList<Product> mp = myproduct.findAll();
            Log.i("Status","View Fragment Mp");
            CustomeGridView gridAdapter = new CustomeGridView(getActivity(),mp,getActivity().getApplicationContext());

            myrecords.setAdapter(gridAdapter);
        }catch(NullPointerException e){
            Log.i("status","Error - null");
        }
    }
    public void refresh(){
        fillRecord(view);
    }
}
