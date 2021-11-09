package com.example.foodmanagement.presentaions.pageradapter;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodmanagement.presentaions.views.fragments.DrinkMenusFragment;
import com.example.foodmanagement.presentaions.views.fragments.FoodMenusFragment;

public class MenuPagerAdapter2 extends FragmentStateAdapter {

    public MenuPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FoodMenusFragment();
            case 1:
                return new DrinkMenusFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
