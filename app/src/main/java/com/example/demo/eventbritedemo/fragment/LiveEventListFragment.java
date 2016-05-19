package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class LiveEventListFragment extends AbstractEventListFragment {
    @NonNull
    @Override
    protected String getEventType() {
        return "live";
    }
}
