package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.interfaces.RegisterInterface;
import com.example.foodmanagement.models.InsertOrderList;
import com.example.foodmanagement.models.InsertUser;
import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.presentaions.presenters.RegisterPresenter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;
import com.example.foodmanagement.presentaions.views.fragments.RegisterFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertUserUseCase {
    private UserRepository userRepository;
    private RegisterPresenter listener;

    public InsertUserUseCase(UserRepository userRepository,RegisterPresenter listener,String userId,String displayName,String firstName
            ,String lastName,String pin,String date) {
        this.userRepository = userRepository;
        this.listener = listener;
        insertOrder(userId,displayName,firstName,lastName,pin,date);
        Log.d("======",userId+displayName+firstName+lastName+pin+date);

    }

    private void insertOrder(String userId,String displayName,String firstName
            ,String lastName,String pin,String date){

        InsertUser insertUser = new InsertUser(userId,displayName,firstName,lastName,pin,date);

        Call<InsertUser> call = userRepository.PostUser(insertUser);
        call.enqueue(new Callback<InsertUser>() {
            @Override
            public void onResponse(Call<InsertUser> call, Response<InsertUser> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (response.body().Code.equalsIgnoreCase("200")){
                        Log.d("InsertSusscess","InsertSusscess");
                        listener.onCallApiListener();
                    }else {
                        listener.onError();
                    }
                }else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<InsertUser> call, Throwable t) {
                Log.d("on Insert isFailure",t.getMessage());
            }
        });
    }
}
