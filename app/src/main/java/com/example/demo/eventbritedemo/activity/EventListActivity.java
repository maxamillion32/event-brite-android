package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getEventList();
    }

    private void getEventList() {
        final WebService.ApiCallMethods retrofitService = WebService.createServiceWithOauthHeader
                (WebService.ApiCallMethods.class, WebService.ApiCallMethods.SERVICE_ENDPOINT);

        retrofitService.getEventListForId(SharedPreferenceManager.getUserId())
                .enqueue(new Callback<EventResponseModel>() {
                    @Override
                    public void onResponse(Call<EventResponseModel> call,
                                           Response<EventResponseModel> response) {

                    }

                    @Override
                    public void onFailure(Call<EventResponseModel> call, Throwable t) {

                    }
                });
    }
}
