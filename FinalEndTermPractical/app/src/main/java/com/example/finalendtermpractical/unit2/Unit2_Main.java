package com.example.finalendtermpractical.unit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.finalendtermpractical.R;

public class Unit2_Main extends AppCompatActivity {
    ImageView img1, img2;
    Button b1, b2, b3, b4;
    Animation an1, an2;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2__main);
        img1 = findViewById(R.id.img);
        img2 = findViewById(R.id.tran);
        b1 = findViewById(R.id.rot);
        b2 = findViewById(R.id.mov);
        b3 = findViewById(R.id.transition);
        b4 = findViewById(R.id.multi);

        an1 = AnimationUtils.loadAnimation(this, R.anim.animation_one);
        an1.setDuration(8000);

        an2 = AnimationUtils.loadAnimation(this, R.anim.animation_two);
        an2.setDuration(8000);

        img2.setBackgroundResource(R.drawable.animation_list);
        animationDrawable = (AnimationDrawable) img2.getBackground();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.startAnimation(an1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.startAnimation(an2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 3.0f, 1f, 3.0f, img2.getWidth() / 2.0f, img2.getHeight() / 2.0f);
                scaleAnimation.setDuration(8000);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                float angle = 360f * 12;
                RotateAnimation animation = new RotateAnimation(0.0f, angle, img2.getWidth() / 2.0f, img2.getHeight() / 2.0f);
                animation.setDuration(8000);
                animation.setInterpolator(getApplicationContext(), android.R.interpolator.accelerate_decelerate);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(animation);
                img2.startAnimation(animationSet);
            }
        });
    }

    public void CanV(View v1) {
        Intent i = new Intent(getApplicationContext(), Canvas.class);
        startActivity(i);
    }
}
