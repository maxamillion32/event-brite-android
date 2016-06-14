package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class LiveEventListFragment extends AbstractEventListFragment {
    final static String LIVE = "live";
    @NonNull
    @Override
    protected String getEventType() {
        return LIVE;
    }
}
