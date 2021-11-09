package com.example.foodmanagement.presentaions.presenters;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.InsertUserUseCase;
import com.example.foodmanagement.domain.usecase.UpdateTableUseCase;
import com.example.foodmanagement.interfaces.TableNumberInterface;

public class TableNumberPresenter implements TableNumberInterface.presenter {

    private TableNumberInterface.view view;

    public TableNumberPresenter(TableNumberInterface.view view,String table_id, String user_id
    ,String table_people,String table_isbusy){
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        UpdateTableUseCase updateTableUseCase = new UpdateTableUseCase(repository, this
                ,table_id,user_id,table_people,table_isbusy);
    }

    public TableNumberPresenter(TableNumberInterface.view view){
        this.view = view;
    }

    public TableNumberPresenter(){}

    @Override
    public void plusPeople(int sumOfPeople){
        ++sumOfPeople;
        view.onSetTextViewPeople(sumOfPeople);
    }

    @Override
    public void minusPeople(int sumOfPeople){
        if (sumOfPeople != 0){
            --sumOfPeople;
            view.onSetTextViewPeople(sumOfPeople);
        }
    }

    public void onError(){}

}
