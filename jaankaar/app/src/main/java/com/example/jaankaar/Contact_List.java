package com.example.jaankaar;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class Contact_List extends Fragment {
  /*  interface Transfer {
        void senddata(String name, String number, String image, int id, String email);
    }*/

    FloatingActionButton add, search;
    View view;
    ArrayList<DataSetterGetter> obj = new ArrayList<>();
    DataSetterGetter d;
    DataBaseHelper db;
    RcAdapter rcAdapter;
    RecyclerView recyclerView;
    AlertDialog alertDialog;
    login_state_checker login_state_checker;
    //  Transfer transfer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_contact__list, container, false);
        add = (FloatingActionButton) view.findViewById(R.id.add_contact);
        /*search = (FloatingActionButton) view.findViewById(R.id.search_contact);*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), add_contact.class);
                startActivity(i);
            }
        });

        login_state_checker = new login_state_checker(view.getContext());

        rcAdapter = new RcAdapter(view.getContext(), obj);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rcAdapter);
        // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //recyclerView.addItemDecoration(new recycler(16)); // 16px. In practice, you'll want to use getDimensionPixelSize

        // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //   recyclerView.addItemDecoration(new recycler(16, recycler.VERTICAL)); // 16px. In practice, you'll want to use getDimensionPixelSize


        updateList();
      /*  rcAdapter.setOnItemClickListener(new RcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DataSetterGetter dataSetterGetter = obj.get(position);
                Intent intent = new Intent(view.getContext(), Show_contact.class);
                Bundle extras = new Bundle();
                extras.putInt("Id", dataSetterGetter.getId());
                extras.putString("Name", dataSetterGetter.getname());
                extras.putString("Number", dataSetterGetter.getnumber());
                extras.putString("Image", dataSetterGetter.getimage());
                extras.putString("Email", dataSetterGetter.getEmail());
                startActivity(intent);
            }
        });*/
        recyclerView.addOnItemTouchListener(new Recycler_view_click_listener(view.getContext(), recyclerView, new Recycler_view_click_listener.ClickListener() {
            @Override
            public void onClick(final View view, int position) {
                  DataSetterGetter dataSetterGetter = obj.get(position);
                  Intent intent = new Intent(getContext(), Call_Details.class);
                  intent.putExtra("contact", dataSetterGetter);
                  startActivity(intent);
                updateList();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }

    private void updateList() {
        db = new DataBaseHelper(view.getContext());
        Cursor res = db.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(view.getContext(), "There is a Error", Toast.LENGTH_SHORT).show();
        } else {
            obj.clear();
            while (res.moveToNext()) {
                DataSetterGetter a = new DataSetterGetter(res.getString(1), res.getString(2), res.getString(3), res.getInt(1), res.getString(4));
                obj.add(a);
            }
            rcAdapter.swap(obj);
        }
    }

    @Override
    public void onResume() {
        updateList();
        super.onResume();
    }

 /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        transfer = (Transfer)context;
    }*/
}


