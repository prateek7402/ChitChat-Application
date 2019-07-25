package com.example.jaankaar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SetDataContent extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 0;
    Bitmap image;
    ImageView img;
    Time_Database time_database;
    ArrayList<Time_dsg> data=new ArrayList<>();
    EditText t, d;

    MainActivity refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data_content);
        t = (EditText) findViewById(R.id.title_tl);
        d = (EditText) findViewById(R.id.description_tl);
        img = (ImageView) findViewById(R.id.iamge);
        time_database = new Time_Database(this);
    }

    public void ADDIMAGE(View v1) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    try {
                        image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img.setImageURI(selectedImage);
                    break;
            }
    }

    public void InsertData(View v2) throws IOException {
        String title = t.getText().toString();
        String description = d.getText().toString();
        boolean result = time_database.insertData(title,image,description);
        if(result)
        {
            Toast.makeText(getApplicationContext(), "The Data is been Uploaded", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            finish();
        }else
        {
            Toast.makeText(getApplicationContext(), "The Data is not been inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void BacktoMain(View v3)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
