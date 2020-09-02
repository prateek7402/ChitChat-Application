package com.example.theparkar.Login_State_Checker;

import android.content.Context;
import android.content.SharedPreferences;

public class Shrared_prefference_login {
    SharedPreferences sharedPreferences;
    boolean status = false;
    private static final String State = "State" ;

    public Shrared_prefference_login(Context context) {
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
