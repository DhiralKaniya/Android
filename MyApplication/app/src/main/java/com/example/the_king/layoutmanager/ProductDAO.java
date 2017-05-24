package com.example.the_king.layoutmanager;

import android.util.Log;

import java.util.ArrayList;


/**
 * Created by The_King on 5/9/2017.
 */

public class ProductDAO{

    public ProductDAO(){}
    public void addProduct(String pname, double cost, String date){
        Log.i("status","add product method");

        Product newProduct = new Product(pname,cost,date);
        newProduct.save();
    }
    public Product findProduct(int pid){
        return Product.findById(Product.class,(long)pid);
    }
    public void deleteProduct(int pid){
        Product myproduct = Product.findById(Product.class,(long)pid);
        myproduct.delete();
    }
    public void updateProduct(int pid,String pname, double cost, String date){
        Product myproduct = Product.findById(Product.class,(long)pid);
        Log.i("status pid ",Long.toString(pid));
        myproduct.setManufacturedate(date);
        myproduct.setProductcost(cost);
        myproduct.setProductname(pname);
        myproduct.save();
    }
    public ArrayList<Product> findAll(){
            Log.i("status", "find all method");
        if(Product.count(Product.class) > 0) {
            ArrayList<Product> mproduct = (ArrayList<Product>) Product.listAll(Product.class);
            return mproduct;
        }
        return null;
    }
}

