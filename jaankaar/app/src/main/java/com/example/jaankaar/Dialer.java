package com.example.jaankaar;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class Dialer extends Fragment {

    TextView t;
    String num = "";
    LinearLayout l;
    private AlertDialog alertDialog;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    ImageView back;
    ImageView call;
    ImageView message;
    login_state_checker login_state_checker;
    private Call_Log_Database call_log_database;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_dialer, container, false);
        t = (TextView) view.findViewById(R.id.dis_display);
        l = (LinearLayout) view.findViewById(R.id.ll1);
        one = (Button) view.findViewById(R.id.b1);
        two = (Button) view.findViewById(R.id.b2);
        three = (Button) view.findViewById(R.id.b3);
        four = (Button) view.findViewById(R.id.b4);
        five = (Button) view.findViewById(R.id.b5);
        six = (Button) view.findViewById(R.id.b6);
        seven = (Button) view.findViewById(R.id.b7);
        eight = (Button) view.findViewById(R.id.b8);
        nine = (Button) view.findViewById(R.id.b9);
        zero = (Button) view.findViewById(R.id.b0);
        back = (ImageView) view.findViewById(R.id.bs);
        call = (ImageView) view.findViewById(R.id.call);
        message = (ImageView) view.findViewById(R.id.mess);
        login_state_checker = new login_state_checker(view.getContext());
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.save);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getContext(),Add_from_dialer.class);
               i.putExtra("Number",num);
               startActivity(i);
            }
        });
        call_log_database = new Call_Log_Database(getContext());
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "1";
                t.setText(num);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "2";
                t.setText(num);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "3";
                t.setText(num);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "4";
                t.setText(num);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "5";
                t.setText(num);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "6";
                t.setText(num);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "7";
                t.setText(num);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "8";
                t.setText(num);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "9";
                t.setText(num);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + "0";
                t.setText(num);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.equals("")) {
                    Toast.makeText(getContext(), "Please enter some data", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    num = num.replace(num.substring(num.length() - 1), "");
                    t.setText(num);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.length() == 0) {
                    Toast.makeText(getContext(), "please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    boolean isInserted = false;
                    try {
                       Uri imageURI= Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.drawable.ic_person_black_24dp1);
                        isInserted = call_log_database.insertData("", num, String.valueOf(imageURI));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (isInserted == true) {
                        //  Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getApplicationContext(), "Dta not inserted", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + num));
                    if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v1 = getLayoutInflater().inflate(R.layout.message, null);
                final EditText mes = (EditText) v1.findViewById(R.id.message1);
                Button sen = (Button) v1.findViewById(R.id.send);

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
                        smsManager.sendTextMessage(num, null, mes1 + "\n\n\n\n" + "This Message is been sent by" + "\n" + login_state_checker.putname() + "\n" + "Sent by Jaankaar App", null, null);
                    }
                });
                alertDialog.setCancelable(true);
            }
        });
        return view;
    }
}





