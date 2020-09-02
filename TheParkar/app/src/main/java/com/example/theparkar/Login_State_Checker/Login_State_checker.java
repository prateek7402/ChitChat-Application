package com.example.theparkar.Login_State_Checker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.theparkar.Deatils_Tab;
import com.example.theparkar.Main_Home;

public class Login_State_checker extends AppCompatActivity {
    Shrared_prefference_login shrared_prefference_login;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shrared_prefference_login = new Shrared_prefference_login(getApplicationContext());
        flag = shrared_prefference_login.getState();
        if(flag == true)
        {
            Intent i = new Intent(getApplicationContext(), Main_Home.class);
            startActivity(i);
            finish();
        }

        else
        {
            Intent i = new Intent(getApplicationContext(), Deatils_Tab.class);
            startActivity(i);
            finish();
        }
    }
}
