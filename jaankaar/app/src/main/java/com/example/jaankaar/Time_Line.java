package com.example.jaankaar;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class Time_Line extends Fragment {

    ArrayList<Time_dsg> obj = new ArrayList<>();
    Time_dsg d;
    Time_Database db;
    RcAdapter3 rcAdapter3;
    RecyclerView recyclerView;
    FloatingActionButton ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time__line, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv4);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Collections.reverse(obj);
        rcAdapter3 = new RcAdapter3(getContext(), obj);
        recyclerView.setAdapter(rcAdapter3);
        ad = (FloatingActionButton) view.findViewById(R.id.add_time);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SetDataContent.class);
                startActivity(i);

            }
        });
        updateList();
        return view;
    }

    private void updateList() {
        db = new Time_Database(getContext());
        Cursor res = db.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "There is a Error", Toast.LENGTH_SHORT).show();
        } else {
            obj.clear();
            while (res.moveToNext()) {
                Time_dsg a = new Time_dsg(res.getString(1), res.getString(2), res.getString(3),res.getString(4));
                obj.add(a);
            }
            rcAdapter3.swap(obj);
        }
    }

    @Override
    public void onResume() {
        updateList();
        super.onResume();
    }

}
