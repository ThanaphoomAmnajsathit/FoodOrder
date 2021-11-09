package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableData {

    @Expose
    @SerializedName("table_id")
    private String tableNumber;
    @Expose
    @SerializedName("table_people")
    private String people;
    @Expose
    @SerializedName("table_isbusy")
    private String isBusy;

    public TableData(String tableNumber,String people,String isBusy){
        setTableNumber(tableNumber);
        setPeople(people);
        setBusy(isBusy);
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public String getPeople() {
        return people;
    }

    public String isBusy() {
        return isBusy;
    }

    public void setBusy(String busy) {
        isBusy = busy;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
