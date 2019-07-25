package com.example.jaankaar;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Adapter extends BaseAdapter {

    private ArrayList<DataSG> d = new ArrayList<>();
    Context context;


    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return d == null ? 0 : d.size();
    }

    @Override
    public Object getItem(int position) {
        return d.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.log_resource_file, parent, false);
        }
        DataSG currentItem = (DataSG) getItem(position);
        TextView t1 = (TextView) convertView.findViewById(R.id.l_name);
        TextView t2 = (TextView) convertView.findViewById(R.id.l_number);
        TextView t3 = (TextView)convertView.findViewById(R.id.l_time);
        de.hdodenhof.circleimageview.CircleImageView img = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.l_img);


        Uri savedImageURI = Uri.parse(currentItem.getImg_new());


        GlideApp.with(context)
                .load(new File(currentItem.getImg_new()))
                .override(200, 200) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .into(img);
        t1.setText(currentItem.getName());
        t2.setText(currentItem.getPhone());
        t3.setText(currentItem.getTime());
        return convertView;
    }

    public void swap(ArrayList<DataSG> obj) {
        d = obj;
        Collections.reverse(obj);
        notifyDataSetChanged();
    }
}
