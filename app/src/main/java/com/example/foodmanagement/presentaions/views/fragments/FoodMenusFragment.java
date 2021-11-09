package com.example.foodmanagement.presentaions.views.fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.FoodMenusInterface;
import com.example.foodmanagement.models.Product;
import com.example.foodmanagement.models.OnPauseMainMenuActivity;
import com.example.foodmanagement.presentaions.presenters.FoodMenusPresenter;
import com.example.foodmanagement.presentaions.recycleviewadapters.MenusRecyclerAdapter;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

import static com.example.foodmanagement.R.id.foodMenusFragment;
import static com.example.foodmanagement.R.id.viewPager2;


public class FoodMenusFragment extends Fragment implements FoodMenusInterface.view {

    private static boolean[] onTabCategorySelected;

    private static List<Product> productsMenus;
    public static MenusRecyclerAdapter adapter;
    private static RecyclerView recyclerView;

    private View view;
    private static int tabPosition ;

    private FoodMenusPresenter presenter;
    private TabLayout tabLayoutFood;
    private MainMenu mainMenu;
    private static ProgressBar progress_menus;

    public FoodMenusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_menus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        this.view = view;
        init(view);
        recyclerViewSetupLayout();
        onSelectCategory();
        onClickRecyclerViewRiceCategory();
        onTabCategorySelected[0] = true;
        //Log.d("onTabCategorySelected", Arrays.toString(onTabCategorySelected));
    }

    @Override
    public void onPause(){
        Log.d("OnPause","OnPause");
        super.onPause();
    }

    @Override
    public void onResume(){
        Log.d("OnPause","OnResume");
        super.onResume();
    }

    private void init(View view){
        tabLayoutFood = view.findViewById(R.id.tab_layout_food);
        recyclerView = view.findViewById(R.id.recyclerview_menus);
        progress_menus = view.findViewById(R.id.progressBar_menus_food);
        productsMenus = new ArrayList<>();
        onTabCategorySelected = new boolean[tabLayoutFood.getTabCount()];
        mainMenu = new MainMenu();
        shootEndpoint("0");
        setAdapter();
    }

    public void shootEndpoint(String category_id){
        showProgress();
        presenter = new FoodMenusPresenter(this,category_id);
    }

    public void showProgress(){
        if (productsMenus.isEmpty()){
            progress_menus.setVisibility(View.VISIBLE);
        }else {
            progress_menus.setVisibility(View.INVISIBLE);
        }
    }

    private void recyclerViewSetupLayout(){
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(glm);
        recyclerView.setItemViewCacheSize(20);
    }

   private void onSelectCategory(){
        tabLayoutFood.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                productsMenus.clear();
                adapter.notifyDataSetChanged();
                onTabCategorySelected[tab.getPosition()] = true;
                shootEndpoint(String.valueOf(tabPosition));
                Log.d("TabTest",Arrays.toString(onTabCategorySelected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                onTabCategorySelected[tab.getPosition()] = false;
                Log.d("TabTest",Arrays.toString(onTabCategorySelected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
   }

    @Override
   public void onUpdateAdapter(){
       recyclerView.setAdapter(adapter);
   }

    private void setAdapter(){
        adapter = new MenusRecyclerAdapter(productsMenus ,presenter);
        recyclerView.setAdapter(adapter);
        Log.d("Set Adapter","Set new Adapter");
    }

    public void clearAdapter(){
        productsMenus.clear();
        presenter = new FoodMenusPresenter(this,String.valueOf(tabPosition));
    }

    public void clearAdapter2(){
        productsMenus.clear();
        adapter.notifyDataSetChanged();
    }

    private void onClickRecyclerViewRiceCategory(){
        adapter.setItemClickListener(position -> {
            mainMenu.onAddOrder(
                    productsMenus.get(position).getMenu_name()
                    ,productsMenus.get(position).getMenu_price()
                    ,getDate()
                    ,FirstFragment.displayName
                    ,FirstFragment.user_id);
        });
    }

    private String getDate(){
        Date dNow = new Date( );
        @SuppressLint("SimpleDateFormat") SimpleDateFormat ft =
                new SimpleDateFormat ("dd.MM.yyyy");
        return ft.format(dNow);
    }

    @Override
    public void onSetProduct(List<Product> product) {
        productsMenus = product;
        setAdapter();
        showProgress();
        Log.d("product",product.toString());
    }

}