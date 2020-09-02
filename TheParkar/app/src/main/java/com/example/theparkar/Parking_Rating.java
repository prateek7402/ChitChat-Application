package com.example.theparkar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theparkar.Adapters.Adapter_Parking_Rating;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.Data_Model.Data_Model_for_user_Details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Parking_Rating extends AppCompatActivity {
    TextView rating, description;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    String user_name;
    Adapter_Parking_Rating adapter_parking_rating;
    ArrayList<Data_Model_For_Rating>  rating_area;
    String id;
    int total_rating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking__rating);
        rating = findViewById(R.id.parking_rating);
        description = findViewById(R.id.rating_description);
        recyclerView = findViewById(R.id.rating_recycler_view);
        floatingActionButton = findViewById(R.id.rating_floating_action_bar);
        rating_area = new ArrayList<Data_Model_For_Rating>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
      //  make_collection();
        id = getIntent().getExtras().getString("Parking_id");
        /* adapter_parking_rating = new Adapter_Parking_Rating(getApplicationContext(),rating_area);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter_parking_rating);
*/
       updateList();

        DocumentReference documentReference1 = firebaseFirestore.collection("Users").document(auth.getUid());
        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Data_Model_for_user_Details data_model_for_user_details = documentSnapshot.toObject(Data_Model_for_user_Details.class);
                user_name = data_model_for_user_details.getName();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Parking_Rating.this);
                final View v1 = getLayoutInflater().inflate(R.layout.rate_the_parking_layout, null);

                final RatingBar ratingBar = v1.findViewById(R.id.parking_rating);
                final EditText editText = v1.findViewById(R.id.rating_review);
                final de.hdodenhof.circleimageview.CircleImageView img = v1.findViewById(R.id.parking_image);
                final Button button = v1.findViewById(R.id.rating_submit);
                final String[] name_user = new String[1];


                ratingBar.setNumStars(5);
                builder.setView(v1);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                DocumentReference documentReference = firebaseFirestore.collection("Parking_List").document(id);
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        DataModel_ParkingInfo dataModel_ParkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
                        Glide.with(getApplicationContext())
                                .load(dataModel_ParkingInfo.getImage())
                                .into(img);
                    }
                });


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String rating = String.valueOf(ratingBar.getRating());
                        String description = editText.getText().toString().trim();

                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c);

                        Data_Model_For_Rating data_model_for_rating = new Data_Model_For_Rating(user_name, null, formattedDate, rating, description);
                        firebaseFirestore.collection(id).document(auth.getUid()).set(data_model_for_rating).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Parking_Rating.this, "Rating saved", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "The data is not been saved, there is been a issue", Toast.LENGTH_SHORT).show();
                            }
                        });
                        alertDialog.dismiss();
                        updateList();
                    }
                });
            }
        });

        
    }

    public void updateList()
    {
        firebaseFirestore.collection(id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                float avg_rating=0;
                if(task.isSuccessful())
                {
                    rating_area.clear();
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        Data_Model_For_Rating data_model_for_rating = documentSnapshot.toObject(Data_Model_For_Rating.class);
                        rating_area.add(new Data_Model_For_Rating(data_model_for_rating.getName(),data_model_for_rating.getImage(),data_model_for_rating.getTime(),data_model_for_rating.getRating(),data_model_for_rating.getDescription()));
                    }
                    for(Data_Model_For_Rating i : rating_area)
                    {
                        avg_rating = avg_rating + Float.parseFloat(i.getRating());
                    }
                    total_rating = rating_area.size();

                    avg_rating = avg_rating/total_rating;
                    rating.setText(""+avg_rating);
                    description.setText("The total number of rating are: "+total_rating);
                    adapter_parking_rating = new Adapter_Parking_Rating(getApplicationContext(),rating_area);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter_parking_rating);
                    adapter_parking_rating.swap(rating_area);
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        rating_area.clear();
        updateList();
    }


    @Override
    protected void onStart() {
        super.onStart();
        rating_area.clear();
        updateList();
    }

   /* public void make_collection(){
        for(int i = 1 ; i  <= 300 ; i++)
        {
            Data_Model_For_Parking_Slots data_model_for_parking_slots = new Data_Model_For_Parking_Slots(false,"","1_"+String.valueOf(i),"","","","Empty");
            final int finalI = i;
            firebaseFirestore.collection("1_Parking_Model").document(String.valueOf(i)).set(data_model_for_parking_slots).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "documrnt added "+ finalI, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/
}
