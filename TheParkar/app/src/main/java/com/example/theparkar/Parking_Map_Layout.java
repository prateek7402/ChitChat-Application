package com.example.theparkar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.theparkar.Adapters.Parking_Layout_Adapter;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.ui.AbstractItem;
import com.example.theparkar.ui.CenterItem;
import com.example.theparkar.ui.EdgeItem;
import com.example.theparkar.ui.EmptyItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Parking_Map_Layout extends AppCompatActivity {
    TextView total,free,name;
    de.hdodenhof.circleimageview.CircleImageView image;
    RecyclerView recyclerView;
    ArrayList<AbstractItem> arrayList;
    FirebaseFirestore firebaseFirestore;
    Parking_Layout_Adapter parking_layout_adapter;
    int tot=0;
    String id;
    String num;
    private static final int COLUMNS = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__map__layout);
        id = getIntent().getExtras().getString("Parking_id");
        total = findViewById(R.id.total_space);
        free = findViewById(R.id.total_free_space);
        name = findViewById(R.id.layout_parking_name);
        image = findViewById(R.id.parking_layout_image);
        recyclerView = findViewById(R.id.container_layout_recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        recyclerView.setLayoutManager(manager);
      //  recyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        arrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        num = id+"_Parking_Model";
        updatelist();

        firebaseFirestore.collection("Parking_List").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
                       //arrayList.add(new Data_Model_For_Parking_Slots(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle()));
                       if (i%COLUMNS==0 || i%COLUMNS==4) {
                           arrayList.add(new EdgeItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i)));
                       } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                           arrayList.add(new CenterItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i)));
                       } else {
                           arrayList.add(new EmptyItem(data_model_for_parking_slots.getParking_id(),data_model_for_parking_slots.isCurrent_status(),data_model_for_parking_slots.getParker(),data_model_for_parking_slots.getVehicle(),String.valueOf(i)));
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
