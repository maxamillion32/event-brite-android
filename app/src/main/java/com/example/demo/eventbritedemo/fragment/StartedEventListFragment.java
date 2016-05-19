package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class StartedEventListFragment extends AbstractEventListFragment {
    @NonNull
    @Override
    protected String getEventType() {
        return "started";
    }
}
