package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.theparkar.Adapters.Adapter_For_History;
import com.example.theparkar.Adapters.Adapter_Parking_Rating;
import com.example.theparkar.Data_Model.Data_Model_For_Customer_Analytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class History_Activity extends AppCompatActivity {
RecyclerView recyclerView;
Adapter_For_History adapter_for_history;
FirebaseAuth auth;
FirebaseFirestore firebaseFirestore;
ArrayList<Data_Model_For_Customer_Analytics> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_);
        recyclerView = findViewById(R.id.history_rv);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        updateList();
    }
    public void updateList()
    {
        firebaseFirestore.collection(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                arr.clear();
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot snapshot : task.getResult())
                    {
                        Data_Model_For_Customer_Analytics data_model_for_customer_analytics = snapshot.toObject(Data_Model_For_Customer_Analytics.class);
                        arr.add(new Data_Model_For_Customer_Analytics(data_model_for_customer_analytics.getTransaction_id(),data_model_for_customer_analytics.getCustomer_name(),data_model_for_customer_analytics.getTime(),data_model_for_customer_analytics.getVehicle(),data_model_for_customer_analytics.getDuration(),data_model_for_customer_analytics.getParking_id(),data_model_for_customer_analytics.getParking_space()));
                    }
                    adapter_for_history = new Adapter_For_History(getApplicationContext(),arr);
                    recyclerView.setAdapter(adapter_for_history);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter_for_history.swap(arr);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        arr.clear();
        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        arr.clear();
        updateList();
    }
}
