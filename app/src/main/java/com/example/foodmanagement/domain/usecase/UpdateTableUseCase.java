package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.InsertUser;
import com.example.foodmanagement.models.StatusTable;
import com.example.foodmanagement.presentaions.presenters.RegisterPresenter;
import com.example.foodmanagement.presentaions.presenters.TableNumberPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTableUseCase {
    private UserRepository userRepository;
    private TableNumberPresenter listener;

    public UpdateTableUseCase(UserRepository userRepository, TableNumberPresenter listener, String table_id
            , String user_id, String table_people
            , String table_isbusy) {
        this.userRepository = userRepository;
        this.listener = listener;
        updateTable(table_id,user_id,table_people,table_isbusy);
    }

    private void updateTable(String table_id,String user_id,String table_people
            ,String table_isbusy){

        StatusTable statusTable = new StatusTable(table_id,user_id,table_people,table_isbusy);

        Call<StatusTable> call = userRepository.PostTable(statusTable);
        call.enqueue(new Callback<StatusTable>() {
            @Override
            public void onResponse(Call<StatusTable> call, Response<StatusTable> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (response.body().Code.equalsIgnoreCase("200")){
                        Log.d("UpdateSusscess","UpdateSusscess");
                    }else {
                        listener.onError();
                    }
                }else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<StatusTable> call, Throwable t) {
                Log.d("on Update isFailure",t.getMessage());
            }
        });
    }
}
