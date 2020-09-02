package com.example.wasera;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter_class extends FragmentPagerAdapter {

    public Adapter_class(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    private Context myContext;
    int totalTabs;

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BlankFragment homeFragment = new BlankFragment();
                return homeFragment;
            case 1:
                BlankFragment2 sportFragment = new BlankFragment2();
                return sportFragment;
            default:
                return null;
        }
        }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
