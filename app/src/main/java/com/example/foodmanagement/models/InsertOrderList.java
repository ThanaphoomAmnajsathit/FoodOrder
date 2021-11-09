package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertOrderList {
    @Expose
    @SerializedName("code")
    public String Code;
    @Expose
    @SerializedName("message")
    public String Message;
    @Expose
    @SerializedName("order_id")
    public String order_id;
    @Expose
    @SerializedName("user_id")
    public String user_id;
    @Expose
    @SerializedName("order_by")
    public String order_by;
    @Expose
    @SerializedName("order_name")
    public String order_name;
    @Expose
    @SerializedName("order_price")
    public String order_price;
    @Expose
    @SerializedName("order_sum")
    public String order_sum;
    @Expose
    @SerializedName("order_modified")
    public String order_modified;
    @Expose
    @SerializedName("order_created")
    public String order_created;
    @Expose
    @SerializedName("order_takeaway")
    public String order_takeaway;
    @Expose
    @SerializedName("order_table")
    public String order_table;

    public InsertOrderList(String order_id
            ,String user_id
            ,String order_by
            ,String order_name
            ,String order_price
            ,String order_sum
            ,String order_modified
            ,String order_created
            ,String order_takeaway
            ,String order_table) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_by = order_by;
        this.order_name = order_name;
        this.order_price = order_price;
        this.order_sum = order_sum;
        this.order_modified = order_modified;
        this.order_created = order_created;
        this.order_takeaway = order_takeaway;
        this.order_table = order_table;
    }

    public InsertOrderList(){}



}
