package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

public class SavedEventListFragment extends AbstractEventListFragment {
    static final String DRAFT = "draft";
    @NonNull
    @Override
    protected String getEventType() {
        return DRAFT;
    }
}
