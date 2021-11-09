package com.example.foodmanagement.presentaions.views.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.FirstFragmentInterface;
import com.example.foodmanagement.models.User;
import com.example.foodmanagement.presentaions.presenters.FirstFragmentPresenter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstFragment extends Fragment implements FirstFragmentInterface.view {

    //button pin
    @BindView(R.id.button_pin_1)
    Button btn_pin_1;
    @BindView(R.id.button_pin_2)
    Button btn_pin_2;
    @BindView(R.id.button_pin_3)
    Button btn_pin_3;
    @BindView(R.id.button_pin_4)
    Button btn_pin_4;
    @BindView(R.id.button_pin_5)
    Button btn_pin_5;
    @BindView(R.id.button_pin_6)
    Button btn_pin_6;
    @BindView(R.id.button_pin_7)
    Button btn_pin_7;
    @BindView(R.id.button_pin_8)
    Button btn_pin_8;
    @BindView(R.id.button_pin_9)
    Button btn_pin_9;
    @BindView(R.id.button_pin_0)
    Button btn_pin_0;

    @BindView(R.id.button_delete_pin)
    ImageView btn_delete_pin;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.textView_login_header)
    TextView textView_header;

    //PassCode Incorrect
    @BindView(R.id.textview_passwordincorrect)
    TextView tv_pinIncorrect;
    @BindView(R.id.imageView_warning)
    ImageView iv_warning;


    TextView tv_pinNumber;
    EditText et_name;
    Button btn_enter;
    TextView tv_register;

    private LinearLayout linearPin;
    private LinearLayout linearBtnPin;
    private FirstFragmentPresenter presenter;

    public static String displayName;
    public static String user_id;
    private List<User> user;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        init(view);
        onClickButton();
        firstOpenApp();
    }

    private void init(View view){
        presenter = new FirstFragmentPresenter(this);
        linearPin = view.findViewById(R.id.linear_pin);
        linearBtnPin = view.findViewById(R.id.linear_btn_pin);
        tv_pinNumber = view.findViewById(R.id.textView_pinNumber);
        et_name = view.findViewById(R.id.editText_name_login);
        btn_enter = view.findViewById(R.id.button_enter);
        tv_register = view.findViewById(R.id.textView_register);
    }

    private void firstOpenApp(){
        tv_pinNumber.setText("Enter your display name");
        linearPin.setVisibility(View.GONE);
        linearBtnPin.setVisibility(View.GONE);
    }

    private void inputName(){
        if (et_name != null) {
            displayName = et_name.getText().toString();
        }
    }

    @OnClick(R.id.button_enter)
    public void onClickEnter(){
        inputName();
        presenter = new FirstFragmentPresenter(this,displayName);
        showProgressBar();
        disableBtnEnter();
    }

    @OnClick(R.id.textView_register)
    public void onClickRegister(){
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_registerFragment);
    }

    private void onClickButton(){
        btn_pin_1.setOnClickListener(v -> presenter.inputPin("1"));

        btn_pin_2.setOnClickListener(v -> presenter.inputPin("2"));

        btn_pin_3.setOnClickListener(v -> presenter.inputPin("3"));

        btn_pin_4.setOnClickListener(v -> presenter.inputPin("4"));

        btn_pin_5.setOnClickListener(v -> presenter.inputPin("5"));

        btn_pin_6.setOnClickListener(v -> presenter.inputPin("6"));

        btn_pin_7.setOnClickListener(v -> presenter.inputPin("7"));

        btn_pin_8.setOnClickListener(v -> presenter.inputPin("8"));

        btn_pin_9.setOnClickListener(v -> presenter.inputPin("9"));

        btn_pin_0.setOnClickListener(v -> presenter.inputPin("0"));

        btn_delete_pin.setOnClickListener(v -> presenter.deletePin());
    }

    @Override
    public void onAddPinCode(){
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ellipsefirstfragment);
        imageView.setPadding(50,0,50,0);
        linearPin.addView(imageView);
    }

    @Override
    public void onDeletePinCode(int position){
        linearPin.removeViewAt(position-1);
    }

    public void onDeleteAllPinCode(){
        linearPin.removeAllViews();
    }

    public void onPassCodeIncorrect(){
        tv_pinIncorrect.setVisibility(View.VISIBLE);
        iv_warning.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_pinIncorrect.setVisibility(View.GONE);
                iv_warning.setVisibility(View.GONE);
            }
        }, 600);
    }

    public void onNavFragment(){
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SelectTableFragment);
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() { progressBar.setVisibility(View.INVISIBLE);}

    public void disableBtnEnter(){ btn_enter.setEnabled(false);}

    public void enableBtnEnter() { btn_enter.setEnabled(true);}

    @Override
    public void onSetUser(List<User> users) {
        if (users.size() != 0) {
            user = users;
            user_id = users.get(0).getUser_id();
            onUserEnter();
        }else {
            Log.d("null","null");
            et_name.setError("Invalid name.");
            et_name.requestFocus();
        }
        Log.d("Get User Data",users.toString());
        hideProgressBar();
        enableBtnEnter();
    }

    private void onUserEnter(){
        tv_pinNumber.setText("Enter your pin nummber");
        textView_header.setText("Hello "+displayName);
        linearPin.setVisibility(View.VISIBLE);
        linearBtnPin.setVisibility(View.VISIBLE);
        et_name.setVisibility(View.GONE);
        btn_enter.setVisibility(View.GONE);
        tv_register.setVisibility(View.GONE);
    }

    public String getDbPassCode(){
        return user.get(0).getUser_pin();
    }

    public void disableButton(){
        btn_pin_1.setEnabled(false);
        btn_pin_2.setEnabled(false);
        btn_pin_3.setEnabled(false);
        btn_pin_4.setEnabled(false);
        btn_pin_5.setEnabled(false);
        btn_pin_6.setEnabled(false);
        btn_pin_7.setEnabled(false);
        btn_pin_8.setEnabled(false);
        btn_pin_9.setEnabled(false);
        btn_pin_0.setEnabled(false);
        btn_delete_pin.setEnabled(false);
    }

}