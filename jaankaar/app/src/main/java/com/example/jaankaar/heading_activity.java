package com.example.jaankaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class heading_activity extends AppCompatActivity {
    login_state_checker login_state_checker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login_state_checker = new login_state_checker(this);
        boolean stat = login_state_checker.state1();
        if (stat) {
            Intent i = new Intent(getApplicationContext(),state_checker.class);
            startActivity(i);
            finish();
        }
        if(!stat){
            Intent i = new Intent(getApplicationContext(),Personal_Info.class);
            startActivity(i);
            finish();
        }
    }
}
