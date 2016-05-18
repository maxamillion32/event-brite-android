package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.VenueModel;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreateNewEventActivity extends AppCompatActivity {

    private EditText eventName;
    private EditText eventCurrency;
    private Button btnCreateEvent;
    private Button btnPublishEvent;
    private EventResponseModel.EventsEntity eventsEntity;
    private VenueModel venueEntity;
    private Button venue;
    private WebService.ApiCallMethods service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        initViews();
        service = WebService.createServiceWithOauthHeader(
                WebService.ApiCallMethods.class,
                WebService.ApiCallMethods.SERVICE_ENDPOINT);
    }

    private void initViews() {
        eventName = (EditText) findViewById(R.id.eventName);
        eventCurrency = (EditText) findViewById(R.id.eventCurrency);
        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        btnPublishEvent = (Button) findViewById(R.id.btnPublishEvent);
        venue = (Button) findViewById(R.id.venue);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEvent();
            }
        });

        btnPublishEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishEvent();
            }
        });

        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                createVenue();
            }
        });
    }

    private void createVenue() {
        service
                .createVenue(getVenueDetails())
                .enqueue(new WebService.CustomCallback<VenueModel>() {
                    @Override
                    public void success(Response<VenueModel> response) {
                        venueEntity = response.body();
                    }
                });
    }

    private JsonObject getVenueDetails() {
        final JsonObject venue = new JsonObject();

        venue.addProperty("name", "United States");

        final JsonObject address = new JsonObject();
        address.addProperty("latitude", "40.690302");
        address.addProperty("longitude", "-73.950266");

        venue.add("address", address);

        final JsonObject model = new JsonObject();
        model.add("venue", venue);

        return model;
    }

    private void updateVenue() {

        Log.d(getLocalClassName(), "updateVenue");

        service
                .updateVenue(eventsEntity.getVenue_id(), getVenusDetails())
                .enqueue(new WebService.CustomCallback<ResponseBody>() {
                    @Override
                    public void success(Response<ResponseBody> response) {

                    }
                });
    }

    private JsonObject getVenusDetails() {
        final JsonObject venue = new JsonObject();
        venue.addProperty("name", "USA");

        final JsonObject model = new JsonObject();
        model.add("venue", venue);

        return model;
    }

    private void publishEvent() {

        service
                .publishEvent(eventsEntity.getId())
                .enqueue(new WebService.CustomCallback<ResponseBody>() {
                    @Override
                    public void success(Response<ResponseBody> response) {

                    }
                });
    }

    private void createNewEvent() {

        service
                .createNewEvent(getEventDetails())
                .enqueue(new WebService.CustomCallback<EventResponseModel.EventsEntity>() {
                    @Override
                    public void success(Response<EventResponseModel.EventsEntity> response) {
                        Toast.makeText(getBaseContext(), "Event Added Successfully",
                                Toast.LENGTH_SHORT).show();
                        btnPublishEvent.setVisibility(View.VISIBLE);
                        eventsEntity = response.body();
                        createTicket();
                    }
                });
    }

    private void createTicket() {
        service
                .createTicket(eventsEntity.getId(), getTicketDetails())
                .enqueue(new WebService.CustomCallback<ResponseBody>() {
                    @Override
                    public void success(Response<ResponseBody> response) {

                    }
                });
    }

    private JsonObject getTicketDetails() {
        final JsonObject model = new JsonObject();
        final JsonObject ticket = new JsonObject();
        ticket.addProperty("name", "Free");
        ticket.addProperty("free", Boolean.TRUE);
        ticket.addProperty("quantity_total", 100);
        model.add("ticket_class", ticket);
        return model;
    }

    private JsonObject getEventDetails() {

        final JsonObject start = new JsonObject();
        start.addProperty("timezone", "Pacific/Honolulu");
        start.addProperty("utc", "2016-06-21T14:30:00Z");

        final JsonObject end = new JsonObject();
        end.addProperty("timezone", "Pacific/Honolulu");
        end.addProperty("utc", "2016-06-27T14:30:00Z");

        final JsonObject name = new JsonObject();
        name.addProperty("html", eventName.getText().toString().trim());

        final JsonObject event = new JsonObject();
        event.add("start", start);
        event.add("end", end);
        event.add("name", name);
        event.addProperty("currency", eventCurrency.getText().toString().trim());
        if (null != eventsEntity) {
            event.addProperty("venue_id", venueEntity.getId());
        }

        final JsonObject model = new JsonObject();
        model.add("event", event);

        return model;
    }
}
