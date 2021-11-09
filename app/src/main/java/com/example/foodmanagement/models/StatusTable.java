package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusTable {
    @Expose
    @SerializedName("code")
    public String Code;
    @Expose
    @SerializedName("message")
    public String Message;
    @Expose
    @SerializedName("table_id")
    private String table_id;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("table_people")
    private String table_people;
    @Expose
    @SerializedName("table_isbusy")
    private String table_isbusy;

    public StatusTable(String table_id,String user_id ,String table_people, String table_isbusy) {
        this.table_id = table_id;
        this.user_id = user_id;
        this.table_people = table_people;
        this.table_isbusy = table_isbusy;
    }
}
