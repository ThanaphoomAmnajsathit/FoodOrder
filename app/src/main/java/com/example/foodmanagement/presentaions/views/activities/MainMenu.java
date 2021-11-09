package com.example.foodmanagement.presentaions.views.activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetMyBillCountUseCase;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.domain.usecase.InsertOrderUseCase;
import com.example.foodmanagement.models.MyBillNumRow;
import com.example.foodmanagement.models.OnPauseOrderSheetMainMenuActivity;
import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.presentaions.helper.MyButtonClickListener;
import com.example.foodmanagement.presentaions.helper.SwipeRecyclerViewHelper;
import com.example.foodmanagement.presentaions.pageradapter.MenuPagerAdapter2;
import com.example.foodmanagement.presentaions.presenters.FoodMenusPresenter;
import com.example.foodmanagement.presentaions.recycleviewadapters.MenusRecyclerAdapter;
import com.example.foodmanagement.presentaions.recycleviewadapters.OrderRecyclerAdapter;
import com.example.foodmanagement.presentaions.views.fragments.DrinkMenusFragment;
import com.example.foodmanagement.presentaions.views.fragments.FirstFragment;
import com.example.foodmanagement.presentaions.views.fragments.FoodMenusFragment;
import com.example.foodmanagement.presentaions.views.fragments.MyBillFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.foodmanagement.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenu extends AppCompatActivity {

    private static OrderRecyclerAdapter adapter;
    private static ArrayList<OrderListData> orderArrayList;
    private static RecyclerView recyclerView;

    private static BottomSheetBehavior bottomSheetBehavior;

    private static TextView tv_order_quantity;
    private static TextView tv_order_price;
    private static TextView tv_header_item;

    private static Button quantity_header;

    private static CardView cv_header;

    private TextView tv_table_orderlist;
    private TextView tv_people_orderlist;
    private TextView tv_header;

    private ViewPager2 viewPager2;
    private ImageView iv_arrow;
    private Button btn_sentOrder;
    private TabLayout tabLayout;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayoutBackgound;
    private ProgressBar progress_order;

    public static int peopleSum ;
    public static int tableNumber;

    private static boolean sentOrder;
    private static int count;

    private int quantity;
    private int price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        init();
        setTextHeader();
        setOrderAdapter();
        viewPagerSetting2();
        onSelectedTabMenus();
        disableSwipeDownBottomSheet();
        sentOrder();
        onRecyclerClick();
        swipeRecycler();
        onBottomSheetNoItem(String.valueOf(count));
    }

    public MainMenu(){}


    @Override
    protected void onPause() {
        OnPauseOrderSheetMainMenuActivity onPause =
                new OnPauseOrderSheetMainMenuActivity(orderArrayList,sentOrder);
        super.onPause();
    }

    public static boolean destroy;

    @Override
    protected void onResume(){
        if (!destroy) {
            if (OnPauseOrderSheetMainMenuActivity.getOrderListData() != null) {
                orderArrayList = OnPauseOrderSheetMainMenuActivity.getOrderListData();
                setOrderAdapter();
                onRecyclerClick();
                onBottomSheetNoItem(String.valueOf(count));
                Log.d("Life Cycle", "OnResume");
            }
        }else {
            cv_header.setCardBackgroundColor(Color.parseColor("#424242"));
            quantity_header.setVisibility(View.INVISIBLE);
            tv_header_item.setVisibility(View.INVISIBLE);
            destroy =false;
        }
        super.onResume();
    }


    private void init(){
        tv_table_orderlist = findViewById(R.id.textview_table_orderlist);
        tv_people_orderlist = findViewById(R.id.textview_people_orderlist);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewPager2);
        iv_arrow = findViewById(R.id.ic_baseline_keyboard_arrow_up_24);
        linearLayout = findViewById(R.id.sheet_layout);
        tv_header = findViewById(R.id.textview_header);
        quantity_header = findViewById(R.id.btn_quantity_header);
        cv_header = findViewById(R.id.cardview_header);
        tv_header_item = findViewById(R.id.textview_header_item);
        tv_order_quantity = findViewById(R.id.textView_order_quantity);
        tv_order_price = findViewById(R.id.textView_order_price);
        progress_order = findViewById(R.id.progressBar_order);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        recyclerView = findViewById(R.id.orderRecyclerVIew);
        btn_sentOrder = findViewById(R.id.button_sent_order);
        relativeLayoutBackgound = findViewById(R.id.relativeLayout_backgound);
        orderArrayList = new ArrayList<>();
    }

    @SuppressLint("SetTextI18n")
    private void setTextHeader(){
        if (getIntent().getExtras() != null) {
            peopleSum = getIntent().getExtras().getInt("peopleSum");
            tableNumber = getIntent().getExtras().getInt("tableNumber");
            tv_people_orderlist.setText(String.valueOf(peopleSum));
            tv_table_orderlist.setText("โต๊ะ " + String.valueOf(tableNumber));
        }{
            tv_people_orderlist.setText(String.valueOf(peopleSum));
            tv_table_orderlist.setText("โต๊ะ " + (tableNumber));
        }
    }

    private void setOrderAdapter(){
        adapter = new OrderRecyclerAdapter(this,orderArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void clearAdapter(){
        orderArrayList.clear();
        count = 0;
        sentOrder = false;
        adapter.notifyDataSetChanged();
    }

    SwipeRecyclerViewHelper swipeHelper;
    private void swipeRecycler(){
         swipeHelper = new SwipeRecyclerViewHelper(this,recyclerView,300) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<SwipeRecyclerViewHelper.MyButton> buffer) {
            buffer.add(new MyButton(MainMenu.this,
                                    "Delete",
                                    0,
                                    70,
                    Color.parseColor("#FF3C30"),
                    new MyButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Log.d("Click Delete","Click");
                            String currentFoodName = orderArrayList.get(pos).getOrder_foodName();
                            adapter.notifyItemRemoved(pos);
                            orderArrayList.remove(pos);
                            if (adapter.getItemCount() == 0){
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                            count--;
                            onBottomSheetNoItem(String.valueOf(count));
                        }
                    }));
            }
        };
    }

    ModifierActivity modifierActivity = new ModifierActivity();
    private void onRecyclerClick(){
        adapter.setItemClickListener(new OrderRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(MainMenu.this, ModifierActivity.class));
                modifierActivity.onSetOrderListData(orderArrayList.get(position), position);
                    Log.d("POSITION ORDEER", String.valueOf(position));
            }
        });
    }

    public void onAddOrder(String productName, String productPrice, String date , String displayName, String user_id) {
        Log.d("Count", String.valueOf(count));
        orderArrayList.add(count, new OrderListData(productName
                , productPrice
                , date
                , displayName
                ,String.valueOf(1)
                ,null
                ,user_id
                ,null));
        adapter.notifyDataSetChanged();
        count++;
        onBottomSheetNoItem(String.valueOf(count));
        findResultPrice();
        findResultQuantity();
    }

    public void onUpdateFoodCount(int position, String foodCount,String modifybox,String isTakeAway){
        orderArrayList.get(position).setOrder_plusCount(foodCount);
        orderArrayList.get(position).setOrder_modify(modifybox);
        orderArrayList.get(position).setOrder_takeAway(isTakeAway);
        adapter.notifyDataSetChanged();
        findResultPrice();
        findResultQuantity();
    }

    private void viewPagerSetting2(){
        viewPager2.setAdapter(new MenuPagerAdapter2(this));
        viewPager2.setUserInputEnabled(false);
    }

    private void onBottomSheetNoItem(String orderSum) {
        if (orderArrayList.isEmpty()) {
            cv_header.setCardBackgroundColor(Color.parseColor("#424242"));
            quantity_header.setVisibility(View.INVISIBLE);
            tv_header_item.setVisibility(View.INVISIBLE);
        } else {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                cv_header.setCardBackgroundColor(Color.parseColor("#FF9900"));
                quantity_header.setVisibility(View.VISIBLE);
                tv_header_item.setVisibility(View.VISIBLE);
                quantity_header.setText(orderSum);
                quantity_header.performClick();
                quantity_header.setPressed(true);
                quantity_header.invalidate();
                quantity_header.setPressed(false);
                quantity_header.invalidate();
                cv_header.performClick();
                cv_header.setPressed(true);
                cv_header.invalidate();
                cv_header.setPressed(false);
                cv_header.invalidate();
            }
        }
    }

    private void disableSwipeDownBottomSheet(){
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED){
                    MenusRecyclerAdapter.isClickable = false;
                    fadeOut(quantity_header);
                    fadeOut(tv_header_item);
                    tv_header.setVisibility(View.VISIBLE);
                    cv_header.setCardBackgroundColor(Color.parseColor("#FF9900"));
                }else {
                    MenusRecyclerAdapter.isClickable = true;
                    fadeIn(quantity_header);
                    fadeIn(tv_header_item);
                    tv_header.setVisibility(View.INVISIBLE);
                    if (orderArrayList.size() != 0) {
                        cv_header.setCardBackgroundColor(Color.parseColor("#FF9900"));
                    }else {
                        cv_header.setCardBackgroundColor(Color.parseColor("#424242"));
                        quantity_header.setVisibility(View.INVISIBLE);
                        tv_header_item.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset >= 0.30f){
                    if (adapter.getItemCount() == 0){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
                transitionBottomSheetBackgroundColor(slideOffset);
                animateBottomSheetArrows(slideOffset);
            }
        });
    }

    private void fadeOut(View view){
        view.animate().alpha(0);
    }

    private void fadeIn(View view){
        view.animate().alpha(1);
    }

    private void transitionBottomSheetBackgroundColor(float slideOffset) {
        int colorFrom = getResources().getColor(R.color.orange);
        int colorTo = getResources().getColor(R.color.white);
        relativeLayoutBackgound.setBackgroundColor(interpolateColor(slideOffset,
                colorFrom, colorTo));
    }

    @SuppressLint("RtlHardcoded")
    private void animateBottomSheetArrows(float slideOffset) {
        // Animate counter-clockwise
        iv_arrow.setRotation(slideOffset * -180);
        // Animate clockwise
        iv_arrow.setRotation(slideOffset * 180);

        iv_arrow.animate().translationX(slideOffset * -450);

    }

    private int interpolateColor(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;
        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;
        return ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));
    }

    private static int num_row;
    UserRepository repository;

    private void sentOrder() {
        btn_sentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentOrder = true;
                adapter.notifyDataSetChanged();
                progress_order.setVisibility(View.VISIBLE);
                btn_sentOrder.setVisibility(View.GONE);
                try {
                    repository = new MainServices().getRetrofit().create(UserRepository.class);
                    GetMyBillCountUseCase getMyBillCountUseCase = new GetMyBillCountUseCase(
                            repository
                            ,MainMenu.this);
                    getMyBillCountUseCase.execute(null);

                }catch (Exception e) {
                    Log.d("Catch Exception", e.getMessage());
                }
                final Handler handler = new Handler();
                handler.postDelayed(() -> {
                    try {
                        for (int i = num_row; i < orderArrayList.size() + num_row ; i++) {
                            repository = new MainServices().getRetrofit().create(UserRepository.class);
                            InsertOrderUseCase insertOrderUseCase = new InsertOrderUseCase(
                                    repository,MainMenu.this
                                    ,orderArrayList
                                    ,i-num_row
                                    ,i);
                        }
                    }catch (Exception e){
                        Log.d("Catch Exception InsertOrderUseCase",e.getMessage());
                    } finally {
                        MyBillFragment.table = String.valueOf(tableNumber);
                        MyBillFragment.name = FirstFragment.displayName;
                        progress_order.setVisibility(View.GONE);
                        btn_sentOrder.setVisibility(View.VISIBLE);
                        startActivity(new Intent(MainMenu.this,SentedOrderActivity.class));
                    }
                    btn_sentOrder.setVisibility(View.GONE);
                }, 1500);
            }
        });
    }

    public void onSetNumRow(List<MyBillNumRow> count){
        num_row = Integer.parseInt(count.get(0).getCount());
    }

    public static boolean getSentOrder(){
        return sentOrder;
    }

    private void findResultQuantity(){
        quantity = 0;
        if (orderArrayList != null){
            for (int i = 0; i < orderArrayList.size() ; i++) {
                int j = Integer.parseInt(orderArrayList.get(i).getOrder_plusCount());
                quantity += j;
            }
            tv_order_quantity.setText("Quantity : "+quantity);
        }
    }

    private void findResultPrice(){
        price = 0;
        if (orderArrayList != null){
            for (int i = 0; i < orderArrayList.size() ; i++) {
                int p = Integer.parseInt(orderArrayList.get(i).getOrder_price());
                int c = Integer.parseInt(orderArrayList.get(i).getOrder_plusCount());
                int result = p * c;
                price += result;
            }
            tv_order_price.setText("Price : "+ price+" Bath");
        }
    }

    private void onSelectedTabMenus(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPagerObj.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0){
                    FoodMenusFragment fragment = new FoodMenusFragment();

                }else {
                    DrinkMenusFragment fragment = new DrinkMenusFragment();
                }
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private FoodMenusPresenter presenter = new FoodMenusPresenter();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        FoodMenusFragment fragment = new FoodMenusFragment();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FoodMenusFragment.adapter.getFilter().filter(newText);
                fragment.showProgress();
                return false;
            }
        });
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                fragment.clearAdapter();
                fragment.showProgress();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.imageView_back_mainmenu)
    void onClickBack(){
        super.onBackPressed();
    }

    public void onError(){
        Log.d("onResponse Status","UnSuccessful");
    }

}