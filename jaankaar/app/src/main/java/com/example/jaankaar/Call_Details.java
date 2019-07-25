package com.example.jaankaar;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Call_Details extends AppCompatActivity {
    TextView Name, Number, Email;
    ImageButton call, c_email;
    de.hdodenhof.circleimageview.CircleImageView img;
    ImageButton messsage;
    String name_c, number_c, email_c, Img;
    Uri Image;
    AlertDialog alertDialog1;
    AlertDialog alertDialog;
    login_state_checker login_state_checker;
    Call_Log_Database call_log_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__details);

        Intent intent = getIntent();
        final DataSetterGetter contact = (DataSetterGetter) intent.getSerializableExtra("contact");

        img = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.c_image);
        Name = (TextView) findViewById(R.id.c_name);
        Number = (TextView) findViewById(R.id.c_phone);
        Email = (TextView) findViewById(R.id.c_email);
        call = (ImageButton) findViewById(R.id.c_call);
        messsage = (ImageButton) findViewById(R.id.c_message);
        c_email = (ImageButton) findViewById(R.id.send_email);
        login_state_checker = new login_state_checker(getApplicationContext());
        call_log_database = new Call_Log_Database(Call_Details.this);

        Name.setText(contact.getname());
        Number.setText(contact.getnumber());
        Email.setText(contact.getEmail());
        String inage = contact.getimage();
        Uri uri = Uri.parse((inage));
        img.setImageURI(uri);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = false;
                try {
                    isInserted = call_log_database.insertData(contact.getname(), contact.getnumber(), contact.getimage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (isInserted == true) {
                  //  Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "Dta not inserted", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + contact.getnumber()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    // return;
                }
                startActivity(i);
            }
        });
        messsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Call_Details.this);
                View v1 = getLayoutInflater().inflate(R.layout.message, null);
                final EditText mes = (EditText) v1.findViewById(R.id.message1);
                Button sen = (Button) v1.findViewById(R.id.send);
                Button bk = (Button) v1.findViewById(R.id.back2);
                builder.setView(v1);
                alertDialog = builder.create();
                alertDialog.setTitle("MESSAGE");
                alertDialog.show();
                sen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //String number = "12346556";  // The number on which you want to send SMS
                        final String mes1 = mes.getText().toString();
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(contact.getnumber(), null, mes1 + "\n\n\n\n" + "This Message is been sent by" + "\n" + login_state_checker.putname() + "\n" + "Sent by Jaankaar App", null, null);
                    }
                });
                bk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                alertDialog.setCancelable(false);
            }
        });
        c_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Call_Details.this);
                View v1 = getLayoutInflater().inflate(R.layout.send_email, null);
                final EditText t1, t2, t3;
                Button b1, b2;
                t1 = (EditText) v1.findViewById(R.id.recep);
                t2 = (EditText) v1.findViewById(R.id.subject);
                t3 = (EditText) v1.findViewById(R.id.mess_mail);
                b1 = (Button) v1.findViewById(R.id.mail_sent);
                b2 = (Button) v1.findViewById(R.id.back1);
                t1.setText(contact.getEmail());
                builder.setView(v1);
                alertDialog1 = builder.create();
                alertDialog1.setTitle("MESSAGE");
                alertDialog1.show();
                alertDialog1.setCancelable(false);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog1.cancel();
                    }
                });
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);

                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, t1.getText().toString());
                        emailIntent.putExtra(Intent.EXTRA_CC, "");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, t2.getText().toString());
                        emailIntent.putExtra(Intent.EXTRA_TEXT, t3.getText().toString());

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Snackbar.make(v, "The mail is been sent", Snackbar.LENGTH_LONG);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void Back(View v1) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

}
