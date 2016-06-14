package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class EndedEventListFragment extends AbstractEventListFragment {
    final static String ENDED = "ended";
    @NonNull
    @Override
    protected String getEventType() {
        return ENDED;
    }
}
