package com.example.jaankaar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PIN_entry extends AppCompatActivity {
    String pass = "";
    TextView t1, t2, t3, t4;
    shared_pref_pass sh;
    int a = 0;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_layout_login);
        t1 = (TextView) findViewById(R.id.past1);
        sh = new shared_pref_pass(this);
    }


    public void add1(View v1) {
        pass +=1;
        t1.setText(pass+" ");
    }

    public void add2(View v2) {
        pass +=2;
        t1.setText(pass+" ");
    }

    public void add3(View v3) {
        pass +=3;
        t1.setText(pass+" ");
    }

    public void add4(View v4) {
        pass +=4;
        t1.setText(pass+" ");
    }

    public void add5(View v5) {
        pass +=5;
        t1.setText(pass+" ");
    }

    public void add6(View v6) {
        pass +=6;
        t1.setText(pass+" ");
    }

    public void add7(View v7) {
        pass +=7;
        t1.setText(pass+" ");
    }

    public void add8(View v8) {
        pass +=8;
        t1.setText(pass+" ");
    }

    public void add9(View v9) {
        pass +=9;
        t1.setText(pass+" ");
    }

    public void add0(View v0) {
        pass +=0;
        t1.setText(pass+" ");
    }

    public void back(View v10) {
        if(pass.equals(""))
        {
            Toast.makeText(this, "Please enter the PIN", Toast.LENGTH_SHORT).show();
        }
        else {
            pass = pass.replace(pass.substring(pass.length() - 1), "");
            t1.setText(pass);
        }
    }

    public void savedata(View V11) {
        if(pass.equals("")||pass.length()<4) {
            Toast.makeText(this, "Please enter a valid Entry", Toast.LENGTH_SHORT).show();
        }
        else
        {
        AlertDialog.Builder builder = new AlertDialog.Builder(PIN_entry.this);
        View v = getLayoutInflater().inflate(R.layout.alerrt_for_saving_pin, null);
        final EditText sup = (EditText) v.findViewById(R.id.supername);
        ImageButton sub = (ImageButton) v.findViewById(R.id.saveact);
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.show();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String supername = sup.getText().toString();
                if (supername.equals("")) {
                    Toast.makeText(PIN_entry.this, "Please give the answer", Toast.LENGTH_SHORT).show();
                } else {
                    sh.setdata(pass, supername);
                    Intent i = new Intent(getApplicationContext(), state_checker.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
    }
}
