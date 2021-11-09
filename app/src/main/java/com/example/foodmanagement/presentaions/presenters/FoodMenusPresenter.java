package com.example.foodmanagement.presentaions.presenters;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.interfaces.FoodMenusInterface;
import com.example.foodmanagement.models.Product;
import com.example.foodmanagement.presentaions.recycleviewadapters.MenusRecyclerAdapter;

import java.util.List;

public class FoodMenusPresenter implements FoodMenusInterface.presenter {

    private FoodMenusInterface.view view;

    public FoodMenusPresenter(FoodMenusInterface.view view,String category_id){
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        GetProductUseCase getProductUseCase = new GetProductUseCase(repository, this,category_id,null,false);
        getProductUseCase.execute(null);
    }

    public FoodMenusPresenter() {
    }

    @Override
    public void onSetProducts(List<Product> products){
        view.onSetProduct(products);
    }

    @Override
    public void onError(){ }


}
