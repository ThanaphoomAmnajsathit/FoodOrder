package com.example.foodmanagement.domain.usecase;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.InsertOrderList;
import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;
import com.example.foodmanagement.presentaions.views.fragments.FirstFragment;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertOrderUseCase {

    private  UserRepository userRepository;
    private  MainMenu listener;
    private static ArrayList<OrderListData> orderListData;
    private int position;
    private int order_id;
    InsertOrderList insertOrderList;

    public InsertOrderUseCase(UserRepository userRepository, MainMenu mainMenu, ArrayList<OrderListData> orderListData, int position, int order_id) {
        this.userRepository = userRepository;
        this.listener = mainMenu;
        this.orderListData = orderListData;
        this.position = position;
        this.order_id = order_id;
        insertOrder();
    }

        private void insertOrder(){

            if (orderListData.get(position).getOrder_modify() == null && orderListData.get(position).getOrder_takeAway() == null){
                    insertOrderList = new InsertOrderList(String.valueOf(order_id)
                            , FirstFragment.user_id
                            , FirstFragment.displayName
                            , orderListData.get(position).getOrder_foodName()
                            , orderListData.get(position).getOrder_price()
                            , orderListData.get(position).getOrder_plusCount()
                            , "null"
                            , orderListData.get(position).getOrder_date()
                            , "null"
                            , String.valueOf(MainMenu.tableNumber));
                }else if (orderListData.get(position).getOrder_modify() == null){

                    insertOrderList = new InsertOrderList(String.valueOf(order_id)
                            ,FirstFragment.user_id
                            , FirstFragment.displayName
                            ,orderListData.get(position).getOrder_foodName()
                            ,orderListData.get(position).getOrder_price()
                            ,orderListData.get(position).getOrder_plusCount()
                            ,"null"
                            ,orderListData.get(position).getOrder_date()
                            ,orderListData.get(position).getOrder_takeAway()
                    ,String.valueOf(MainMenu.tableNumber));
                }else if (orderListData.get(position).getOrder_takeAway() == null){

                insertOrderList = new InsertOrderList(String.valueOf(order_id)
                        , FirstFragment.user_id
                        , FirstFragment.displayName
                        , orderListData.get(position).getOrder_foodName()
                        , orderListData.get(position).getOrder_price()
                        , orderListData.get(position).getOrder_plusCount()
                        , orderListData.get(position).getOrder_modify()
                        , orderListData.get(position).getOrder_date()
                        , orderListData.get(position).getOrder_takeAway()
                        ,String.valueOf(MainMenu.tableNumber));
            } else {
                insertOrderList = new InsertOrderList(String.valueOf(order_id)
                        , FirstFragment.user_id
                        , FirstFragment.displayName
                        , orderListData.get(position).getOrder_foodName()
                        , orderListData.get(position).getOrder_price()
                        , orderListData.get(position).getOrder_plusCount()
                        , orderListData.get(position).getOrder_modify()
                        , orderListData.get(position).getOrder_date()
                        , orderListData.get(position).getOrder_takeAway()
                        ,String.valueOf(MainMenu.tableNumber));
            }

            Call<InsertOrderList> call = userRepository.PostOrder(insertOrderList);

            call.enqueue(new Callback<InsertOrderList>() {
                @Override
                public void onResponse(Call<InsertOrderList> call, Response<InsertOrderList> response) {
                    if (response.isSuccessful() && response.body() != null){
                        if (response.body().Code.equalsIgnoreCase("200")){
                            Log.d("InsertSusscess","InsertSusscess");
                        }else {
                            listener.onError();
                        }
                    }else {
                        listener.onError();
                    }
                }

                @Override
                public void onFailure(Call<InsertOrderList> call, Throwable t) {
                    Log.d("on Insert isFailure",t.getMessage());
                }
            });
        }

    }

