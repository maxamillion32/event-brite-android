package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.VenueModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreateNewEventActivity extends AppCompatActivity {

    private EditText eventName;
    private EditText eventCurrency;
    private Button btnCreateEvent;
    private EventResponseModel.EventsEntity eventsEntity;
    private VenueModel venueEntity;
    private Button venue;
    private ApiCallMethods service;
    private Button btnStartDate;
    private Button btnEndDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        initViews();
        service = WebService.createServiceWithOauthHeader(
                ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);
    }

    private void initViews() {
        eventName = (EditText) findViewById(R.id.eventName);
        eventCurrency = (EditText) findViewById(R.id.eventCurrency);
        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        venue = (Button) findViewById(R.id.venue);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEvent();
            }
        });

        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                createVenue();
            }
        });

        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);

        final View.OnClickListener datePickerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker((Button) v);
            }
        };

        btnStartDate.setOnClickListener(datePickerListener);
        btnEndDate.setOnClickListener(datePickerListener);
    }

    private void createVenue() {
        service
                .createVenue(getVenueDetails())
                .enqueue(new CustomCallback<VenueModel>() {
                    @Override
                    public void onSuccess(Response<VenueModel> response) {
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
                .enqueue(new CustomCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {

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

    private void createNewEvent() {

        service
                .createNewEvent(getEventDetails())
                .enqueue(new CustomCallback<EventResponseModel.EventsEntity>() {
                    @Override
                    public void onSuccess(Response<EventResponseModel.EventsEntity> response) {
                        Utility.showToast("Event Added Successfully");

                        gotoCreateEventTicketActivity(response.body());
                    }
                });
    }

    private void gotoCreateEventTicketActivity(EventResponseModel.EventsEntity eventsEntity) {
        final Intent intent = new Intent(this, CreateEventTicketActivity.class);
        intent.putExtra(Constants.IntentKeys.EVENT, eventsEntity);
        startActivity(intent);
        finish();
    }

    private JsonObject getEventDetails() {

        final JsonObject start = new JsonObject();
        final TimeZone timeZone = TimeZone.getDefault();
        start.addProperty("timezone", timeZone.getID());
        start.addProperty("utc", btnStartDate.getText().toString());

        final JsonObject end = new JsonObject();
        end.addProperty("timezone", timeZone.getID());
        end.addProperty("utc", btnEndDate.getText().toString());

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

    private void showDateTimePicker(final Button button) {
        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        dialogView.findViewById(R.id.btnSetDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
                final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);

                final Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(), 0);
                alertDialog.dismiss();
                button.setText(Utility.getFormattedDate(calendar));
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }
}
