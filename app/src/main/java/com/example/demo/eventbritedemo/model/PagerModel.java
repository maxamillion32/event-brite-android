package com.example.demo.eventbritedemo.model;

import com.example.demo.eventbritedemo.fragment.PagerFragment;

public class PagerModel {
    private PagerFragment fragment;
    private String title;

    public PagerModel(PagerFragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public PagerFragment getFragment() {
        return fragment;
    }

    public void setFragment(PagerFragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
