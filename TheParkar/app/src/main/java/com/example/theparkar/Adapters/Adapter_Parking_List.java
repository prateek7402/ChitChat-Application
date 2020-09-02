package com.example.theparkar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theparkar.Book_Parking_Activity;
import com.example.theparkar.Data_Model.DataModel_ParkingInfo;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.Data_Model.Datum;
import com.example.theparkar.Parking_Information_Layout;
import com.example.theparkar.R;

import java.util.ArrayList;

public class Adapter_Parking_List extends RecyclerView.Adapter<Adapter_Parking_List.Parking_View_Holder> {

    Context context;
    ArrayList<DataModel_ParkingInfo> art;



    public Adapter_Parking_List(Context context, ArrayList<DataModel_ParkingInfo> art) {
        this.context = context;
        this.art = art;
    }

    @NonNull
    @Override
    public Parking_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_parking_list_view_model, parent, false);
        return new Parking_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Parking_View_Holder holder, final int position) {
        final DataModel_ParkingInfo dataModel_parkingInfo = art.get(position);
        Glide.with(holder.parking_Image.getContext())
                .load(dataModel_parkingInfo.getImage())
                .into(holder.parking_Image);
        holder.parking_name.setText(dataModel_parkingInfo.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Parking_Information_Layout.class);
                i.putExtra("Parking_id", dataModel_parkingInfo.getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return art.size();
    }


    public class Parking_View_Holder extends RecyclerView.ViewHolder {
        TextView parking_name, parking_info;
        ImageView parking_Image;

        public Parking_View_Holder(@NonNull View itemView) {
            super(itemView);
            parking_name = itemView.findViewById(R.id.parking_main_name);
            parking_info = itemView.findViewById(R.id.parking_main_details);
            parking_Image = itemView.findViewById(R.id.parking_main_image);
        }
    }


    public void swap(ArrayList<DataModel_ParkingInfo> obj) {
        art = obj;
        notifyDataSetChanged();
    }


}
