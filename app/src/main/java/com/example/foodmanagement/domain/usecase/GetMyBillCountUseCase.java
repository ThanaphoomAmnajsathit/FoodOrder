package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.DisposableObservable;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.ReturnMyBill;
import com.example.foodmanagement.models.ReturnMyBillCount;
import com.example.foodmanagement.presentaions.presenters.MyBillPresenter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class GetMyBillCountUseCase extends UseCase<retrofit2.Response<ReturnMyBillCount>, Void> {
    private UserRepository userRepository;
    private MainMenu listener;

    public GetMyBillCountUseCase(UserRepository userRepository, MainMenu listener){
        this.userRepository = userRepository;
        this.listener = listener;
    }

    @Override
    public Observable<Response<ReturnMyBillCount>> buildObservable(Void aVoid) {
        return userRepository.getMyBillCount();
    }

    @Override
    protected DisposableObserver<Response<ReturnMyBillCount>> getDisposableObservable() {
        return new GetMyBillCountUseCase.DisposableGetMyBillCount();
    }

    class DisposableGetMyBillCount extends DisposableObservable<Response<ReturnMyBillCount>> {

        @Override
        public void onNext(retrofit2.Response<ReturnMyBillCount> response) {
            super.onNext(response);
            if (response.isSuccessful() && response.body() != null) {

                if(response.body().Code.equals("200"))
                {
                    //listener.onSetUserListLoadMore(response.body().Users);
                    Log.d("====================>>>>>>>","=====================");
                    Log.d("====================>>>>>>>","=====================");
                    Log.d("====================>>>>>>>","=====================");
                    listener.onSetNumRow(response.body().num_row);
                }else{
                    Log.d("error","error");
                    Log.d("error","error");
                    Log.d("error","error");

                }
            } else {

            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            //listener.onError();
            Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
            Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
            Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
            Log.d("ONGTEST", e.getMessage().toString());
        }
    }
}
