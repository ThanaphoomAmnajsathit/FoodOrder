package com.example.foodmanagement.presentaions.views.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodmanagement.MainActivity;
import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.TableNumberInterface;
import com.example.foodmanagement.presentaions.presenters.TableNumberPresenter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectPeopleFragment extends Fragment implements TableNumberInterface.view {

    private TableNumberPresenter presenter;
    private static int tableNumber = 0;
    private static int sumOfPeople = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textview_tableNumber)
    TextView tv_table_number;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textView_sumOfPeople)
    TextView tv_sumOfPeople;

    public static void newInstance(int positionTable){
        tableNumber = positionTable;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_number, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        presenter = new TableNumberPresenter(this);
        setTextViewTableNumber();
    }

    @SuppressLint("DefaultLocale")
    private void setTextViewTableNumber(){
        tv_table_number.setText(String.format("Table : %d", tableNumber));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.imageView_back_tablenumber)
    void onClickBack(){
        NavHostFragment.findNavController(this).popBackStack();
    }

    @SuppressLint({"DefaultLocale", "NonConstantResourceId"})
    @OnClick(R.id.imageView_plus_tableNumber)
    void onClickPlus(){
        presenter.plusPeople(sumOfPeople);
    }

    @SuppressLint({"DefaultLocale", "NonConstantResourceId"})
    @OnClick(R.id.imageView_minus_tableNumber)
    void onClickMinus(){
        presenter.minusPeople(sumOfPeople);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.button_ok)
    void onClickOk(){
        if (sumOfPeople > 0) {
            updateTable();
            Intent intent = new Intent();
            intent.putExtra("tableNumber", tableNumber);
            intent.putExtra("peopleSum", sumOfPeople);
            intent.setClass(getContext(), MainMenu.class);
            startActivity(intent);
        }else {
            alertDialog();
        }
    }

    private TableNumberPresenter updateTablePresenter;
    private void updateTable(){
        try {
            updateTablePresenter = new TableNumberPresenter(this,String.valueOf(tableNumber),FirstFragment.user_id
                    ,String.valueOf(sumOfPeople),"true");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void alertDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("");
        alertDialog.setMessage("Please select a many of people");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onSetTextViewPeople(int sumOfPeople){
        SelectPeopleFragment.sumOfPeople = sumOfPeople;
        tv_sumOfPeople.setText(MessageFormat.format("{0} People", sumOfPeople));
    }

}