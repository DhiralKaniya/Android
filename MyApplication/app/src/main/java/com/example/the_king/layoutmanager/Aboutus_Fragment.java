package com.example.the_king.layoutmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/*
    images = new ArrayList<String>();

        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i01.jpg");
        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i02.jpg");
        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i03.jpg");
        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i04.jpg");
        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i05.jpg");
        images.add("E:\\Andriod\\MyApplication\\app\\src\\main\\res\\drawable-hdpi\\i06.jpg");

 */

public class Aboutus_Fragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {


    private static final String TAG = "StaggeredGridActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private SampleAdapter mAdapter;

    private ArrayList<String> mData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutus,container,false);
        mGridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
        mAdapter = new SampleAdapter(getActivity().getApplicationContext(),android.R.layout.activity_list_item, generateData());
        // do we have saved data?
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
                //onLoadMoreItems();
            }
        }
    }

    private void onLoadMoreItems() {
        final ArrayList<String> sampleData = generateData();
        for (String data : sampleData) {
            mAdapter.add(data);
        }
        // stash all the data in our backing store
        mData.addAll(sampleData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity().getApplicationContext(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
    private ArrayList<String> generateData() {
        ArrayList<String> images = new ArrayList<String>();
        images.add(Integer.toString(R.drawable.i01));
        images.add(Integer.toString(R.drawable.i02));
        images.add(Integer.toString(R.drawable.i03));
        images.add(Integer.toString(R.drawable.i04));
        images.add(Integer.toString(R.drawable.i05));
        images.add(Integer.toString(R.drawable.i06));
        return images;
    }
}
