package com.example.demo.eventbritedemo.adapter;

import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.demo.eventbritedemo.fragment.PagerFragment;
import com.example.demo.eventbritedemo.model.PagerModel;

import java.util.List;

public class EventPagerAdapter extends FragmentStatePagerAdapter {

    private final List<PagerModel> modelList;

    public EventPagerAdapter(FragmentManager fm, List<PagerModel> modelList) {
        super(fm);
        this.modelList = modelList;
    }

    @Override
    public PagerFragment getItem(int position) {
        return modelList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return modelList.get(position).getTitle();
    }
}
