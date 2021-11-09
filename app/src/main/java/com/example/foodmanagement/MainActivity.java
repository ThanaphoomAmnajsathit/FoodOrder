package com.example.foodmanagement;

import android.os.Bundle;

import com.example.foodmanagement.presentaions.views.activities.MainMenu;
import com.example.foodmanagement.presentaions.views.fragments.FirstFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static int[] plusRice ;
    private static int[] plusNoodle ;
    private static boolean[] isRiceSelected ;
    private static boolean[] isNoodleSelected ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

        public MainActivity(){}

        public MainActivity(int[] plusRice,int[]plusNoodle,boolean[] isRiceSelected,boolean[] isNoodleSelected){
         setPlusRice(plusRice);
         setPlusNoodle(plusNoodle);
         setIsRiceSelected(isRiceSelected);
         setIsNoodleSelected(isNoodleSelected);
        }

    public static void setPlusRice(int[] plusRice) {
        MainActivity.plusRice = plusRice;
    }

    public static void setPlusNoodle(int[] plusNoodle) {
        MainActivity.plusNoodle = plusNoodle;
    }

    public static void setIsRiceSelected(boolean[] isRiceSelected) {
        MainActivity.isRiceSelected = isRiceSelected;
    }

    public static void setIsNoodleSelected(boolean[] isNoodleSelected) {
        MainActivity.isNoodleSelected = isNoodleSelected;
    }

    public int[] getPlusRice() {
        return plusRice;
    }

    public int[] getPlusNoodle() {
        return plusNoodle;
    }

    public boolean[] getIsRiceSelected() {
        return isRiceSelected;
    }

    public boolean[] getIsNoodleSelected() {
        return isNoodleSelected;
    }
}
