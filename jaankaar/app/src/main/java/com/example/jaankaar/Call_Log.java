package com.example.jaankaar;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Call_Log extends Fragment {

    ListView listView;
    ArrayList<DataSG> obj = new ArrayList<>();
    DataSG d;
    Call_Log_Database db;
    Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call__log, container, false);
        listView = (ListView) view.findViewById(R.id.lv2);
        adapter = new Adapter(getContext());
        listView.setAdapter(adapter);
        updateList();
        return view;
    }

    private void updateList() {
        db = new Call_Log_Database(getContext());
        Cursor res = db.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "There is a Error", Toast.LENGTH_SHORT).show();
        } else {
            obj.clear();
            while (res.moveToNext()) {
                DataSG a = new DataSG(res.getString(1),res.getString(2),res.getString(3),res.getString(4));
                obj.add(a);
            }
            adapter.swap(obj);
        }

    }

}
