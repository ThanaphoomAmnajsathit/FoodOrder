package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyBillNumRow {
    @Expose
    @SerializedName("count")
    private String count;

    public String getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "MyBillNumRow{" +
                "count='" + count + '\'' +
                '}';
    }
}
