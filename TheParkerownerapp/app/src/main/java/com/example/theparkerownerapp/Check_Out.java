package com.example.theparkerownerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

public class Check_Out extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    TextView name, ide, info;
    FirebaseAuth auth;
    String parker, vehicle, vehicle_number;
    Date date;
    Random random;
    String id;
    int idt;
    String num;
    EditText duration;
    Data_Model_For_Parking_Slots data_model_for_parking_slots;
    // Data_Model_for_user_Details data_model_for_user_details;
    de.hdodenhof.circleimageview.CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__out);

        firebaseFirestore = FirebaseFirestore.getInstance();
        id = getIntent().getExtras().getString("Parking");
        ide = findViewById(R.id.book_parking_space_id_checkout);
        name = findViewById(R.id.book_parking_space_name_ckeckout);
        img = findViewById(R.id.book_parking_image_checkout);
        info = findViewById(R.id.vehicle_checkin_info_checkout);
        ide.setText("The Space ID:" + id);
        auth = FirebaseAuth.getInstance();
        date = Calendar.getInstance().getTime();
        duration = findViewById(R.id.duration_check_out);
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

    public void CheckOut(View v1)
    {
        final String dura = duration.getText().toString();
        if(TextUtils.isEmpty(dura))
        {
            Toast.makeText(this, "Please enter the duration", Toast.LENGTH_SHORT).show();
        }
        else{
            idt = random.nextInt();
            final String unique = String.valueOf(date) + String.valueOf(idt);
            firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
            Data_Model_For_Parking_Slots data_model_for_parking_slots = new Data_Model_For_Parking_Slots(false, "", id, "", "", "", "Empty");
            firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).set(data_model_for_parking_slots).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {  //   Toast.makeText(Book_Parking_Activity.this, "", Toast.LENGTH_SHORT).show();
                    /*FirebaseFirestore fire = FirebaseFirestore.getInstance();
                    Data_Model_For_Parking_Owner_History data_model_for_customer_analytics = new Data_Model_For_Parking_Owner_History(parker, vehicle_number, vehicle, String.valueOf(date), "", unique);
                    fire.collection("Owner_Parking_History_1").document(unique).set(data_model_for_customer_analytics).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent i = new Intent(getApplicationContext(), Main_View_Model.class);
                            Toast.makeText(getApplicationContext(), "congratulations the data is been saved and you have been checked in", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    });*/
                }
            });

            FirebaseFirestore fire = FirebaseFirestore.getInstance();
            final Data_Model_For_Customer_Analytics data_model_for_customer_analytics = new Data_Model_For_Customer_Analytics(unique,parker, String.valueOf(date), vehicle, dura, id, num);
            fire.collection(auth.getUid()).document(unique).set(data_model_for_customer_analytics).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                      /*  Intent i = new Intent(getApplicationContext(), Parking_Map_Layout.class);
                        Toast.makeText(Book_Parking_Activity.this, "congratulations the data is been saved and your parking slot is been booked", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();*/
                    FirebaseFirestore fired = FirebaseFirestore.getInstance();
                    Data_Model_For_Parking_Owner_History data_model_for_parking_owner_history = new Data_Model_For_Parking_Owner_History(parker, vehicle_number, vehicle, String.valueOf(date), dura, unique);
                    fired.collection("Owner_Parking_History_1").document(unique).set(data_model_for_parking_owner_history).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Check_Out.this, "The vehicle is been successfully checked out", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),Main_View_Model.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            });

        }
    }
}
