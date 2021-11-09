package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertUser {
    @Expose
    @SerializedName("code")
    public String Code;
    @Expose
    @SerializedName("message")
    public String Message;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("user_display_name")
    private String user_display_name;
    @Expose
    @SerializedName("user_first_name")
    private String user_first_name;
    @Expose
    @SerializedName("user_last_name")
    private String user_last_name;
    @Expose
    @SerializedName("user_pin")
    private String user_pin;
    @Expose
    @SerializedName("user_created")
    private String user_created;


    public InsertUser(String user_id, String user_display_name, String user_first_name, String user_last_name, String user_pin
                        ,String user_created) {
        this.user_id = user_id;
        this.user_display_name = user_display_name;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_pin = user_pin;
        this.user_created = user_created;
    }
}
