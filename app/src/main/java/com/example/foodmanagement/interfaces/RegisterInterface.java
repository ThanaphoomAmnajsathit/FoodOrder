package com.example.foodmanagement.interfaces;

public interface RegisterInterface {

    interface view {
        void onCallApiListener();
    }

    interface presenter {
        void onCallApiListener();

        void onError();
    }
}
