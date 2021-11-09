package com.example.foodmanagement.presentaions.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.models.OrderListData;


public class ModifierActivity extends AppCompatActivity {

    private MainMenu mainMenu;

    private ImageView iv_back_modifier;
    private ImageView iv_plus_modefier;
    private ImageView iv_minus_modefier;
    private TextView tv_foodname_modifier;
    private TextView tv_sumOfFood;
    private EditText et_modifyBox;
    private Button btn_save;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch_takeAway;

    private static OrderListData orderListData;
    private static int orderListPosition;
    private int foodCount;
    private String isSwitchState ="0";

    public ModifierActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        init();
        onClickBack();
        onSetOrderDetail();
        onClickSignFoodCount();
        onClickButtonSave();
        takeAway();
    }

    private void init(){

        iv_back_modifier = findViewById(R.id.imageView_back_modifier);
        iv_plus_modefier = findViewById(R.id.imageView_plus_modifier);
        iv_minus_modefier = findViewById(R.id.imageView_minus_modifier);
        tv_foodname_modifier = findViewById(R.id.textview_foodname_modifier);
        tv_sumOfFood = findViewById(R.id.textView_sumOfFood);
        et_modifyBox = findViewById(R.id.editText_modifier);
        switch_takeAway = findViewById(R.id.switch_takeAway);
        btn_save = findViewById(R.id.button_save);

        mainMenu = new MainMenu();
    }

    public void onSetOrderListData(OrderListData orderListData,int orderListPosition){
        ModifierActivity.orderListData = orderListData;
        ModifierActivity.orderListPosition = orderListPosition;
    }

    private void onSetOrderDetail(){
        if (orderListData != null){
            tv_foodname_modifier.setText(orderListData.getOrder_foodName());
            tv_sumOfFood.setText(orderListData.getOrder_plusCount());
            foodCount = Integer.parseInt(orderListData.getOrder_plusCount());
            if (orderListData.getOrder_modify() != null){
                et_modifyBox.setText(orderListData.getOrder_modify());
            }
            if (orderListData.getOrder_takeAway() != null) {
                if (orderListData.getOrder_takeAway().equalsIgnoreCase("1")) {
                    switch_takeAway.setChecked(true);
                    isSwitchState = "1";
                }
            }
        }
    }

    private void onClickBack(){
        iv_back_modifier.setOnClickListener(v -> onBackPressed());
    }

    private void onClickSignFoodCount(){

        iv_plus_modefier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodCount <= 10){
                    foodCount++;
                }
                tv_sumOfFood.setText(String.valueOf(foodCount));
            }
        });

        iv_minus_modefier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodCount >= 2){
                    foodCount--;
                }
                tv_sumOfFood.setText(String.valueOf(foodCount));
            }
        });
    }

    private void takeAway(){
        switch_takeAway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        isSwitchState = "1";
                    }else {
                        isSwitchState = null;
                    }
                }
        });
}


    private void onClickButtonSave(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et_modifyBox.getText().toString();
                if (str.length() != 0){
                    mainMenu.onUpdateFoodCount(orderListPosition,String.valueOf(foodCount),str,isSwitchState);
                }else {
                    mainMenu.onUpdateFoodCount(orderListPosition,String.valueOf(foodCount),null,isSwitchState);
                }
                onBackPressed();
            }
        });
    }


}