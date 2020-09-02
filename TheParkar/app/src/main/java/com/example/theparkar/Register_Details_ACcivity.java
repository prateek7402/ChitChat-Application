package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_for_user_Details;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_Details_ACcivity extends AppCompatActivity {
FirebaseAuth auth;
FirebaseFirestore firebaseFirestore;
TextView u_name,u_phone,u_address,u_vehicle,u_vehicle_number;
Button details_submit;
Data_Model_for_user_Details data_model_for_user_details;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__details__accivity);
        auth = FirebaseAuth.getInstance();
        final String user_id = auth.getUid();
        u_name = findViewById(R.id.register_name);
        u_phone = findViewById(R.id.register_number);
        u_address = findViewById(R.id.register_address);
        u_vehicle = findViewById(R.id.register_car_model);
        u_vehicle_number = findViewById(R.id.register_car_number);
        details_submit = findViewById(R.id.b_submit_detail);
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.save_data__progress);
        details_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = u_name.getText().toString();
                String phone = u_phone.getText().toString();
                String address = u_address.getText().toString();
                String vehicle = u_vehicle.getText().toString();
                String vehicle_number = u_vehicle_number.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)||TextUtils.isEmpty(vehicle)||TextUtils.isEmpty(vehicle_number))
                {
                    Toast.makeText(Register_Details_ACcivity.this, "Pleaes enter the asked details", Toast.LENGTH_SHORT).show();
                    return;
                }

                data_model_for_user_details = new Data_Model_for_user_Details(name,auth.getCurrentUser().getEmail(),phone,address,vehicle,vehicle_number);

                firebaseFirestore.collection("Users").document(user_id).set(data_model_for_user_details).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Register_Details_ACcivity.this, "The data is been saved successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),Deatils_Tab.class);
                        Toast.makeText(Register_Details_ACcivity.this, "We are taking you to the Login Page. Pleaes login", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Register_Details_ACcivity.this, "The data is not been saved, there is been a issue", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


       // Toast.makeText(this, user_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
