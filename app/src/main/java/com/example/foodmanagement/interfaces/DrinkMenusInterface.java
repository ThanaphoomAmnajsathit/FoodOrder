package com.example.foodmanagement.interfaces;

import com.example.foodmanagement.models.Product;
import com.example.foodmanagement.models.User;

import java.util.List;

public interface DrinkMenusInterface {

    interface view{

        void onSetProduct(List<Product> product);


    }

    interface presenter{
        void onError();

        void onSetProducts(List<Product> products);
    }
}
