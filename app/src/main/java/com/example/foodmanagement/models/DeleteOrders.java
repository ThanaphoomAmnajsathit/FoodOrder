package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteOrders {

    @Expose
    @SerializedName("code")
    public String Code;
    @Expose
    @SerializedName("message")
    public String Message;
    @Expose
    @SerializedName("user_id")
    public String user_id;

    public DeleteOrders(String user_id) {
        this.user_id = user_id;
    }
}
