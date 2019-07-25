package com.example.jaankaar;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class shared_pref_pass {
    /*public shared_pref_pass(String password,String answer) {
        this.password = password;
        this.answer = answer;
    }*/

    String PIN = "PIN";
    String Ans = "Ans";
    String password = "password";
    String answer = "answer";
    String state = "state";
    String PrefName = "MyPref";
    SharedPreferences pref;

    public shared_pref_pass(Context context) {
        pref = context.getSharedPreferences("MyPref",MODE_PRIVATE);
    }

    public void setdata(String pass, String ans)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(state,true);
        editor.putString(PIN,pass);
        editor.putString(Ans,ans);
        editor.apply();
    }

    public String putdata()
    {
        return String.valueOf(pref.getString(PIN,null));
    }

    public String verify()
    {
        return String.valueOf(pref.getString(Ans,null));
    }

    public boolean state()
    {
        return pref.getBoolean(state,false);
    }

    public void remov()
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
