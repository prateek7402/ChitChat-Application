package com.example.jaankaar;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class login_state_checker {

    private static final String State = "State" ;
    private static final String Name = "Name";
    private static final String Email = "Email";
    private static final String Number = "Number";
    private static final String Image = "Image";
    SharedPreferences preff;

    public login_state_checker(Context context) {
        preff = context.getSharedPreferences("MyPref",MODE_PRIVATE);
    }



    void dataset(String user_phonenumber, String user_name, String user_emailid,String image)
{
    SharedPreferences.Editor editor = preff.edit();
    editor.putBoolean(State,true);
    editor.putString(Name,user_name);
    editor.putString(Number,user_phonenumber);
    editor.putString(Email,user_emailid);
    editor.putString(Image,image);
    editor.apply();
}

String putname()
{
return preff.getString(Name,null);
}

String putnumber()
{
    return preff.getString(Number,null);
}

String putemail()
{
    return preff.getString(Email,null);
}

String putimage()
{
    return preff.getString(Image,null);
}

boolean state1()
{
    return preff.getBoolean(State,false);
}

public void Cl()
{
    SharedPreferences.Editor editor = preff.edit();
    editor.clear();
    editor.commit();
}
}
