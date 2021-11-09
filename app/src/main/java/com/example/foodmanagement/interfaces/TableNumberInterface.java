package com.example.foodmanagement.interfaces;

public interface TableNumberInterface {

    interface view{

        void onSetTextViewPeople(int sumOfPeople);

    }

    interface presenter{

        void plusPeople(int sumOfPeople);

        void minusPeople(int sumOfPeople);

    }
}
