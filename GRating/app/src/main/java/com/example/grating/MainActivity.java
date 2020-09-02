package com.example.grating;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
LinearLayout ll;
    AnimationDrawable animationDrawable;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout)findViewById(R.id.background);

        ll.setBackgroundResource(R.drawable.list_view);
        animationDrawable = (AnimationDrawable)ll.getBackground();

        animationDrawable.start();
    }
    public void Get(View v1)
    {
        Intent i = new Intent(getApplicationContext(),SplahActivity.class);
        startActivity(i);
    }
    public void Network(View v2)
    {
        Intent i = new Intent(getApplicationContext(),NetworkAdapter.class);
        startActivity(i);
    }
}
