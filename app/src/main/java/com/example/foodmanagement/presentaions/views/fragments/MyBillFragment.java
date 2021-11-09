package com.example.foodmanagement.presentaions.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.MyBillInterface;
import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.presentaions.presenters.MyBillPresenter;
import com.example.foodmanagement.presentaions.recycleviewadapters.OrderRecyclerAdapter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBillFragment extends Fragment implements MyBillInterface.view {

    private MyBillPresenter presenter;

    private static RecyclerView recyclerView;
    private static OrderRecyclerAdapter adapter;
    private static ArrayList<OrderListData> orderListData;


    @BindView(R.id.textView_bill_table)
    TextView tv_bill_table;

    @BindView(R.id.textView_bill_name)
    TextView tv_bill_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textview_bill_quantity)
    TextView tv_bill_quantity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textview_bill_price)
    TextView tv_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textview_bill_vat)
    TextView tv_vat;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textview_bill_total)
    TextView tv_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progressBar_mybill)
    ProgressBar progressBar;

    private int quantity;
    private int price;
    private int vat;
    private int total;

    public static String table;
    public static String name;

    public MyBillFragment() {
        // Required empty public constructor
    }

    public static MyBillFragment newInstance(String table, String name) {
        MyBillFragment fragment = new MyBillFragment();
        Bundle args = new Bundle();
        args.putString("table",table);
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.recyclerview_myBill);
        recyclerView.setVisibility(View.GONE);
        adapter = new OrderRecyclerAdapter();
        orderListData = new ArrayList<>();
        presenter =  new MyBillPresenter(this,FirstFragment.user_id);
        setInvisible();
        progressBar.setVisibility(View.VISIBLE);
        tv_bill_table.setText("โต๊ะ "+table);
        tv_bill_name.setText(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_bill, container, false);
    }

    @Override
    public void onSetProduct(List<OrderListData> order) {
        recyclerView.setVisibility(View.VISIBLE);
        orderListData.addAll(order);
        setAdapter();
        findResultQuantity();
        findResultPrice();
        findResultVat();
        findResultTotal();
        setVisible();
        progressBar.setVisibility(View.GONE);
    }

    public void setAdapter(){
        adapter = new OrderRecyclerAdapter(getContext(),orderListData);
        recyclerView.setAdapter(adapter);
    }

    private void findResultQuantity(){
        for (int i = 0; i < orderListData.size() ; i++) {
            String str =  orderListData.get(i).getOrder_plusCount();
            int j = Integer.parseInt(str);
            quantity += j;
        }
        tv_bill_quantity.setText(String.valueOf(quantity));
    }

    private void findResultPrice(){
        for (int i = 0; i < orderListData.size() ; i++) {
            int p = Integer.parseInt(orderListData.get(i).getOrder_price());
            int c = Integer.parseInt(orderListData.get(i).getOrder_plusCount());;
            int result = p * c;
            price += result;
        }
        tv_price.setText(price+" Bath");
    }

    private void findResultVat(){
        vat = price * 7/100;
        tv_vat.setText(vat+" Bath");
    }

    private void findResultTotal(){
        total = price + vat;
        tv_total.setText(total+" Bath");
    }

    private void setInvisible(){
        tv_bill_quantity.setVisibility(View.INVISIBLE);
        tv_price.setVisibility(View.INVISIBLE);
        tv_vat.setVisibility(View.INVISIBLE);
        tv_total.setVisibility(View.INVISIBLE);
    }

    private void setVisible(){
        tv_bill_quantity.setVisibility(View.VISIBLE);
        tv_price.setVisibility(View.VISIBLE);
        tv_vat.setVisibility(View.VISIBLE);
        tv_total.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.button_mybill_back)
    public void onClickBack(){
        NavHostFragment.findNavController(this).popBackStack();
    }

}