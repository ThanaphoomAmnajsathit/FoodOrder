package com.example.foodmanagement.presentaions.presenters;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.interfaces.DrinkMenusInterface;
import com.example.foodmanagement.interfaces.FoodMenusInterface;
import com.example.foodmanagement.models.Product;
import com.example.foodmanagement.models.User;

import java.util.List;

public class DrinkMenusPresenter implements DrinkMenusInterface.presenter {

    private DrinkMenusInterface.view view;

    public DrinkMenusPresenter(DrinkMenusInterface.view view, String category_id){
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        GetProductUseCase getProductUseCase = new GetProductUseCase(repository,this,category_id,null,false);
        getProductUseCase.execute(null);
    }

    public DrinkMenusPresenter(){}

    @Override
    public void onError() {

    }

    @Override
    public void onSetProducts(List<Product> products) {
        view.onSetProduct(products);
    }


}
