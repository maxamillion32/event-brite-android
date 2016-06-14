package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

public class CreateEventVenueActivity extends AppCompatActivity {
    private VenueModel venueEntity;
    private Button createVenue;
    private EditText venueName;
    private Call<VenueModel> createVenueCall;
    private SupportMapFragment mapFragment;
    private Marker marker;
    private ApiCallMethods service;
    private GoogleMap googleMap;
    private LatLng location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_venue);
        initViews();
    }

    private void initViews() {
        createVenue = (Button) findViewById(R.id.createVenue);
        createVenue.setOnClickListener(getOnCreateVenueClickListener());

        venueName = (EditText) findViewById(R.id.venueName);

        final Button searchName = (Button) findViewById(R.id.searchName);
        searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByName(venueName.getText().toString());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initMaps();

        service = WebService
                .createRetrofitService(ApiCallMethods.class,
                        "http://maps.googleapis.com/maps/api/");
    }

    @NonNull
    private View.OnClickListener getOnCreateVenueClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createVenueCall = WebService.createServiceWithOauthHeader(ApiCallMethods.class)
                        .createVenue(getVenueDetails());
                createVenueCall.enqueue(new CustomCallback<VenueModel>() {
                    @Override
                    public void onSuccess(Response<VenueModel> response) {
                        venueEntity = response.body();
                        Utility.showToast(getString(R.string.toast_venue_created));
                        finish();
                    }

                    @Override
                    public boolean shouldShowLoader() {
                        return true;
                    }
                });
            }
        };
    }

    private void initMaps() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(final GoogleMap googleMap) {
                CreateEventVenueActivity.this.googleMap = googleMap;
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        displayMarker(latLng);
                    }
                });

            }
        });
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
        address.addProperty("latitude", location.latitude);
        address.addProperty("longitude", location.longitude);

        venue.add("address", address);

        final JsonObject model = new JsonObject();
        model.add("venue", venue);

        return model;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != createVenueCall) {
            createVenueCall.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void searchByName(String name) {
        service
                .getLocationFor(name)
                .enqueue(new CustomCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        try {
                            final JsonObject locationJson = response.body()
                                    .getAsJsonArray("results").get(0)
                                    .getAsJsonObject().getAsJsonObject("geometry")
                                    .getAsJsonObject("location");

                            Log.d("dsgadrsg", locationJson.toString());

                            final LatLng location = new LatLng(
                                    Double.parseDouble(
                                            locationJson.getAsJsonPrimitive("lat").toString()
                                    ),
                                    Double.parseDouble(
                                            locationJson.getAsJsonPrimitive("lng").toString()
                                    )
                            );
                            displayOnMaps(location);

                        } catch (Exception e) {
                            e.printStackTrace();
                            onFailure(null, e);
                        }
                    }

                    @Override
                    public boolean shouldShowLoader() {
                        return true;
                    }
                });
    }

    private void displayOnMaps(final LatLng location) {
        displayMarker(location);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    private void displayMarker(LatLng location) {
        if (null != marker) {
            marker.remove();
        }

        this.location = location;

        marker = googleMap.addMarker(new MarkerOptions().position(location));
        getAddressFromLocation(location);
    }

    private void getAddressFromLocation(LatLng location) {
        service
                .getAddressFor(location.latitude + "," + location.longitude)
                .enqueue(new CustomCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        try {
                            final String address = response.body().getAsJsonArray("results")
                                    .get(0).getAsJsonObject()
                                    .getAsJsonPrimitive("formatted_address")
                                    .toString().replaceAll("\"", "");
                            venueName.setText(address);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
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