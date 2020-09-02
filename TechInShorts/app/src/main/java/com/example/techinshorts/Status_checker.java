package com.example.techinshorts;

import android.content.Context;
import android.content.SharedPreferences;

public class Status_checker {
    SharedPreferences sharedPreferences;
    boolean status = false;
    private static final String State = "State" ;

    public Status_checker(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
    }

    public void setStatus()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(State,true);
        editor.apply();
    }
    public boolean getState()
    {
        return sharedPreferences.getBoolean(State,false);
    }

    public void logout()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
