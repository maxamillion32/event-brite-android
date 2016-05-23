package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.VenueModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class CreateEventVenueActivity extends AppCompatActivity {
    private ApiCallMethods service;
    private VenueModel venueEntity;
    private Button createVenue;
    private EditText venueName;
    private EditText venueLat;
    private EditText venueLong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_venue);
        initViews();
        service = WebService.createServiceWithOauthHeader
                (ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);
    }

    private void initViews() {
        createVenue = (Button) findViewById(R.id.createVenue);
        createVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service
                        .createVenue(getVenueDetails())
                        .enqueue(new CustomCallback<VenueModel>() {
                            @Override
                            public void onSuccess(Response<VenueModel> response) {
                                venueEntity = response.body();
                                Utility.showToast("Venue created successfully");
                                finish();
                            }

                            @Override
                            public boolean showLoader() {
                                return true;
                            }
                        });
            }
        });

        venueName = (EditText) findViewById(R.id.venueName);
        venueLat = (EditText) findViewById(R.id.venueLat);
        venueLong = (EditText) findViewById(R.id.venueLong);
    }

    @Override
    public void finish() {
        if (null != venueEntity && !TextUtils.isEmpty(venueEntity.getId())) {
            setResult(RESULT_OK, new Intent()
                    .putExtra(Constants.IntentKeys.VENUE_ID, venueEntity.getId()));
        }
        super.finish();
    }

    private JsonObject getVenueDetails() {
        final JsonObject venue = new JsonObject();

        venue.addProperty("name", venueName.getText().toString());

        final JsonObject address = new JsonObject();
        address.addProperty("latitude", venueLat.getText().toString());
        address.addProperty("longitude", venueLong.getText().toString());

        venue.add("address", address);

        final JsonObject model = new JsonObject();
        model.add("venue", venue);

        return model;
    }

//    private void updateVenue() {
//
//        Log.d(getLocalClassName(), "updateVenue");
//
//        service
//                .updateVenue(eventsEntity.getVenue_id(), getVenusDetails())
//                .enqueue(new CustomCallback<ResponseBody>() {
//                    @Override
//                    public void onSuccess(Response<ResponseBody> response) {
//
//                    }
//                });
//    }
}