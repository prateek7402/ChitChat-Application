package com.example.jaankaar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity /*implements Contact_List.Transfer */ {

    /*
        String name_c, number_c, email_c, Img;
        Call_Details call_details;
        DrawerLayout d;
        NavigationView nv;
    */
    shared_pref_pass shared_pref_pass;
    LinearLayout l;
    ImageView imageView1, imageView2;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Dialer:
                    fragment = new Dialer();
                    loadFragment(fragment);
                    return true;
                case R.id.main_contact:
                    fragment = new Contact_List();
                    loadFragment(fragment);
                    return true;
                case R.id.call_log:
                    fragment = new Call_Log();
                    loadFragment(fragment);
                    return true;
                case R.id.Note_keeper:
                    fragment = new Note_Keeper();
                    loadFragment(fragment);
                    return true;
                case R.id.Time_Line:
                    fragment = new Time_Line();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private Fragment fragment;
    private login_state_checker login_state_checker;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared_pref_pass = new shared_pref_pass(getApplicationContext());
        loadFragment(new Dialer());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        imageView1 = (ImageView) findViewById(R.id.show);
        l = (LinearLayout) findViewById(R.id.container);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v2 = getLayoutInflater().inflate(R.layout.activity_my__account, null);
                TextView t1 = (TextView) v2.findViewById(R.id.pi_name);
                TextView t2 = (TextView) v2.findViewById(R.id.pi_phone);
                TextView t3 = (TextView) v2.findViewById(R.id.pi_email);
                de.hdodenhof.circleimageview.CircleImageView pic = (de.hdodenhof.circleimageview.CircleImageView) v2.findViewById(R.id.profile_image_pi);
                FloatingActionButton floatingActionButton = (FloatingActionButton) v2.findViewById(R.id.del);
                login_state_checker = new login_state_checker(MainActivity.this);
                builder.setView(v2);
                t1.setText(login_state_checker.putname());
                t2.setText(login_state_checker.putnumber());
                t3.setText(login_state_checker.putemail());
                Uri savedImageURI = Uri.parse(login_state_checker.putimage());

                pic.setImageURI(savedImageURI);
                alertDialog = builder.create();
                alertDialog.setTitle("Account Details");
                alertDialog.show();
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login_state_checker.Cl();
                        shared_pref_pass.remov();
                        Intent i = new Intent(getApplicationContext(), heading_activity.class);
                        startActivity(i);
                        finish();
                    }
                });
                alertDialog.setCancelable(true);
            }
        });

        imageView2 = (ImageView) findViewById(R.id.exit);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
                b.setTitle("Quit App");
                b.setMessage("Do you really want to exit?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.setCancelable(false);
                b.show();
            }
        });
        //   d = (DrawerLayout)findViewById(R.id.container);
        //  nv = (NavigationView)findViewById(R.id.nav);
        //    ActionBar ab = getSupportActionBar();
        //  ab.setDisplayHomeAsUpEnabled(true);
        //   call_details = new Call_Details(name_c,number_c,email_c,Img);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

 /*   @Override
    public void senddata(String name, String number, String image, int id, String email) {
        name_c = name;
        number_c = number;
        email_c = email;
        Img = image;
    }*/

}
