package com.example.the_king.layoutmanager;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Calendar;


import static android.system.Os.getpid;

public class Insert_Fragment extends Fragment {
    private Button b1 ;
    private final ProductDAO myproduct = new ProductDAO();
    private EditText pname,date,price;
    private long pid=0;
    private static final int DATE_DAILOG_ID = 0;
    public void setPid(long pid){
        this.pid = pid;
    }
    public int getPid(){ return (int)this.pid; }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_insert_,container,false);
        b1 = (Button) view.findViewById(R.id.addButton);
        pname = (EditText) view.findViewById(R.id.e1);
        price = (EditText) view.findViewById(R.id.e2);
        date = (EditText) view.findViewById(R.id.e3);
        date.setTextIsSelectable(true);
        Toolbar toolbar = (Toolbar) container.findViewById(R.id.toolbar);

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });
        if(pid != 0){
            Log.i("pid",Integer.toString(getPid()));
            Product p = Product.findById(Product.class,pid);
            pname.setText(p.getProductname());
            price.setText(Double.toString(p.getProductcost()));
            date.setText(p.getManufacturedate());
            b1.setText("Update Record");
        }
        b1.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view){
                String productname = pname.getText().toString();
                double productprice = Double.parseDouble(price.getText().toString());
                String manufactureDate = date.getText().toString();
                if(b1.getText()=="Update Record")
                    myproduct.updateProduct(getPid(),productname,productprice,manufactureDate);
                else
                    myproduct.addProduct(productname,productprice,manufactureDate);
                Fragment fragment = new view_Fragment();
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.display,fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void insertRecord(View view){
        String productname = pname.getText().toString();
        double productprice = Double.parseDouble(price.getText().toString());
        String manufactureDate = date.getText().toString();
        myproduct.addProduct(productname, productprice, manufactureDate);

    }


}
