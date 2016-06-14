package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class StartedEventListFragment extends AbstractEventListFragment {
    static final String STARTED = "started";
    @NonNull
    @Override
    protected String getEventType() {
        return STARTED;
    }
}
