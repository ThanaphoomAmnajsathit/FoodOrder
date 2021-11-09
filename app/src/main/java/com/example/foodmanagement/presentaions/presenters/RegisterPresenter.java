package com.example.foodmanagement.presentaions.presenters;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.domain.usecase.InsertUserUseCase;
import com.example.foodmanagement.interfaces.RegisterInterface;
import com.example.foodmanagement.models.InsertUser;

public class RegisterPresenter implements RegisterInterface.presenter {

    RegisterInterface.view view;

    public RegisterPresenter(RegisterInterface.view view,String userId,String displayName,String firstName
    ,String lastName,String pin,String date){
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        InsertUserUseCase insertUserUseCase = new InsertUserUseCase(repository, this
                ,userId,displayName,firstName,lastName,pin,date);
    }

    @Override
    public void onCallApiListener() {
        view.onCallApiListener();
    }

    @Override
    public void onError(){}
}
