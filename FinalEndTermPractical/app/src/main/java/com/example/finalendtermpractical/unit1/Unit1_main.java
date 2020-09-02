package com.example.finalendtermpractical.unit1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.finalendtermpractical.R;

import java.util.ArrayList;

public class Unit1_main extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit1_main);
        recyclerView = findViewById(R.id.rc1);
        ArrayList<DataClass> dat = new ArrayList<>();
        dat.add(new DataClass(DataClass.INPUT_MODE,"",0));
        dat.add(new DataClass(DataClass.DISPLAY_MODE,"",0));
        dat.add(new DataClass(DataClass.REGISTER_MODE,"",0));
        dat.add(new DataClass(DataClass.SIGNUP_MODE,"",0));

        Adapter ad = new Adapter(dat,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(ad);
    }
}
