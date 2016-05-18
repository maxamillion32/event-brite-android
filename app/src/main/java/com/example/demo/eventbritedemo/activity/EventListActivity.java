package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Call;
import retrofit2.Response;

public class EventListActivity extends AppCompatActivity implements Constants.ViewFlipperConstants {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        initViews();
    }

    private void initViews() {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        final FloatingActionButton btnCreateEvent =
                (FloatingActionButton) findViewById(R.id.btnCreateEvent);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventListActivity.this, CreateNewEventActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getEventList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchEventActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getEventList() {
        viewFlipper.setDisplayedChild(LOADING);
        final WebService.ApiCallMethods retrofitService = WebService.createServiceWithOauthHeader
                (WebService.ApiCallMethods.class, WebService.ApiCallMethods.SERVICE_ENDPOINT);

        retrofitService.getOwnedEventListWithStatus("live")
                .enqueue(new WebService.CustomCallback<EventResponseModel>() {

                    @Override
                    public void success(Response<EventResponseModel> response) {
                        displayEventList(response.body());
                    }

                    @Override
                    public void onFailure(Call<EventResponseModel> call, Throwable t) {
                        viewFlipper.setDisplayedChild(ERROR);
                    }
                });
    }

    private void displayEventList(EventResponseModel body) {
        viewFlipper.setDisplayedChild(SUCCESS);
    }
}
