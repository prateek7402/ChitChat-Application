package com.example.theparkar.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.theparkar.Adapters.Adapter_Parking_List;
import com.example.theparkar.Data_Model.Datum;
import com.example.theparkar.History_Activity;
import com.example.theparkar.Main_Home_Activity;
import com.example.theparkar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LinearLayout b1,b2;
    RecyclerView recyclerView;
    Context context;
    String URL = "https://api.jsonbin.io/b/5d7f9562123f825262cb79e6";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        b1 = root.findViewById(R.id.to_the_main);
        b2 = root.findViewById(R.id.to_the_history);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Main_Home_Activity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  =  new Intent(getContext(), History_Activity.class);
                startActivity(i);
            }
        });
       /* recyclerView = root.findViewById(R.id.parking_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Datum[] users =  gson.fromJson(response, Datum[].class);
                recyclerView.setAdapter(new Adapter_Parking_List(getContext(),users));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something has went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
*/


       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = context;
    }

    public void ToTheMain(View v1)
    {

    }

    public void ToTheHistory(View v2)
    {

    }
}