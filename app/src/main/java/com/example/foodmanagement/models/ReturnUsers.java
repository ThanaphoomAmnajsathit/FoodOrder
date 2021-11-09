package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnUsers {
    @Expose
    @SerializedName("code")
    public String Code;
    @Expose
    @SerializedName("message")
    public String Message;
    @Expose
    @SerializedName("users")
    public List<User> user;
}
