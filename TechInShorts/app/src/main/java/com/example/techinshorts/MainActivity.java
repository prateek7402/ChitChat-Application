package com.example.techinshorts;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    LinearLayout ll;
   // Animation ani;
   // AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   ani = AnimationUtils.loadAnimation(this,R.anim.animation_1);
        ani.setDuration(2000);
        ll = (LinearLayout)findViewById(R.id.back1);
        ll.setBackgroundResource(R.drawable.list_view);
        animationDrawable = (AnimationDrawable)ll.getBackground();
        animationDrawable.start();
        ll.startAnimation(ani);*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(), Login_state_checker.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
