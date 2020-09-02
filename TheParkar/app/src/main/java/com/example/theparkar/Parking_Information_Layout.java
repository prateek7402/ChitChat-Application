package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theparkar.Adapters.Adapter_Parking_List;
import com.example.theparkar.Adapters.Tab_Adapter;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_for_user_Details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Parking_Information_Layout extends AppCompatActivity  {
TextView name,owner,space,empty,email,number;
FirebaseFirestore firebaseFirestore;
String num;
String id;
ArrayList<Data_Model_For_Parking_Slots> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__information__layout);
        id = getIntent().getExtras().getString("Parking_id");
        name = findViewById(R.id.paring_name);
        owner = findViewById(R.id.paring_owner);
        space = findViewById(R.id.parking_capacity);
        empty = findViewById(R.id.parking_empty);
        email = findViewById(R.id.paring_owner_email);
        number = findViewById(R.id.paring_owner_number);
       // ratingBar = findViewById(R.id.parking_rating);
       // tabLayout = findViewById(R.id.parking_tab);
       // viewPager = findViewById(R.id.parking_viewPager);
        firebaseFirestore = FirebaseFirestore.getInstance();
//        String num = "2";
        num = id+"_Parking_Model";

        DocumentReference documentReference = firebaseFirestore.collection("Parking_List").document(id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DataModel_ParkingInfo dataModel_ParkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                name.setText(dataModel_ParkingInfo.getName());
                owner.setText("Owner:"+dataModel_ParkingInfo.getOwner());
               // space.setText(dataModel_ParkingInfo.getCapacity());
               // empty.setText(dataModel_ParkingInfo.getEmpty());
                email.setText("Email:"+dataModel_ParkingInfo.getEmail());
                number.setText("Contact:"+dataModel_ParkingInfo.getNumber());
            }
        });
     firebaseFirestore.collection(num).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
         @Override
         public void onComplete(@NonNull Task<QuerySnapshot> task) {
             if(task.isSuccessful())
             {
                 int full = 0;
                 for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                     Data_Model_For_Parking_Slots data_model_for_parking_slots = documentSnapshot.toObject(Data_Model_For_Parking_Slots.class);
                     arrayList.add(new Data_Model_For_Parking_Slots(data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),data_model_for_parking_slots.getParker_name(),data_model_for_parking_slots.getVehicle_Number(),data_model_for_parking_slots.getPark_stat()));
                 }
                 for(Data_Model_For_Parking_Slots i : arrayList)
                 {
                     if(i.isCurrent_status() == false)
                         full++;
                 }
                 space.setText(""+arrayList.size());
                 empty.setText(""+full);
             }
         }
     });


    }

    public void Home(View v1)
    {
      Intent i = new Intent(getApplicationContext(),Parking_Map_Layout.class);
      i.putExtra("Parking_id", id);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(i);
    }

    public void Rating(View v2)
    {
       Intent i = new Intent(getApplicationContext(),Parking_Rating.class);
       i.putExtra("Parking_id", id);
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(i);
    }

    public void Gallery(View v3)
    {

    }

    public void Contact(View v4)
    {

    }

}
