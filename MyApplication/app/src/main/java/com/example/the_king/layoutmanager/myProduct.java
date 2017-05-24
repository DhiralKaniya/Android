package com.example.the_king.layoutmanager;

import com.orm.SugarRecord;

/**
 * Created by The_King on 5/10/2017.
 */

public class myProduct extends SugarRecord {
    private String pname;
    private String cost;
    private String date;
    public myProduct(){

    }
    public myProduct(String pname,String cost,String date){
        this.pname = pname;
        this.cost = cost;
        this.date = date;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
