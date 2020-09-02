package com.example.theparkerownerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.theparkerownerapp.Data_Models.DataModel_ParkingInfo;
import com.example.theparkerownerapp.Data_Models.Data_Model_For_Customer_Analytics;
import com.example.theparkerownerapp.Data_Models.Data_Model_For_Parking_Owner_History;
import com.example.theparkerownerapp.Data_Models.Data_Model_For_Parking_Slots;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Check_In extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    TextView name, ide, info;
    FirebaseAuth auth;
    String parker, vehicle, vehicle_number;
    Date date;
    Random random;
    String id;
    int idt;
    String num;
    Data_Model_For_Parking_Slots data_model_for_parking_slots;
    // Data_Model_for_user_Details data_model_for_user_details;
    de.hdodenhof.circleimageview.CircleImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__in);

        firebaseFirestore = FirebaseFirestore.getInstance();
        id = getIntent().getExtras().getString("Parking");
        ide = findViewById(R.id.book_parking_space_id);
        name = findViewById(R.id.book_parking_space_name);
        img = findViewById(R.id.book_parking_image);
        info = findViewById(R.id.vehicle_checkin_info);
        ide.setText("The Space ID:" + id);
        auth = FirebaseAuth.getInstance();
        date = Calendar.getInstance().getTime();
        random = new Random();
        num = String.valueOf(id.charAt(0)) + "_Parking_Model";

        firebaseFirestore.collection("Parking_List").document(String.valueOf(id.charAt(0))).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DataModel_ParkingInfo dataModel_parkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                name.setText(dataModel_parkingInfo.getName());
                Glide.with(getApplicationContext()).load(dataModel_parkingInfo.getImage()).into(img);
            }
        });
       // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        firebaseFirestore.collection("1_Parking_Model").document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                data_model_for_parking_slots = documentSnapshot.toObject(Data_Model_For_Parking_Slots.class);
                parker = data_model_for_parking_slots.getParker_name();
                vehicle = data_model_for_parking_slots.getVehicle();
                vehicle_number = data_model_for_parking_slots.getVehicle_Number();
                info.setText("The Vehicle is :" + vehicle + "\n" + "The Parker is :" + parker + "\n" + "The Vehicle Number is :" + vehicle_number);
            }
        });

    }

    public void CheckIn(View v1) {

        idt = random.nextInt();
        final String unique = String.valueOf(date) + String.valueOf(idt);

        firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
              /*  FirebaseFirestore fire = FirebaseFirestore.getInstance();
                Data_Model_For_Customer_Analytics data_model_for_customer_analytics = new Data_Model_For_Customer_Analytics(unique,parker, String.valueOf(date.getTime()), vehicle, null, id, num);
                fire.collection(auth.getUid()).document(unique).set(data_model_for_customer_analytics).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      *//*  Intent i = new Intent(getApplicationContext(), Parking_Map_Layout.class);
                        Toast.makeText(Book_Parking_Activity.this, "congratulations the data is been saved and your parking slot is been booked", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();*//*
                    }
                });*/
            }
        });
        //final String unique = String.valueOf(date) + String.valueOf(idt);
        Data_Model_For_Parking_Slots data_model_for_parking_slots = new Data_Model_For_Parking_Slots(true, unique, id, vehicle, parker, vehicle_number, "Non_Empty");

        firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).set(data_model_for_parking_slots).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {  //   Toast.makeText(Book_Parking_Activity.this, "", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Main_View_Model.class);
                Toast.makeText(getApplicationContext(), "congratulations the data is been saved and you have been checked in", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }
        });
    }
}