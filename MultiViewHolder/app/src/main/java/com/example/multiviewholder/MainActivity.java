package com.example.multiviewholder;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (RecyclerView) findViewById(R.id.rc1);

        ArrayList<Data> list = new ArrayList<Data>();
        list.add(new Data(Data.INPUT_MODE, "", 0));
        list.add(new Data(Data.DISPLAY_MODE, "Prateek Suman", R.drawable.p2));
        list.add(new Data(Data.IMAGE_MODE, "Welcome to the snow Valley", R.drawable.wal4));
        list.add(new Data(Data.MUSIC_MODE, "", R.raw.ye_baate));



        Adapter_multi adapter_multi = new Adapter_multi(list,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        rc.setLayoutManager(linearLayoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.setAdapter(adapter_multi);

    }

    public void Animation(View v1)
    {
         Intent i= new Intent(getApplicationContext(),Animation_new.class);
         startActivity(i);
    }

    public void AnimSet(View v2)
    {
        Intent i= new Intent(getApplicationContext(),Animatoin_Set.class);
        startActivity(i);
    }

}
