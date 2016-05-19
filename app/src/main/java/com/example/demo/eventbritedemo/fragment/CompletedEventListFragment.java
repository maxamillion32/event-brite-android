package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class CompletedEventListFragment extends AbstractEventListFragment {
    @NonNull
    @Override
    protected String getEventType() {
        return "completed";
    }
}
