package com.example.the_king.layoutmanager;
import com.orm.SugarRecord;
/**
 * Created by The_King on 5/10/2017.
 */

public class Product extends SugarRecord{
    private String productname;
    private String manufacturedate;
    private double productcost;
    public Product(){

    }
    public Product(String productname,double productcost,String manufacturedate){
        this.productname = productname;
        this.productcost = productcost;
        this.manufacturedate = manufacturedate;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getManufacturedate() {
        return manufacturedate;
    }

    public void setManufacturedate(String manufacturedate) {
        this.manufacturedate = manufacturedate;
    }

    public double getProductcost() {
        return productcost;
    }

    public void setProductcost(double productcost) {
        this.productcost = productcost;
    }
}
