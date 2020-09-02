package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Password_Activity extends AppCompatActivity {
    EditText reset_email;
    Button reset;
    ProgressBar pb;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password_);
        reset_email = findViewById(R.id.reset_password);
        reset = findViewById(R.id.b_reset_password);
        pb = findViewById(R.id.reset_progress);
        auth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reset_email.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Reset_Password_Activity.this, "Please enter a valid email address to reset the password", Toast.LENGTH_LONG).show();
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(Reset_Password_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Reset_Password_Activity.this, "We have sent you instructions to your registered email address to reset the password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Reset_Password_Activity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        pb.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pb.setVisibility(View.GONE);
    }
}
