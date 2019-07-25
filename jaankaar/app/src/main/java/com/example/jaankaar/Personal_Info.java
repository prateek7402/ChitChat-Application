package com.example.jaankaar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;
//import static com.example.jaankaar.Personal_Info.RequestPermissionCode;
import static com.example.jaankaar.splash.RequestPermissionCode;

public class Personal_Info extends AppCompatActivity {
EditText t1,t2,t3;
Button save;
Bitmap image;
private static final int GALLERY_REQUEST_CODE = 0;
login_state_checker login_state_checker;
Uri selectedImage = null;
ProgressBar progressBar;
//String imgadd;
de.hdodenhof.circleimageview.CircleImageView img;
LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__info);
        t1 = (EditText)findViewById(R.id.tename);
        t2 = (EditText)findViewById(R.id.tephone);
        t3 = (EditText)findViewById(R.id.temail);
        img = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_image);
        login_state_checker = new login_state_checker(this);
        save = (Button)findViewById(R.id.b_login);
        l = (LinearLayout)findViewById(R.id.ll1);
      //  progressBar = (ProgressBar)findViewById(R.id.pb1);
        //checkForPermission();
        if(CheckingPermissionIsEnabledOrNot())
        {
            Toast.makeText(Personal_Info.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
        }

        // If, If permission is not enabled then else condition will execute.
        else {

            //Calling method to enable permission.
            RequestMultiplePermission();

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // imgadd = selectedImage.toString();
                if(t1.getText().toString().equals("")||t2.getText().toString().equals("")||t3.getText().toString().equals("")||selectedImage==null)
                {
                    Snackbar n =  Snackbar.make(l,"Please give all the details",Snackbar.LENGTH_LONG);
                    n.show();
                    return;
                }
                else {

                    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AppStorage";
                    File dir = new File(file_path);
                    if(!dir.exists())
                        dir.mkdirs();

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String fileName = "PNG_"+timeStamp+"_";
                    File file = new File(dir, fileName + ".png");
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    image.compress(Bitmap.CompressFormat.PNG, 85, fileOutputStream);
                    try {
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String imagePath = file_path + "/" + fileName + ".png";
                    login_state_checker.dataset(t2.getText().toString(), t1.getText().toString(), t3.getText().toString(),imagePath);
                    Snackbar n =  Snackbar.make(l,"Please give all the details",Snackbar.LENGTH_LONG);
                    n.show();
                    Intent i = new Intent(getApplicationContext(),state_checker.class);
                    startActivity(i);
                    finish();
                    save.setVisibility(View.INVISIBLE);
                   // progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    
    
    public void ADD(View v1) {
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

    
   /* private void checkForPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                //flag = true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 300);
            }
        }
    }

    
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "File access granted", Toast.LENGTH_SHORT).show();
                //flag=true;
            } else {
                Toast.makeText(this, "File access not granted", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "File access granted", Toast.LENGTH_SHORT).show();
                //flag=true;
            } else {
                Toast.makeText(this, "File access not granted", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 300) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "File access granted", Toast.LENGTH_SHORT).show();
                //flag=true;
            } else {
                Toast.makeText(this, "File access not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
   //Permission function starts from here
   private void RequestMultiplePermission() {

       // Creating String Array with Permissions.
       ActivityCompat.requestPermissions(Personal_Info.this, new String[]
               {
                       CAMERA,
                       RECORD_AUDIO,
                       SEND_SMS,
                       CALL_PHONE,
                       WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);
   }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordAudioPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean SendSMSPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean CallPhone = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean ExternalStorage = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    if (CameraPermission && RecordAudioPermission && SendSMSPermission && CallPhone && ExternalStorage) {
                        Toast.makeText(Personal_Info.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Personal_Info.this,"",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    // Checking permission is enabled or not using function starts from here.
    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ForthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
}
