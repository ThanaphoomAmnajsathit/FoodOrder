package com.example.foodmanagement.presentaions.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.RegisterInterface;
import com.example.foodmanagement.presentaions.presenters.RegisterPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterFragment extends Fragment implements RegisterInterface.view {

    @BindView(R.id.editText_register_displayName)
    EditText et_displayName;
    @BindView(R.id.editText_register_firstName)
    EditText et_firstName;
    @BindView(R.id.editText_register_lastname)
    EditText et_lastName;
    @BindView(R.id.editText_register_pin)
    EditText et_pin;
    @BindView(R.id.editText_register_confirmPin)
    EditText et_ConfirmPin;

    @BindView(R.id.progressBar_register)
    ProgressBar progressBar_register;

    @BindView(R.id.button_Signup)
    Button btn_signUp;

    private String userId;
    private String displayName;
    private String firstName;
    private String lastName;
    private String pin;
    private String date;

    private RegisterPresenter presenter;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        init(view);
    }

    private void init(View view){

    }

    private boolean checkPassword(){
        String pin = et_pin.getText().toString();
        String ConfirmPin = et_ConfirmPin.getText().toString();
        if (pin.equals(ConfirmPin)){
            return true;
        }else {
            et_ConfirmPin.setError("The password not match.");
            et_ConfirmPin.requestFocus();
            return false;
        }
    }

    private boolean checkPasswordLength(){
        String pin = et_pin.getText().toString();
        if (pin.length() < 4){
            et_pin.setError("The password must be of length 4.");
            et_pin.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private boolean checkDisplayName(){
        String displayName = et_displayName.getText().toString();
        if (displayName.length() > 0){
            return true;
        }else {
            et_displayName.setError("The display name is empty.");
            et_displayName.requestFocus();
            return false;
        }
    }

    private boolean checkFirstName(){
        String firstName = et_firstName.getText().toString();
        if (firstName.length() >0){
            return true;
        }else {
            et_firstName.setError("The first name is empty.");
            et_firstName.requestFocus();
            return false;
        }
    }

    private boolean checkLastName(){
        String lastName = et_lastName.getText().toString();
        if (lastName.length() >0){
            return true;
        }else {
            et_lastName.setError("The last name is empty.");
            et_lastName.requestFocus();
            return false;
        }
    }

    public int random(){
        Random random = new Random();
        int r = random.nextInt(9000);
        return r;
    }

    @OnClick(R.id.button_Signup)
    public void onClickSignUp(){
        if (checkPassword() && checkPasswordLength() && checkDisplayName() && checkFirstName() && checkLastName()){
            progressBar_register.setVisibility(View.VISIBLE);
            userId = String.valueOf(random());
            displayName = et_displayName.getText().toString();
            firstName = et_firstName.getText().toString();
            lastName = et_lastName.getText().toString();
            pin = et_ConfirmPin.getText().toString();
            date = getDate();
            callAPIInsertUser(userId,displayName,firstName,lastName,pin,date);
            btn_signUp.setEnabled(false);
        }
    }

    private String getDate(){
        Date dNow = new Date( );
        @SuppressLint("SimpleDateFormat") SimpleDateFormat ft =
                new SimpleDateFormat ("dd.MM.yyyy");
        return ft.format(dNow);
    }


    private void callAPIInsertUser(String userId,String displayName,String firstName,String lastName,String pin,String date){
        presenter = new RegisterPresenter(this,userId,displayName,firstName,lastName,pin,date);
    }

    @OnClick(R.id.imageView_back_register)
    public void onClickBack(){
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onCallApiListener() {
        NavHostFragment.findNavController(this).popBackStack();
    }
}