package com.example.thecrackchat;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecrackchat.Data_Classes.User_Details;
import com.example.thecrackchat.Login_State_Checker.Shrared_prefference_login;
import com.example.thecrackchat.Main_UI.Main_Home;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    EditText t_name, t_email, t_userName, t_password, t_password_agaain;
    ImageView login, facebook, google;
    TextView Login;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Shrared_prefference_login shrared_prefference_login;
   // private static final BigInteger RC_SIGN_IN = 938106662189;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_name = findViewById(R.id.editTextName);
        t_email = findViewById(R.id.editTextEmail);
        t_userName = findViewById(R.id.editTextUserName);
        t_password = findViewById(R.id.editTextPassword);
        t_password_agaain = findViewById(R.id.editTextPassword_again);
        login = findViewById(R.id.but_register);
        facebook = findViewById(R.id.bt_facebook);
        google = findViewById(R.id.bt_google);
        Login = findViewById(R.id.red_login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        // Configure Google Sign In

        shrared_prefference_login = new Shrared_prefference_login(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = t_name.getText().toString();
                final String email = t_email.getText().toString();
                final String user = t_userName.getText().toString();
                String pass1 = t_password.getText().toString();
                String pass2 = t_password_agaain.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(user) || TextUtils.isEmpty(pass1) || TextUtils.isEmpty(pass2))
                {
                    Snackbar.make(findViewById(R.id.new_login), "enter all the fields", Snackbar.LENGTH_SHORT).show();
                }
                    else{
                    if (pass1.length() <= 6) {
                        Snackbar.make(findViewById(R.id.new_login), "The length of the password should be greater than 6 aplabets", Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (pass1.equals(pass2)==false) {
                            Snackbar.make(findViewById(R.id.new_login), "The password didn't matched", Snackbar.LENGTH_SHORT).show();
                        } else {
                            firebaseAuth.createUserWithEmailAndPassword(email, pass1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Snackbar.make(findViewById(R.id.new_login), "Congratulations the user is been created\nredirecting to the main page", Snackbar.LENGTH_SHORT).show();
                                    String uid = firebaseAuth.getUid();
                                    User_Details  user_details = new User_Details(name,uid,email,user,"","");
                                    firebaseFirestore.collection("Users").document(uid).set(user_details).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent i = new Intent(getApplicationContext(), Main_Home.class);
                                            shrared_prefference_login.setStatus();

                                            startActivity(i);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Snackbar.make(findViewById(R.id.new_login), "sorry the data could not be saved", Snackbar.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(findViewById(R.id.new_login), "Login failed due to some of the technical issues. Please retry", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            }
        });

      google.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Snackbar.make(findViewById(R.id.bt_google), "Sorry we are not offering this service at time.", Snackbar.LENGTH_SHORT).show();
          }
      });
      facebook.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Snackbar.make(findViewById(R.id.bt_google), "Sorry we are not offering this service at time.", Snackbar.LENGTH_SHORT).show();
          }
      });

      Login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
              startActivity(i);
              finish();
          }
      });
    }
}
