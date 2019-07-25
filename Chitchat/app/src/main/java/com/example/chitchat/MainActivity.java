package com.example.chitchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Man(View v1)
    {
        Intent i = new Intent(getApplicationContext(),Man.class);
        startActivity(i);
    }

    public void Women (View v2)
    {
        Intent i = new Intent(getApplicationContext(),Women.class);
        startActivity(i);
    }
}
