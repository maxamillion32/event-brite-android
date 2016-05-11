package com.example.demo.eventbritedemo;

import android.app.Application;

public class ApplicationClass extends Application {
    private static ApplicationClass mInstance;

    public static synchronized ApplicationClass getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
