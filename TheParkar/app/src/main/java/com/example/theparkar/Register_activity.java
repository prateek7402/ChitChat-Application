package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_activity extends AppCompatActivity {
    EditText r_email, r_password;
    Button register;
    ProgressBar pb;
    CheckBox checkBox;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        r_email = findViewById(R.id.email_register);
        r_password = findViewById(R.id.pass_register);
        register = findViewById(R.id.b_register);
        pb = findViewById(R.id.register_progress);
        checkBox = findViewById(R.id.check_box_register);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = r_email.getText().toString();
                String password = r_password.getText().toString();
                if (checkBox.isChecked()) {
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(Register_activity.this, "Please enter a email address to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(Register_activity.this, "Please enter the password to continue", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pb.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register_activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Register_activity.this, "Created the user with the given credentials:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register_activity.this, "Registration failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), Register_Details_ACcivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register_activity.this, "Please accept the policies to proceed further", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    public void Pol(View v1) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        pb.setVisibility(View.GONE);
    }
}
