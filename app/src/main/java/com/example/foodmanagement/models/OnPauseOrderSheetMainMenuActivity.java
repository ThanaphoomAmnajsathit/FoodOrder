package com.example.foodmanagement.models;

import java.util.ArrayList;

public class OnPauseOrderSheetMainMenuActivity {

    private static ArrayList<OrderListData> orderListData;
    private static boolean sentedOrder;

    public OnPauseOrderSheetMainMenuActivity(ArrayList<OrderListData> orderListData,boolean sentedOrder){
        setOrderListData(orderListData);
        setSentedOrder(sentedOrder);
    }

    public OnPauseOrderSheetMainMenuActivity(){}

    public static ArrayList<OrderListData> getOrderListData() {
        return orderListData;
    }

    public static boolean getSentedOrder() {
        return sentedOrder;
    }

    public void setSentedOrder(boolean sentedOrder) {
        OnPauseOrderSheetMainMenuActivity.sentedOrder = sentedOrder;
    }

    public void setOrderListData(ArrayList<OrderListData> orderListData) {
        OnPauseOrderSheetMainMenuActivity.orderListData = orderListData;
    }
}
