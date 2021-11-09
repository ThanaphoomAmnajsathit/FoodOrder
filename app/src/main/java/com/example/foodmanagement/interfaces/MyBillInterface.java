package com.example.foodmanagement.interfaces;

import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.models.Product;

import java.util.List;

public interface MyBillInterface {

    interface presenter{
        void onSetProduct(List<OrderListData> order);

        void onError();
    }

    interface view{
        void onSetProduct(List<OrderListData> order);
    }

}
