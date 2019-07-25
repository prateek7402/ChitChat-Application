package com.example.jaankaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Note_Viewer extends AppCompatActivity {
TextView t1,t2;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__viewer);
        t1 = (TextView)findViewById(R.id.vtitle);
        t2 = (TextView)findViewById(R.id.vnote);
        b1 = (Button)findViewById(R.id.v_save);
        Intent intent = getIntent();
        final DSGN note = (DSGN) intent.getSerializableExtra("contact");
        t1.setText(note.getTitle());
        File myFile = new File(note.getAdd());
        FileInputStream foi = null;
        try {
            foi = new FileInputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        String s = "";
        try {
            while ((i = foi.read()) != -1) {
                s = s + (char) i;
            }
        }catch (IOException E)
        {
            E.printStackTrace();
        }
        try {
            foi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t2.setText(s);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Note_Keeper.class);
                startActivity(i);
                finish();
            }
        });
    }
}
