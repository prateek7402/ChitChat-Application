package com.example.theparkar.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.theparkar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
  //  FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    String url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=163c8921bbaa4a4c93732de3aa236821";
    ArrayList<Data> dt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dt = new ArrayList<>();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Data data = gson.fromJson(response,Data.class);
                recyclerView.setAdapter(new adapter_main(getContext(),data));
               /* StandardResolution[] standardResolutions =  gson.fromJson(String.valueOf(response.startsWith("StandardResolution")), StandardResolution[].class);
                Likes[] likes = gson.fromJson(response,Likes[].class);
                Comments[] comments = gson.fromJson(response,Comments[].class);
                Caption[] captions = gson.fromJson(response,Caption[].class);*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something has went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

        return root;
    }
}