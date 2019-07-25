package com.example.chitchat;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class chat_rec_women extends RecyclerView.ViewHolder  {



    TextView leftText,rightText;

    public chat_rec_women(View itemView){
        super(itemView);

        leftText = (TextView)itemView.findViewById(R.id.leftText_women);
        rightText = (TextView)itemView.findViewById(R.id.rightText_women);


    }
}
