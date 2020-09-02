package com.example.testnumber1;

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

public class Register_Activity extends AppCompatActivity {
    EditText r_email, r_pass;
    Button register;
    ProgressBar pb;
    CheckBox checkBox;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        r_email = findViewById(R.id.email_register);
        r_pass = findViewById(R.id.pass_register);
        pb = findViewById(R.id.register_progress);
        checkBox = findViewById(R.id.check_box_register);
        register = findViewById(R.id.b_register);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    pb.setVisibility(View.VISIBLE);
                    String email = r_email.getText().toString();
                    final String password = r_pass.getText().toString();
                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                        Toast.makeText(Register_Activity.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register_Activity.this, "Something has went wrong", Toast.LENGTH_SHORT).show();
                                if (password.length() < 6) {
                                    Toast.makeText(Register_Activity.this, "Enter a password of more than 6 characters", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Register_Activity.this, "Register successfull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                finish();
                                pb.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register_Activity.this, "Pleaes agree to our policies", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pb.setVisibility(View.GONE);
    }
}
