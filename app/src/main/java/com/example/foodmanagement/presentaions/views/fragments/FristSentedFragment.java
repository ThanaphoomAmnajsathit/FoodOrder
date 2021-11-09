package com.example.foodmanagement.presentaions.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodmanagement.MainActivity;
import com.example.foodmanagement.R;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.UpdateTableUseCase;
import com.example.foodmanagement.interfaces.FirstSentedInterface;
import com.example.foodmanagement.presentaions.presenters.FirstSentedPresenter;
import com.example.foodmanagement.presentaions.presenters.TableNumberPresenter;
import com.example.foodmanagement.presentaions.recycleviewadapters.MenusRecyclerAdapter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;
import com.example.foodmanagement.presentaions.views.activities.SentedOrderActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FristSentedFragment extends Fragment implements FirstSentedInterface.view {

    private MainMenu mainMenu;
    private FirstSentedPresenter presenter;

    public FristSentedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mainMenu = new MainMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frist_sented, container, false);
    }

    @OnClick(R.id.imageView_mybill)
    public void onClickMyBill(){
        NavHostFragment.findNavController(this).navigate(R.id.action_fristSentedFragment_to_myBillFragment);
    }

    @OnClick(R.id.imageView_orderagain)
    public void onClickOrderAgain(){
        MainMenu.destroy = true;
        mainMenu.clearAdapter();
        MenusRecyclerAdapter.isClickable = true;
        Intent intent = new Intent(getActivity(),MainMenu.class);
        startActivity(intent);
    }

    @OnClick(R.id.imageView_checkout)
    public void onClickCheckout(){
        presenter = new FirstSentedPresenter(this,FirstFragment.user_id);
        Log.d("User_id",FirstFragment.user_id);
        logout();
    }

    public void logout(){
        TableNumberPresenter presenter = new TableNumberPresenter();
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        UpdateTableUseCase updateTableUseCase = new UpdateTableUseCase(repository,presenter,MyBillFragment.table,"0","0","false");
        mainMenu.clearAdapter();
        MenusRecyclerAdapter.isClickable = true;
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}