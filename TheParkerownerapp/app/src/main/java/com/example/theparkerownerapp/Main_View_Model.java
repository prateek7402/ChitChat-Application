package com.example.theparkerownerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.theparkerownerapp.Adapters.Parking_Layout_Adapter;
import com.example.theparkerownerapp.Data_Models.AbstractItem;
import com.example.theparkerownerapp.Data_Models.CenterItem;
import com.example.theparkerownerapp.Data_Models.DataModel_ParkingInfo;
import com.example.theparkerownerapp.Data_Models.Data_Model_For_Parking_Slots;
import com.example.theparkerownerapp.Data_Models.EdgeItem;
import com.example.theparkerownerapp.Data_Models.EmptyItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Main_View_Model extends AppCompatActivity {
    TextView total,free,name;
    de.hdodenhof.circleimageview.CircleImageView image;
    RecyclerView recyclerView;
    ArrayList<AbstractItem> arrayList;
    ArrayList<Data_Model_For_Parking_Slots> art;
    FirebaseFirestore firebaseFirestore;
    Parking_Layout_Adapter parking_layout_adapter;
    int tot=0;
    String id;
    String num;
    private static final int COLUMNS = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__view__model);

//        id = getIntent().getExtras().getString("Parking_id");
        total = findViewById(R.id.total_space);
        free = findViewById(R.id.total_free_space);
        name = findViewById(R.id.layout_parking_name);
        image = findViewById(R.id.parking_layout_image);
        recyclerView = findViewById(R.id.container_layout_recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        recyclerView.setLayoutManager(manager);
        art = new ArrayList<>();
        arrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        num = "1"+"_Parking_Model";
        updatelist();

        firebaseFirestore.collection("Parking_List").document("1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DataModel_ParkingInfo dataModel_parkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                name.setText(dataModel_parkingInfo.getName());
                //  image.setImageResource(Integer.parseInt(dataModel_parkingInfo.getImage()));
                Glide.with(getApplicationContext())
                        .load(dataModel_parkingInfo.getImage())
                        .into(image);
            }
        });
    }

    public void updatelist()
    {
        firebaseFirestore.collection(num).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    arrayList.clear();
                    int fre = 0;
                    int i = 0;
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        Data_Model_For_Parking_Slots data_model_for_parking_slots = documentSnapshot.toObject(Data_Model_For_Parking_Slots.class);
                     //   art.add((new Data_Model_For_Parking_Slots(data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.getVehicle(),data_model_for_parking_slots.getParker_name(),data_model_for_parking_slots.getVehicle_Number(),data_model_for_parking_slots.getPark_stat())));
                        //art.add(new Data_Model_For_Parking_Slots(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),data_model_for_parking_slots.getParker_name(),data_model_for_parking_slots.getVehicle_Number(),data_model_for_parking_slots.getPark_stat());
                        if (i%COLUMNS==0 || i%COLUMNS==4) {
                            arrayList.add(new EdgeItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i),data_model_for_parking_slots.getPark_stat()));
                        } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                            arrayList.add(new CenterItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i),data_model_for_parking_slots.getPark_stat()));
                        } else {
                            arrayList.add(new EmptyItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i),data_model_for_parking_slots.getPark_stat()));
                        }
                    }
                    for(AbstractItem pi : arrayList)
                    {
                        if(pi.isCurrent_status() == false)
                            fre++;
                    }
                    tot = arrayList.size();
                    total.setText("Total Space Available:"+tot);
                    free.setText("Total Free Space:"+fre);
                    parking_layout_adapter = new Parking_Layout_Adapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(parking_layout_adapter);
                    parking_layout_adapter.swap(arrayList);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updatelist();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatelist();
    }
}
