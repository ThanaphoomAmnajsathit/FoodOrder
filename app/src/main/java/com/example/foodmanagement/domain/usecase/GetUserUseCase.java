package com.example.foodmanagement.domain.usecase;

import android.util.Log;

import com.example.foodmanagement.domain.repositories.DisposableObservable;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.models.ReturnUsers;
import com.example.foodmanagement.presentaions.presenters.DrinkMenusPresenter;
import com.example.foodmanagement.presentaions.presenters.FirstFragmentPresenter;

import io.reactivex.Observable;
import retrofit2.Response;

public class GetUserUseCase extends UseCase<Response<ReturnUsers>, Void> {
    private UserRepository userRepository;
    private FirstFragmentPresenter listener;
    private String display_name;

    public GetUserUseCase(UserRepository userRepository, FirstFragmentPresenter listener,String display_name){
        this.userRepository = userRepository;
        this.listener = listener;
        this.display_name = display_name;
    }


    @Override
    public Observable<Response<ReturnUsers>> buildObservable(Void aVoid) {
        return userRepository.getUser(display_name);
    }

    @Override
    protected DisposableObservable<Response<ReturnUsers>> getDisposableObservable() {
        return new DisposableGetUsers();
    }

    class DisposableGetUsers extends DisposableObservable<Response<ReturnUsers>> {

        @Override
        public void onNext(Response<ReturnUsers> response) {
            super.onNext(response);
            if (response.isSuccessful() && response.body() != null) {
                if(response.body().Code.equals("200"))
                {
                    listener.onSetUser(response.body().user);
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
