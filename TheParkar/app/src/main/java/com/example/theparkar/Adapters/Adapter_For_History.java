package com.example.theparkar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Customer_Analytics;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_For_History extends RecyclerView.Adapter<Adapter_For_History.History_View_Holder> {

    Context context;
    ArrayList<Data_Model_For_Customer_Analytics> ar;
    DataModel_ParkingInfo dataModel_parkingInfo;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public Adapter_For_History(Context context, ArrayList<Data_Model_For_Customer_Analytics> ar) {
        this.context = context;
        this.ar = ar;
    }

    @NonNull
    @Override
    public Adapter_For_History.History_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_model_history, parent, false);
        return new Adapter_For_History.History_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_For_History.History_View_Holder holder, int position) {
          Data_Model_For_Customer_Analytics dat = ar.get(position);
          String space_id = dat.getParking_space();
          firebaseFirestore.collection("Parking_List").document(String.valueOf(space_id.charAt(0))).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
              @Override
              public void onSuccess(DocumentSnapshot documentSnapshot) {
                  dataModel_parkingInfo = documentSnapshot.toObject(DataModel_ParkingInfo.class);
              }
          });
/*        Glide.with(holder.img.getContext())
                .load(dataModel_parkingInfo.getImage())
                .into(holder.img);*/
        holder.t1.setText("Unique Transaction id: "+dat.getTransaction_id());
//        holder.t2.setText("Parking Name: "+dataModel_parkingInfo.getName());
        holder.t3.setText("Duration: "+dat.getDuration()+"     CheckIN Time: "+dat.getTime());
    }

    @Override
    public int getItemCount() {
        return ar.size();
    }

    public  class History_View_Holder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView t1,t2,t3;
        public History_View_Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.history_background);
            t1 = itemView.findViewById(R.id.history_id);
            t2 = itemView.findViewById(R.id.history_name);
            t3 = itemView.findViewById(R.id.history_duration);
        }
    }

    public void swap(ArrayList<Data_Model_For_Customer_Analytics> obj) {
        ar = obj;
        notifyDataSetChanged();
    }
}
