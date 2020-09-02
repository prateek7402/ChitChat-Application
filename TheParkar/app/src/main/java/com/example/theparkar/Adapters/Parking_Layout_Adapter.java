package com.example.theparkar.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theparkar.Book_Parking_Activity;
import com.example.theparkar.Data_Model.Data_Model_For_Customer_Analytics;
import com.example.theparkar.Data_Model.Data_Model_For_Parking_Slots;
import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.R;
import com.example.theparkar.ui.AbstractItem;

import java.util.ArrayList;

public class Parking_Layout_Adapter extends RecyclerView.Adapter<Parking_Layout_Adapter.Parking_Layout_ViewHolder> {

    Context context;
    ArrayList<AbstractItem> arr;
    private LayoutInflater mLayoutInflater;
    ArrayList<Data_Model_For_Customer_Analytics> datum = new ArrayList<>();

    public Parking_Layout_Adapter(Context context, ArrayList<AbstractItem> arr) {
        this.context = context;
        this.arr = arr;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Parking_Layout_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.parking_map_layout_view_model, parent, false);
            return new Parking_Layout_ViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.parking_map_layout_view_model, parent, false);
            return new Parking_Layout_ViewHolder(itemView);
        } else {
            View itemView = new View(context);
            return new Parking_Layout_ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Parking_Layout_ViewHolder holder, final int position) {
        int type = arr.get(position).getType();
        final AbstractItem data = arr.get(position);
        if (type == AbstractItem.TYPE_CENTER) {
           if(data.isCurrent_status() == false)
           {
               Glide.with(holder.imgSeat.getContext())
                       .load(R.drawable.ic_directions_car_black_red)
                       .into(holder.imgSeat);
               holder.t_id_name.setText(data.getParking_id());
               holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(context, Book_Parking_Activity.class);
                       i.putExtra("Parking_id", String.valueOf(position+1));
                       i.putExtra("Parking", data.getParking_id());
                       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       context.startActivity(i);
                   }
               });
           }
           else
           {
               Glide.with(holder.imgSeat.getContext())
                       .load(R.drawable.ic_directions_car_black_green)
                       .into(holder.imgSeat);
               holder.t_id_name.setText(data.getParking_id());
               holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       AlertDialog.Builder builder = new AlertDialog.Builder(context);
                       View v1 = mLayoutInflater.inflate(R.layout.activity_booked_already, null);
                       builder.setView(v1);
                       builder.setCancelable(true);
                       final AlertDialog alertDialog = builder.create();
                       alertDialog.show();
                   }
               });
           }
        }
        else if(type == AbstractItem.TYPE_EDGE)
        {
            if(data.isCurrent_status() == false)
            {
                Glide.with(holder.imgSeat.getContext())
                        .load(R.drawable.ic_directions_car_black_red)
                        .into(holder.imgSeat);
                holder.t_id_name.setText(data.getParking_id());
                holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, Book_Parking_Activity.class);
                        i.putExtra("Parking_id", String.valueOf(position+1));
                        i.putExtra("Parking", data.getParking_id());
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                });
            }
            else
            {
                Glide.with(holder.imgSeat.getContext())
                        .load(R.drawable.ic_directions_car_black_green)
                        .into(holder.imgSeat);
                holder.t_id_name.setText(data.getParking_id());
                holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View v1 = mLayoutInflater.inflate(R.layout.activity_booked_already, null);
                        builder.setView(v1);
                        builder.setCancelable(true);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        }


        /*if(data.isCurrent_status() == false) {
            holder.ll.setBackgroundResource(R.drawable.wall);
            holder.status.setText("Status:Vacant");
        }
        else {
            holder.ll.setBackgroundResource(R.drawable.wall_1);
            holder.status.setText("Status:Occupied");
        }

        holder.id.setText("Parking Number:"+data.getParking_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Book_Parking_Activity.class);
                i.putExtra("Parking_id",data.getParking_id());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class Parking_Layout_ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgSeat;
        TextView t_id_name;
      //  ImageView imgSeatSelected;
        public Parking_Layout_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSeat =  itemView.findViewById(R.id.img_seat);
            t_id_name = itemView.findViewById(R.id.park_id_map);
        //    imgSeatSelected = itemView.findViewById(R.id.img_seat_selected);
        }
    }
    public void swap(ArrayList<AbstractItem> obj) {
        arr = obj;
        notifyDataSetChanged();
    }
}
