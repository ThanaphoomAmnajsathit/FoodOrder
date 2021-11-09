package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.DeleteOrders;
import com.example.foodmanagement.presentaions.presenters.FirstSentedPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteOrdersUseCase {

    private UserRepository userRepository;
    private FirstSentedPresenter listener;
    private String user_id;


    public DeleteOrdersUseCase(UserRepository userRepository, FirstSentedPresenter listener,String user_id){
        this.userRepository = userRepository;
        this.listener = listener;
        this.user_id = user_id;
        deleteUser();
        Log.d("Delete","Delete Orders ");
    }

    private void deleteUser(){
        DeleteOrders deleteOrders = new DeleteOrders(user_id);
        Call<DeleteOrders> call = userRepository.deleteOrders(deleteOrders);
        call.enqueue(new Callback<DeleteOrders>() {
            @Override
            public void onResponse(Call<DeleteOrders> call, Response<DeleteOrders> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (response.body().Code.equals("200")){
                        Log.d("Success","Delete Orders Success");

                    }else {
                        listener.onError();
                    }
                }else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<DeleteOrders> call, Throwable t) {

            }
        });
    }
}
