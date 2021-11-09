package com.example.foodmanagement.presentaions.presenters;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetMyBillUseCase;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.interfaces.MyBillInterface;
import com.example.foodmanagement.models.OrderListData;

import java.util.ArrayList;
import java.util.List;

public class MyBillPresenter implements MyBillInterface.presenter {

    private MyBillInterface.view view;

    public MyBillPresenter(MyBillInterface.view view,String user_id) {
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        GetMyBillUseCase getMyBillUseCase = new GetMyBillUseCase(repository,this,user_id);
        getMyBillUseCase.execute(null);
    }


    @Override
    public void onSetProduct(List<OrderListData> order) {
        view.onSetProduct(order);
    }

    @Override
    public void onError() {

    }
}
