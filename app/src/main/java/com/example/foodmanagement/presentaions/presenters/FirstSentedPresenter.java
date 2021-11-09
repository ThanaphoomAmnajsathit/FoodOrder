package com.example.foodmanagement.presentaions.presenters;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.DeleteOrdersUseCase;
import com.example.foodmanagement.interfaces.FirstSentedInterface;

public class FirstSentedPresenter implements FirstSentedInterface.presenter {

    private FirstSentedInterface.view view;

    public FirstSentedPresenter(FirstSentedInterface.view view,String user_id) {
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        DeleteOrdersUseCase deleteOrdersUseCase = new DeleteOrdersUseCase(repository,this, user_id);
    }

    public void logout(){
        view.logout();
    }

    public void onError(){
        Log.d("onDelete Status","isError");
    }
}
