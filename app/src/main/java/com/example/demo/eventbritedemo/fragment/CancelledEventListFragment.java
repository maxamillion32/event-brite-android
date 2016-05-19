package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class CancelledEventListFragment extends AbstractEventListFragment {
    @NonNull
    @Override
    protected String getEventType() {
        return "cancelled";
    }
}
