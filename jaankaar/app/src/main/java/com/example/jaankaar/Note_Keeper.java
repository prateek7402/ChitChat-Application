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
public class Note_Keeper extends Fragment {

    FloatingActionButton floatingActionButton;
    ArrayList<DSGN> obj = new ArrayList<>();
    DSGN d;
    Database_for_notekeeper db;
    RcAdapter1 rcAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_note__keeper, container, false);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.add_note);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv3);
        Collections.reverse(obj);
        rcAdapter = new RcAdapter1(getContext(),obj);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rcAdapter);
        updateList();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),Note_Adder.class);
                startActivity(i);
            }
        });


        recyclerView.addOnItemTouchListener(new Recycler_view_click_listener_new(view.getContext(), recyclerView, new Recycler_view_click_listener_new.ClickListener() {
            @Override
            public void onClick(final View view, int position) {
                DSGN dataSetterGetter = obj.get(position);
                Intent intent = new Intent(getContext(), Note_Viewer.class);
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
        db = new Database_for_notekeeper(getContext());
        Cursor res = db.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "There is a Error", Toast.LENGTH_SHORT).show();
        } else {
            obj.clear();
            while (res.moveToNext()) {
                DSGN a = new DSGN(res.getString(1), res.getString(2));
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


}
