package com.example.jaankaar;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Note_Adder extends AppCompatActivity {
    TextView t1, t2;
    Button b1;
    Uri uri;
    String path;
    Database_for_notekeeper database_for_notekeeper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__adder);
        t1 = (TextView) findViewById(R.id.ntitle);
        t2 = (TextView) findViewById(R.id.nnote);
        b1 = (Button) findViewById(R.id.n_save);
        database_for_notekeeper = new Database_for_notekeeper(Note_Adder.this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = t1.getText().toString();
                String note = t2.getText().toString();
                if (title.equals("") || note.equals("")) {
                    Toast.makeText(Note_Adder.this, "Enter some value", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AppStorage";
                        File dir = new File(file_path);
                        if (!dir.exists())
                            dir.mkdirs();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        String fileName = "PNG_" + timeStamp + "_";
                        File file = new File(dir, fileName + ".txt");
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos.write(note.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Note_Adder.this, "Data written", Toast.LENGTH_SHORT).show();
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        path = file_path + "/" + fileName + ".txt";
                        try {
                            database_for_notekeeper.insertData(title, String.valueOf(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(getApplicationContext(),Note_Keeper.class);
                        startActivity(i);
                        finish();

                    }
                }
            }
        });
    }
}
