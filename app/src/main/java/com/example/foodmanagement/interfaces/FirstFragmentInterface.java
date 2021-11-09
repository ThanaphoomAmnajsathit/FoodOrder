package com.example.foodmanagement.interfaces;

import com.example.foodmanagement.models.User;

import java.util.List;

public interface FirstFragmentInterface {

    interface view{

        void onAddPinCode();

        void onDeletePinCode(int position);

        void onDeleteAllPinCode();

        void onNavFragment();

        void showProgressBar();

        void onSetUser(List<User> users);

        String getDbPassCode();

        void onPassCodeIncorrect();

        void disableButton();
    }

    interface presenter{

        void inputPin(String buttonNumber);

        void deletePin();

        void onError();

        void onSetUser(List<User> users);

    }
}
