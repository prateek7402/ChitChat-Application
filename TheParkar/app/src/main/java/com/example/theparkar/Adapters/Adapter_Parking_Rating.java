package com.example.theparkar.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theparkar.Data_Model.Data_Model_For_Rating;
import com.example.theparkar.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_Parking_Rating extends RecyclerView.Adapter<Adapter_Parking_Rating.Rating_View_Holder> {

    Context context;
    ArrayList<Data_Model_For_Rating> arr;

    public Adapter_Parking_Rating(Context context, ArrayList<Data_Model_For_Rating> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public Rating_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rating_display_layout, parent, false);
        return new Rating_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rating_View_Holder holder, int position) {
         Data_Model_For_Rating object = arr.get(position);
         holder.name.setText(object.getName());
         holder.ratingBar.setRating(Float.parseFloat(object.getRating()));
         holder.date.setText(object.getTime());
         holder.description.setText(object.getDescription());
         holder.rating.setText(object.getRating());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class Rating_View_Holder extends RecyclerView.ViewHolder
    {
        TextView name,description,date,rating;
        de.hdodenhof.circleimageview.CircleImageView User_Image;
        RatingBar ratingBar;
        public Rating_View_Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rating_name);
            description = itemView.findViewById(R.id.rating_description);
            date = itemView.findViewById(R.id.rating_date);
            User_Image = itemView.findViewById(R.id.rating_image);
            ratingBar = itemView.findViewById(R.id.rating_rating_bar);
            rating = itemView.findViewById(R.id.rating_numerics);
        }
    }

    public void swap(ArrayList<Data_Model_For_Rating> obj) {
        arr = obj;
        notifyDataSetChanged();
    }
}
