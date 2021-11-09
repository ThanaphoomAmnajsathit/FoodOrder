package com.example.foodmanagement.presentaions.pageradapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodmanagement.presentaions.views.fragments.DrinkMenusFragment;
import com.example.foodmanagement.presentaions.views.fragments.FoodMenusFragment;

public class MenuPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MenuPagerAdapter(FragmentManager fragmentManager , int numOfTabs){
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FoodMenusFragment();
            case 1:
                return new DrinkMenusFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
