package com.example.multiviewholder;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Animation_new extends AppCompatActivity {
    Button b1,b2,b3;
    ImageView img,img2;
    Animation ani,anil;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_new);

        img = (ImageView)findViewById(R.id.img);
        b1 = (Button)findViewById(R.id.rotate);
        b2 = (Button)findViewById(R.id.scale);
        b3 = (Button)findViewById(R.id.change);
        img2 = (ImageView)findViewById(R.id.img2);

        ani = AnimationUtils.loadAnimation(this,R.anim.animation_one);
        ani.setDuration(8000);

        anil = AnimationUtils.loadAnimation(this,R.anim.animation_two);
        anil.setDuration(8000);

        img2.setBackgroundResource(R.drawable.my_list);
        animationDrawable = (AnimationDrawable)img2.getBackground();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.startAnimation(ani);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.startAnimation(anil);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });
    }
}
