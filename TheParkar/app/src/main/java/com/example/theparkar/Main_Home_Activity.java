package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.theparkar.Adapters.Adapter_Parking_List;
import com.example.theparkar.Adapters.Adapter_Parking_Rating;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Main_Home_Activity extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseFirestore firebaseFirestore;
Adapter_Parking_List adapter_parking_list;
ArrayList<DataModel_ParkingInfo> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        recyclerView = findViewById(R.id.parking_list_recycler_view);
        firebaseFirestore = FirebaseFirestore.getInstance();

        updateList();
        arr.clear();
    }

    public void updateList()
    {

        firebaseFirestore.collection("Parking_List").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    arr.clear();
                   // Toast.makeText(Main_Home_Activity.this, "Task here"+task.getResult().size(), Toast.LENGTH_SHORT).show();
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        DataModel_ParkingInfo dataModel_parkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                        arr.add(new DataModel_ParkingInfo(dataModel_parkingInfo.getEmail(),dataModel_parkingInfo.getImage(),dataModel_parkingInfo.getName(),dataModel_parkingInfo.getNumber(),dataModel_parkingInfo.getOwner(),dataModel_parkingInfo.getId()));
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter_parking_list = new Adapter_Parking_List(getApplicationContext(),arr);
                    recyclerView.setAdapter(adapter_parking_list);
                    adapter_parking_list.swap(arr);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
        arr.clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList();
        arr.clear();
    }
}
