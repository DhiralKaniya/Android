package com.example.the_king.layoutmanager;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by The_King on 5/11/2017.
 */

public class SampleAdapter extends ArrayAdapter<String> {
    private static final String TAG = "SampleAdapter";

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private ArrayList<String> caption;
    public SampleAdapter(Context context, int textViewResourceId,
                         ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
        caption = new ArrayList<String>();
        caption.add("Hello");
        caption.add("World");
        caption.add("new");
        caption.add("w1");
        caption.add("navigation");
        caption.add("bar");
    }

    public String getCaption(int position){
        return caption.get(position);
    }
    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {

        final ViewHolder vh;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_custom, parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                    .findViewById(R.id.imgView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        TextView mytext = (TextView) convertView.findViewById(R.id.textview);
        double positionHeight = getPositionRatio(position);

        vh.imgView.setHeightRatio(positionHeight);
        mytext.setText(getCaption(position));
        Picasso.with(getContext()).load(Integer.parseInt(getItem(position))).networkPolicy(NetworkPolicy.OFFLINE).into(vh.imgView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(getContext()).load(Uri.parse(getItem(position))).placeholder(R.mipmap.ic_launcher)
                        .error(R.drawable.i01)
                        .into(vh.imgView); ;
            }
        });

        return convertView;
    }
    /*public Uri getImage(int position){
        return Uri.parse(images.get(position));
    }*/
    static class ViewHolder {
        DynamicHeightImageView imgView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }
}
