package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.DisposableObservable;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.ReturnMyBill;
import com.example.foodmanagement.models.ReturnProducts;
import com.example.foodmanagement.presentaions.presenters.FoodMenusPresenter;
import com.example.foodmanagement.presentaions.presenters.MyBillPresenter;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class GetMyBillUseCase extends UseCase<retrofit2.Response<ReturnMyBill>, Void> {
        private UserRepository userRepository;
        private MyBillPresenter listener;
        private String user_id;

        public GetMyBillUseCase(UserRepository userRepository, MyBillPresenter listener, String user_id){
            this.userRepository = userRepository;
            this.listener = listener;
            this.user_id = user_id;
        }

        @Override
        public Observable<Response<ReturnMyBill>> buildObservable(Void aVoid) {
            return userRepository.getMyBill(user_id);
        }

        @Override
        protected DisposableObserver<Response<ReturnMyBill>> getDisposableObservable() {
            return new GetMyBillUseCase.DisposableGetMyBill();
        }

        class DisposableGetMyBill extends DisposableObservable<Response<ReturnMyBill>> {

            @Override
            public void onNext(retrofit2.Response<ReturnMyBill> response) {
                super.onNext(response);
                if (response.isSuccessful() && response.body() != null) {

                    if(response.body().Code.equals("200"))
                    {
                        //listener.onSetUserListLoadMore(response.body().Users);
                        Log.d("====================>>>>>>>","=====================");
                        Log.d("====================>>>>>>>","=====================");
                        Log.d("====================>>>>>>>","=====================");
                        listener.onSetProduct(response.body().myBill);
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
                //listener.onError();
                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","=====================");
                Log.d("ONGTEST", e.getMessage().toString());
            }
        }
}
