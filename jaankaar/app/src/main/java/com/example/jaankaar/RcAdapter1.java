package com.example.jaankaar;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class RcAdapter1 extends RecyclerView.Adapter<RcAdapter1.MyViewHolder>
{
    Context context;
    ArrayList<DSGN> mylist;
    RcAdapter1(Context context, ArrayList<DSGN> mylist)
    {
        this.context=context;
        this.mylist=mylist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_for_notekeeper, viewGroup, false);
        MyViewHolder obj=new MyViewHolder(v);

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull RcAdapter1.MyViewHolder myViewHolder, int i)
    {
        // Uri savedImageURI = Uri.parse(myfile.getAbsolutePath());
       /* File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/AppStorage/"+".jpg");
        File myfile = new File(String.valueOf(dir));*/


        DSGN ds= mylist.get(i);
        myViewHolder.t1.setText(ds.getTitle());
       // myViewHolder.t2.setText(ds.getnumber());
       // Uri savedImageURI = Uri.parse(ds.getimage());

       /* GlideApp.with(context)
                .load(new File(ds.getimage()))
               .override(200, 200) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .into(myViewHolder.img);*/
      //  myViewHolder.img.setImageURI(savedImageURI);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1;
      public MyViewHolder(@NonNull View itemView)
      {
            super(itemView);
          t1 = (TextView) itemView.findViewById(R.id.note_des);

        }
    }
    public void swap(ArrayList<DSGN> obj) {
        mylist = obj;
        Collections.reverse(obj);
        notifyDataSetChanged();
    }
}
