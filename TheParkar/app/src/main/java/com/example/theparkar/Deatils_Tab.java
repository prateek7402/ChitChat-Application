package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.theparkar.Login_State_Checker.Shrared_prefference_login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Deatils_Tab extends AppCompatActivity {
    EditText t_email, t_pass;
    Button login;
    FirebaseAuth auth;
    ProgressBar pb;
    Shrared_prefference_login shrared_prefference_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils__tab);
        t_email = findViewById(R.id.email_login);
        t_pass = findViewById(R.id.pass_login);
        login = findViewById(R.id.b_login);
        auth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.login_progress);
        shrared_prefference_login = new Shrared_prefference_login(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t_email.getText().toString();
                final String password = t_pass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Deatils_Tab.this, "Please enter a registered email address", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Deatils_Tab.this, "Please enter the password", Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Deatils_Tab.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pb.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                t_pass.setError("Password is too short");
                            } else {
                                Toast.makeText(Deatils_Tab.this, "Login failed, please check your email id or the password", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent launch = new Intent(getApplicationContext(),Main_Home.class);
                            shrared_prefference_login.setStatus();
                            startActivity(launch);
                            finish();

                        }
                    }
                });
            }
        });
    }

    public void Forget(View v1) {
        Intent i = new Intent(getApplicationContext(),Reset_Password_Activity.class);
        startActivity(i);
    }

    public void Register(View v2) {
        Intent i = new Intent(getApplicationContext(), Register_activity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pb.setVisibility(View.GONE);
    }
}

