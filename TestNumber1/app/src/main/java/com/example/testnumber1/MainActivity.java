package com.example.testnumber1;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
EditText l_email,l_password;
Button Login;
FirebaseAuth auth;
ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l_email = findViewById(R.id.email_login);
        l_password  =findViewById(R.id.pass_login);
        Login = findViewById(R.id.b_login);
        pb = findViewById(R.id.login_progress);
        auth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = l_email.getText().toString();
                String password = l_password.getText().toString();
                pb.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivity.this, "Please enter the valid details", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful())
                         {
                             Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(getApplicationContext(),Main_new.class);
                             startActivity(i);
                             pb.setVisibility(View.GONE);
                         }
                    }
                });
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        pb.setVisibility(View.GONE);
    }

    public  void Register(View v1)
    {
        Intent i = new Intent(getApplicationContext(),Register_Activity.class);
        startActivity(i);
    }
}
