package com.example.jaankaar;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class RcAdapter3 extends RecyclerView.Adapter<RcAdapter3.MyViewHolder>
{
    Context context;
    ArrayList<Time_dsg> mylist;
    RcAdapter3(Context context, ArrayList<Time_dsg> mylist)
    {
        this.context=context;
        this.mylist=mylist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View v = LayoutInflater.from(context).inflate(R.layout.container_for_layout, viewGroup, false);
        MyViewHolder obj=new MyViewHolder(v);

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull RcAdapter3.MyViewHolder myViewHolder, int i)
    {
        // Uri savedImageURI = Uri.parse(myfile.getAbsolutePath());
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/AppStorage/"+".jpg");
        File myfile = new File(String.valueOf(dir));


        Time_dsg ds= mylist.get(i);
        myViewHolder.t1.setText(ds.getTitle());
        myViewHolder.t2.setText(ds.getDescription());
        myViewHolder.t3.setText(ds.getDate());
        Uri savedImageURI = Uri.parse(ds.getPicAdd());

        GlideApp.with(context)
                .load(new File(ds.getPicAdd()))
               .override(1000, 2000) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .into(myViewHolder.img);
      //  myViewHolder.img.setImageURI(savedImageURI);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;
        ImageView img;
      public MyViewHolder(@NonNull View itemView)
      {
            super(itemView);
          t1 = (TextView) itemView.findViewById(R.id.title_display);
          t2 = (TextView) itemView.findViewById(R.id.description_display);
          t3 = (TextView) itemView.findViewById(R.id.date_display);
          img = (ImageView) itemView.findViewById(R.id.imagemain_display);

        }
    }
    public void swap(ArrayList<Time_dsg> obj) {
        mylist = obj;
        Collections.reverse(obj);
        notifyDataSetChanged();
    }


}
