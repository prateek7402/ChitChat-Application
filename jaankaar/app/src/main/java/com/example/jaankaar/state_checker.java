package com.example.jaankaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class state_checker extends AppCompatActivity {
shared_pref_pass she;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        she = new shared_pref_pass(this);
        boolean st = she.state();
        if(!st)
        {
            Intent i = new Intent(this,PIN_entry.class);
            startActivity(i);
            finish();
        }
        if(st)
        {
            Intent i = new Intent(this,PIN.class);
            startActivity(i);
            finish();
        }
    }
}
