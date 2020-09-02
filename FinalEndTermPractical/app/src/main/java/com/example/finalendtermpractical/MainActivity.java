package com.example.finalendtermpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalendtermpractical.unit1.Unit1_main;
import com.example.finalendtermpractical.unit2.Unit2_Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Unit1(View v1)
    {
        Intent i = new Intent(getApplicationContext(), Unit1_main.class);
        startActivity(i);
    }

    public void Unit2(View v2)
    {
        Intent i = new Intent(getApplicationContext(), Unit2_Main.class);
        startActivity(i);
    }

    public void Unit3(View v3)
    {

    }

    public void Unit4(View v4)
    {

    }

    public void Unit5(View v5)
    {

    }

    public void Unit6(View v6)
    {

    }
}
