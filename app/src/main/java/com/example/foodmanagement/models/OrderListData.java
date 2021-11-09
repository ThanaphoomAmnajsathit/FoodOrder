package com.example.foodmanagement.models;

import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderListData {

    @Expose
    @SerializedName("user_id")
    private String user_id;

    @Expose
    @SerializedName("order_name")
    private String order_foodName;

    @Expose
    @SerializedName("order_price")
    private String order_price;

    @Expose
    @SerializedName("order_sum")
    private String order_plusCount;

    @Expose
    @SerializedName("order_modified")
    private String order_modify;

    @Expose
    @SerializedName("order_created")
    private String order_date;

    @Expose
    @SerializedName("order_by")
    private String order_userName;

    @Expose
    @SerializedName("order_takeaway")
    private String order_takeAway;

    public OrderListData(String order_foodName
            ,String order_price
            ,String order_date
            ,String order_userName
            ,String order_plusCount
            ,String order_modify
    ,String user_id
    ,String order_takeAway) {
        setOrder_foodName(order_foodName);
        setOrder_price(order_price);
        setOrder_date(order_date);
        setOrder_userName(order_userName);
        setOrder_plusCount(order_plusCount);
        setOrder_modify(order_modify);
        setUser_id(user_id);
        setOrder_takeAway(order_takeAway);
    }

    public OrderListData(){}

    public void setOrder_foodName(String order_foodName) {
        this.order_foodName = order_foodName;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_userName(String order_userName) {
        this.order_userName = order_userName;
    }

    public void setOrder_plusCount(String order_plusCount) {
        this.order_plusCount = order_plusCount;
    }

    public String getOrder_modify() {
        return order_modify;
    }

    public void setOrder_modify(String order_modify) {
        this.order_modify = order_modify;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_takeAway() {
        return order_takeAway;
    }

    public void setOrder_takeAway(String order_takeAway) {
        this.order_takeAway = order_takeAway;
    }

    public String getOrder_plusCount() {
        return order_plusCount;
    }

    public String getOrder_foodName() {
        return order_foodName;
    }

    public String getOrder_price() {
        return order_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getOrder_userName() {
        return order_userName;
    }

    @Override
    public String toString() {
        return "OrderListData{" +
                "order_foodName='" + order_foodName + '\'' +
                ", order_price='" + order_price + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_userName='" + order_userName + '\'' +
                '}';
    }
}
