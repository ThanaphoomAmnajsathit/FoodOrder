package com.example.foodmanagement.models;

public class OnPauseMainMenuActivity {

    private static int[] plusRice ;
    private static int[] plusNoodle ;
    private static boolean[] isRiceSelected ;
    private static boolean[] isNoodleSelected ;

    public OnPauseMainMenuActivity(){}

    public OnPauseMainMenuActivity(int[] plusRice,int[] plusNoodle,
                                   boolean[] isRiceSelected,boolean[] isNoodleSelected){
        setPlusRice(plusRice);
        setPlusNoodle(plusNoodle);
        setIsRiceSelected(isRiceSelected);
        setIsNoodleSelected(isNoodleSelected);
    }

    public static int[] getPlusRice() {
        return plusRice;
    }

    public static void setPlusRice(int[] plusRice) {
        OnPauseMainMenuActivity.plusRice = plusRice;
    }

    public static int[] getPlusNoodle() {
        return plusNoodle;
    }

    public static void setPlusNoodle(int[] plusNoodle) {
        OnPauseMainMenuActivity.plusNoodle = plusNoodle;
    }

    public static boolean[] getIsRiceSelected() {
        return isRiceSelected;
    }

    public static void setIsRiceSelected(boolean[] isRiceSelected) {
        OnPauseMainMenuActivity.isRiceSelected = isRiceSelected;
    }

    public static boolean[] getIsNoodleSelected() {
        return isNoodleSelected;
    }

    public static void setIsNoodleSelected(boolean[] isNoodleSelected) {
        OnPauseMainMenuActivity.isNoodleSelected = isNoodleSelected;
    }
}
