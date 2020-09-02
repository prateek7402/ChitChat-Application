package com.example.wasera.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.wasera.Adapter_class;
import com.example.wasera.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private DashboardViewModel dashboardViewModel;
    TabLayout tabLayout;
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //   final TextView textView = root.findViewById(R.id.text_dashboard);
        /*dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        tabLayout = root.findViewById(R.id.tab_l);
        viewPager = root.findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Messages"));
        tabLayout.addTab(tabLayout.newTab().setText("Notifications"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        Adapter_class adapter_class = new Adapter_class(getFragmentManager(), 2);

        viewPager.setAdapter(adapter_class);

        tabLayout.setOnTabSelectedListener(this);


        return root;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}