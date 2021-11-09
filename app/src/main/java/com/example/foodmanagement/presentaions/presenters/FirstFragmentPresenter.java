package com.example.foodmanagement.presentaions.presenters;


import android.opengl.Visibility;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetUserUseCase;
import com.example.foodmanagement.interfaces.FirstFragmentInterface;
import com.example.foodmanagement.models.User;
import com.example.foodmanagement.presentaions.views.fragments.FirstFragment;

import java.util.List;

public class FirstFragmentPresenter implements FirstFragmentInterface.presenter {

    private FirstFragmentInterface.view view;

    private String passCode = "";
    private String dbPassCode;
    private int position = 0;

    public FirstFragmentPresenter(FirstFragmentInterface.view view,String display_name) {
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        GetUserUseCase getUserUseCase = new GetUserUseCase(repository,this, display_name);
        getUserUseCase.execute(null);
    }

    public FirstFragmentPresenter(FirstFragmentInterface.view view) {
        this.view = view;
    }

    @Override
    public void inputPin(String buttonNumber) {
        if (position <= 3) {
            //Log.d("test", String.valueOf(buttonNumber));
            passCode += buttonNumber;
            view.onAddPinCode();
            position++;
        }
        if (position == 4) {
            Log.d("passCode", passCode);
            dbPassCode = view.getDbPassCode();
            if (passCode.equals(dbPassCode)){
                view.disableButton();
                navFragment();
            }else {
                Log.d("passcode incorrect","passcode incorrect");
                view.onDeleteAllPinCode();
                view.onPassCodeIncorrect();
                passCode = "";
                position = 0;
            }
        }
    }

    @Override
    public void deletePin(){
        if (position > 0) {
            view.onDeletePinCode(position);

            StringBuilder stringBuilder = new StringBuilder(passCode);
            passCode = stringBuilder.deleteCharAt(position-1).toString();
            Log.d("Delete_PassCode",passCode);

            position--;
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSetUser(List<User> users) {
        view.onSetUser(users);
    }

    public void navFragment(){
        view.showProgressBar();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.onNavFragment();
            }
        }, 600);
    }
}
