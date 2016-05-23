package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Response;

public class CreateNewEventActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 51;
    private EditText eventName;
    private EditText eventCurrency;
    private Button btnCreateEvent;
    private EventResponseModel.EventsEntity eventsEntity;
    private String venueId;
    private Button venue;
    private Call<EventResponseModel.EventsEntity> createEventCall;
    private Button btnStartDate;
    private Button btnEndDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        initViews();
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
                createVenue();
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
        startActivityForResult(new Intent(this, CreateEventVenueActivity.class), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode && REQUEST_CODE == requestCode && null != data) {
            venueId = data.getStringExtra(Constants.IntentKeys.VENUE_ID);
        }
    }

    private void createNewEvent() {

        createEventCall = WebService.createServiceWithOauthHeader(
                ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT)
                .createNewEvent(getEventDetails());

        createEventCall
                .enqueue(new CustomCallback<EventResponseModel.EventsEntity>() {
                    @Override
                    public void onSuccess(Response<EventResponseModel.EventsEntity> response) {
                        Utility.showToast("Event Created Successfully");
                        gotoCreateEventTicketActivity(response.body());
                    }

                    @Override
                    public boolean showLoader() {
                        return true;
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
        if (!TextUtils.isEmpty(venueId)) {
            event.addProperty("venue_id", venueId);
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

    @Override
    protected void onStop() {
        super.onStop();
        if (null != createEventCall) {
            createEventCall.cancel();
        }
    }
}
