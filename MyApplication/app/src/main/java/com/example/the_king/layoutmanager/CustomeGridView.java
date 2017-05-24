package com.example.the_king.layoutmanager;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by The_King on 5/9/2017.
 */

public class CustomeGridView extends RecyclerView.Adapter {
    private ArrayList<Product> myproduct ;
    private Context mycontext;
    private FragmentActivity activity;
    int i=1;

    public CustomeGridView(FragmentActivity a,ArrayList<Product> products,Context context){
        this.myproduct = products;
        this.mycontext = context;
        this.activity = a;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button editbtn;
        Button delbtn ;
        TextView id;
        TextView name;
        TextView price;
        TextView date ;
        public MyViewHolder(View view){
            super(view);
            editbtn= (Button) view.findViewById(R.id.editbtn);
            delbtn = (Button) view.findViewById(R.id.deletebtn);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("Status","CustomeGrid View");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cell,parent,false);
        MyViewHolder myview = new MyViewHolder(view);
        return myview;
    }
    public Object getItem(int i){
        return myproduct.get(i);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("Status","Bind->CustomeGrid View");
        MyViewHolder myview = (MyViewHolder)holder;
        final Product mp = (Product)getItem(position);
        myview.editbtn.setText("E");
        myview.delbtn.setText("D");
        myview.id.setText(Long.toString(mp.getId()));
        myview.name.setText(mp.getProductname());
        myview.price.setText(Double.toString(mp.getProductcost()));
        myview.date.setText(mp.getManufacturedate());
        myview.editbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Insert_Fragment fragment = new Insert_Fragment();
                fragment.setPid(mp.getId());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.display,fragment);
                fragmentTransaction.commit();
            }
        });
        myview.delbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Product.delete(mp);
                myproduct.remove(position);
                notifyItemRemoved(position);
            }
        });
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if(myproduct==null)
            return 0;
        return myproduct.size();
    }
}
