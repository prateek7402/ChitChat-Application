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

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.MyViewHolder>
{
    Context context;
    ArrayList<DataSetterGetter> mylist;
    RcAdapter(Context context, ArrayList<DataSetterGetter> mylist)
    {
        this.context=context;
        this.mylist=mylist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_for_data, viewGroup, false);
        MyViewHolder obj=new MyViewHolder(v);

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull RcAdapter.MyViewHolder myViewHolder, int i)
    {
        // Uri savedImageURI = Uri.parse(myfile.getAbsolutePath());
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/AppStorage/"+".jpg");
        File myfile = new File(String.valueOf(dir));


        DataSetterGetter ds= mylist.get(i);
        myViewHolder.t1.setText(ds.getname());
        myViewHolder.t2.setText(ds.getnumber());
        Uri savedImageURI = Uri.parse(ds.getimage());

        GlideApp.with(context)
                .load(new File(ds.getimage()))
               .override(200, 200) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .into(myViewHolder.img);
      //  myViewHolder.img.setImageURI(savedImageURI);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        de.hdodenhof.circleimageview.CircleImageView img;
      public MyViewHolder(@NonNull View itemView)
      {
            super(itemView);
          t1 = (TextView) itemView.findViewById(R.id.c_name);
          t2 = (TextView) itemView.findViewById(R.id.c_number);
          img = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.c_img);

        }
    }
    public void swap(ArrayList<DataSetterGetter> obj) {
        mylist = obj;
        Collections.reverse(obj);
        notifyDataSetChanged();
    }
}
