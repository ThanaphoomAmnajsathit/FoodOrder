package com.example.foodmanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @Expose
    @SerializedName("product_id")
    private String menu_id;
    @Expose
    @SerializedName("product_price")
    private String menu_price;
    @Expose
    @SerializedName("product_name")
    private String menu_name;

    private int countOrder;

    public Product(String menu_id , String menu_price , String menu_name , int countOder){
        setMenu_id(menu_id);
        setMenu_price(menu_price);
        setMenu_name(menu_name);
        setCountOrder(countOder);
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "menu_id='" + menu_id + '\'' +
                ", menu_price='" + menu_price + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", countOrder=" + countOrder +
                '}';
    }
}
