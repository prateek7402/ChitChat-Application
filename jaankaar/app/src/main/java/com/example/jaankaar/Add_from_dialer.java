package com.example.jaankaar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Add_from_dialer extends AppCompatActivity {
    EditText t1, t2, t3;
    Button save;
    Bitmap image;
    DataBaseHelper db;
    private static final int GALLERY_REQUEST_CODE = 0;
    login_state_checker login_state_checker;
    Uri selectedImage = null;
    //String imgadd;
    de.hdodenhof.circleimageview.CircleImageView img;
    private LinearLayout l;
    ArrayList<DataSetterGetter> data=new ArrayList<>();
    MainActivity refresh;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_from_dialer);
        t1 = (EditText) findViewById(R.id.d_name);
        t2 = (EditText) findViewById(R.id.d_phone);
        t3 = (EditText) findViewById(R.id.d_email);
        img = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.d_image);
     //   progressBar = (ProgressBar)findViewById(R.id.pb3);
        login_state_checker = new login_state_checker(this);
        save = (Button) findViewById(R.id.d_con);
        l = (LinearLayout) findViewById(R.id.ll3);
        db = new DataBaseHelper(this);

        Intent intent = getIntent();
        String number = intent.getExtras().getString("Number");
        t2.setText(number);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                // Launching the Intent
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,phone,email;
                name = t1.getText().toString();
                phone = t2.getText().toString();
                email = t3.getText().toString();
                if(name.equals("")||email.equals("")||phone.equals("")||selectedImage==null)
                {
                    Snackbar n =  Snackbar.make(l,"Please give all the details",Snackbar.LENGTH_LONG);
                    n.show();
                }
                else
                {
                    boolean result = false;
                    try {
                        result = db.insertData(name,phone,image,email);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(result)
                    {
                        Toast.makeText(getApplicationContext(), "Please wait till file is been loaded", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "The Data is not been inserted", Toast.LENGTH_LONG).show();
                    }
                }
               // progressBar.setVisibility(View.VISIBLE);
                save.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    selectedImage = data.getData();
                    try {
                        image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img.setImageURI(selectedImage);
                    break;
            }
    }
}
