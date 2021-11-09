package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.foodmanagement.domain.repositories.DisposableObservable;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.ReturnProducts;
import com.example.foodmanagement.presentaions.presenters.DrinkMenusPresenter;
import com.example.foodmanagement.presentaions.presenters.FoodMenusPresenter;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class GetProductUseCase extends UseCase<retrofit2.Response<ReturnProducts>, Void> {
    private UserRepository userRepository;
    private FoodMenusPresenter foodListener;
    private DrinkMenusPresenter drinkListener;
    private String category_id;
    private String search;
    private boolean isSearch;

    public GetProductUseCase(UserRepository userRepository, FoodMenusPresenter foodListener,
                             String category_id,String search,boolean isSearch){
        this.userRepository = userRepository;
        this.foodListener = foodListener;
        this.category_id = category_id;
        this.search = search;
        this.isSearch = isSearch;
    }

    public GetProductUseCase(UserRepository userRepository, DrinkMenusPresenter drinkListener,
                             String category_id,String search,boolean isSearch){
        this.userRepository = userRepository;
        this.drinkListener = drinkListener;
        this.category_id = category_id;
        this.search = search;
        this.isSearch = isSearch;
    }

    @Override
    public Observable<Response<ReturnProducts>> buildObservable(Void aVoid) {
        if (isSearch){
            return userRepository.searchProduct(search);
        }else {
            return userRepository.getProduct(category_id);
        }
    }

    @Override
    protected DisposableObserver<retrofit2.Response<ReturnProducts>> getDisposableObservable() {
        return new GetProductUseCase.DisposableGetProduct();
    }

    class DisposableGetProduct extends DisposableObservable<Response<ReturnProducts>> {

        @Override
        public void onNext(retrofit2.Response<ReturnProducts> response) {
            super.onNext(response);
            if (foodListener != null) {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().Code.equals("200")) {
                        //listener.onSetUserListLoadMore(response.body().Users);
                        foodListener.onSetProducts(response.body().Products);
                    } else {
                        foodListener.onError();

                    }
                } else {
                    foodListener.onError();

                }
            }else {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().Code.equals("200")) {
                        //listener.onSetUserListLoadMore(response.body().Users);
                        drinkListener.onSetProducts(response.body().Products);
                    } else {
                        drinkListener.onError();

                    }
                } else {
                    drinkListener.onError();
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            //listener.onError();
            Log.d("ONGTEST", e.getMessage().toString());
        }
    }


}
