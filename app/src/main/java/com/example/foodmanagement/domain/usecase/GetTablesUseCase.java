package com.example.foodmanagement.domain.usecase;

import android.service.controls.Control;
import android.util.Log;

import com.example.foodmanagement.domain.repositories.DisposableObservable;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.ReturnTables;
import com.example.foodmanagement.models.ReturnUsers;
import com.example.foodmanagement.presentaions.presenters.FirstFragmentPresenter;
import com.example.foodmanagement.presentaions.presenters.SelectTablePresenter;

import java.util.Arrays;

import io.reactivex.Observable;
import retrofit2.Response;

public class GetTablesUseCase extends UseCase<Response<ReturnTables>, Void> {
    private UserRepository userRepository;
    private SelectTablePresenter listener;

    public GetTablesUseCase(UserRepository userRepository, SelectTablePresenter listener){
        this.userRepository = userRepository;
        this.listener = listener;
    }


    @Override
    public Observable<Response<ReturnTables>> buildObservable(Void aVoid) {
        return userRepository.getTable();
    }

    @Override
    protected DisposableObservable<Response<ReturnTables>> getDisposableObservable() {
        return new GetTablesUseCase.DisposableGetTables();
    }

    class DisposableGetTables extends DisposableObservable<Response<ReturnTables>> {

        @Override
        public void onNext(Response<ReturnTables> response) {
            super.onNext(response);
            if (response.isSuccessful() && response.body() != null) {
                if(response.body().Code.equals("200"))
                {
                    listener.onSetTables(response.body().tableData);
                    Log.d("GetTable", String.valueOf(response.body().tableData));
                }else{
                    listener.onError();

                }
            } else {
                listener.onError();

            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            listener.onError();
            Log.d("ONGTEST", e.getMessage().toString());
        }
    }
}
