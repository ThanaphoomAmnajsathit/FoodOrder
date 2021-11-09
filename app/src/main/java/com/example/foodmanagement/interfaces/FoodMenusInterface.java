package com.example.foodmanagement.interfaces;

import com.example.foodmanagement.models.Product;

import java.util.List;

public interface FoodMenusInterface {

    interface view{
        //void onUpdateMenuRiceList(int i);

        void onUpdateAdapter();

        //void onUpdateMenuNoodleList(int i);

        void onSetProduct(List<Product> product);
    }

    interface presenter{

        void onError();

        void onSetProducts(List<Product> products);
    }
}
