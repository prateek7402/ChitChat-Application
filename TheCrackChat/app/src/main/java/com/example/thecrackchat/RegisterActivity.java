package com.example.thecrackchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.thecrackchat.Login_State_Checker.Shrared_prefference_login;
import com.example.thecrackchat.Main_UI.Main_Home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
EditText email,password;
TextView reset,register;
ImageView facebook,google,login;
FirebaseAuth auth;
Shrared_prefference_login shrared_prefference_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextEmail_login);
        password = findViewById(R.id.editTextPassword_login);
        login = findViewById(R.id.login);
        reset = findViewById(R.id.reset_pass);
        register = findViewById(R.id.new_login);
        facebook = findViewById(R.id.facebook);
        google = findViewById(R.id.google);
        shrared_prefference_login = new Shrared_prefference_login(getApplicationContext());
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(em)||TextUtils.isEmpty(pass))
                {
                    Snackbar.make(findViewById(R.id.login), "Please fill all the fields", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(em,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent i = new Intent(getApplicationContext(), Main_Home.class);
                            shrared_prefference_login.setStatus();
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(findViewById(R.id.login), "Sorry for the inconvinience but login failed", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                View v1 = getLayoutInflater().inflate(R.layout.activity_reset_password, null);
                builder.setView(v1);
                builder.setCancelable(true);
                builder.show();
                final EditText editText = v1.findViewById(R.id.reset_password);
                Button button = v1.findViewById(R.id.b_reset_password);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = editText.getText().toString();
                        if(TextUtils.isEmpty(email))
                        {
                            Snackbar.make(findViewById(R.id.b_reset_password), "Enter a email id please", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Snackbar.make(findViewById(R.id.b_reset_password), "email to reset the password link is been sent", Snackbar.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(findViewById(R.id.b_reset_password), "Reset password email could not be sent", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.google), "Sorry we are not offering this service at time.", Snackbar.LENGTH_SHORT).show();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.google), "Sorry we are not offering this service at time.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
