package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class EndedEventListFragment extends AbstractEventListFragment {
    @NonNull
    @Override
    protected String getEventType() {
        return "ended";
    }
}
