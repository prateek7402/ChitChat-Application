package com.example.techinshorts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_state_checker extends AppCompatActivity {
    Status_checker status_checker;
    Boolean flag = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status_checker = new Status_checker(getApplicationContext());
        flag = status_checker.getState();
        if(flag == true)
        {
            Intent i = new Intent(getApplicationContext(),News_feed.class);
            startActivity(i);
            finish();
        }

        else
        {
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
