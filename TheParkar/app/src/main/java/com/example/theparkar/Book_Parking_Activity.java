package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Customer_Analytics;
import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_for_user_Details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Book_Parking_Activity extends AppCompatActivity {
   // String park_id;
    FirebaseFirestore firebaseFirestore;
    TextView name,ide;
    FirebaseAuth auth;
    String parker,vehicle,vehicle_number;
    Date date;
    Random random;
    String id;
    int idt;
    String num;
    Data_Model_for_user_Details data_model_for_user_details;
    de.hdodenhof.circleimageview.CircleImageView img;

    ArrayList<Data_Model_For_Customer_Analytics> art = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__parking_);
       // park_id = getIntent().getExtras().getString("Parking_id");
        firebaseFirestore = FirebaseFirestore.getInstance();
        id = getIntent().getExtras().getString("Parking");
        ide = findViewById(R.id.book_parking_space_id);
        name = findViewById(R.id.book_parking_space_name);
        img = findViewById(R.id.book_parking_image);
        ide.setText("The Space ID:"+id);
        auth = FirebaseAuth.getInstance();
        date = Calendar.getInstance().getTime();
        random = new Random();
        num = String.valueOf(id.charAt(0))+"_Parking_Model";
        //Toast.makeText(this, park_id, Toast.LENGTH_SHORT).show();
        firebaseFirestore.collection("Parking_List").document(String.valueOf(id.charAt(0))).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DataModel_ParkingInfo dataModel_parkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                name.setText(dataModel_parkingInfo.getName());
                Glide.with(getApplicationContext()).load(dataModel_parkingInfo.getImage()).into(img);
            }
        });

        firebaseFirestore.collection("Users").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                data_model_for_user_details = documentSnapshot.toObject(Data_Model_for_user_Details.class);
                parker = data_model_for_user_details.getName();
                vehicle = data_model_for_user_details.getVehicle();
                vehicle_number = data_model_for_user_details.getVehicle_Number();

            }
        });
        firebaseFirestore.collection(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot queryDocumentSnapshot: task.getResult())
                    {
                        Data_Model_For_Customer_Analytics data_model_for_customer_analytics = queryDocumentSnapshot.toObject(Data_Model_For_Customer_Analytics.class);
                        art.add(new Data_Model_For_Customer_Analytics(data_model_for_customer_analytics.getTransaction_id(),data_model_for_customer_analytics.getCustomer_name(),data_model_for_customer_analytics.getTime(),data_model_for_customer_analytics.getVehicle(),data_model_for_customer_analytics.getDuration(),data_model_for_customer_analytics.getParking_id(),data_model_for_customer_analytics.getParking_space()));
                    }
                }
            }
        });
    }
    public void Payment(View v1)
    {
        if(true) {
            idt = random.nextInt();
            final String unique = String.valueOf(date) + String.valueOf(idt);

            Data_Model_For_Parking_Slots data_model_for_parking_slots = new Data_Model_For_Parking_Slots(true, unique, id, vehicle,parker,vehicle_number,"Empty");

            firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });

            firebaseFirestore.collection(num).document(id.replace(String.valueOf(id.charAt(0)) + "_", "")).set(data_model_for_parking_slots).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Book_Parking_Activity.this, "The slot is been booked by you and the booking will be confirmed after you check in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Parking_Map_Layout.class);
                    startActivity(i);
                    finish();
                    /* FirebaseFirestore fire = FirebaseFirestore.getInstance();
                    Data_Model_For_Customer_Analytics data_model_for_customer_analytics = new Data_Model_For_Customer_Analytics(unique, data_model_for_user_details.getName(), null, data_model_for_user_details.getVehicle(), null, id, num);
                    fire.collection(auth.getUid()).document(unique).set(data_model_for_customer_analytics).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent i = new Intent(getApplicationContext(), Parking_Map_Layout.class);
                            Toast.makeText(Book_Parking_Activity.this, "congratulations the data is been saved and your parking slot is been booked", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    });*/
                }
            });
        }
        else
        {
            Toast.makeText(this, "You are only allowed to park only 2 vehicles at a time through a single account", Toast.LENGTH_SHORT).show();
        }
    }
}
