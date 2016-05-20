package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreateEventTicketActivity extends AppCompatActivity {
    private EditText totalTickets;
    private EditText ticketName;
    private EventResponseModel.EventsEntity eventsEntity;
    private Button btnPublishEvent;
    private ApiCallMethods service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_ticket);
        initViews();
        service = WebService.createServiceWithOauthHeader
                (ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);
    }

    private void initViews() {
        eventsEntity = getIntent().getParcelableExtra(Constants.IntentKeys.EVENT);

        ticketName = (EditText) findViewById(R.id.ticketName);
        totalTickets = (EditText) findViewById(R.id.totalTickets);
        final Button btnCreateTicket = (Button) findViewById(R.id.btnCreateTicket);
        btnPublishEvent = (Button) findViewById(R.id.btnPublishEvent);
        btnCreateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTicket();
            }
        });
        btnPublishEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishEvent();
            }
        });
    }

    private void createTicket() {
        if (null == eventsEntity) {
            return;
        }
        service
                .createTicket(eventsEntity.getId(), getTicketDetails())
                .enqueue(new CustomCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {
                        btnPublishEvent.setVisibility(View.VISIBLE);
                    }
                });
    }

    private JsonObject getTicketDetails() {
        final JsonObject model = new JsonObject();
        final JsonObject ticket = new JsonObject();
        ticket.addProperty("name", ticketName.getText().toString());
        ticket.addProperty("free", Boolean.TRUE);
        ticket.addProperty("quantity_total", Integer.parseInt(totalTickets.getText().toString()));
        model.add("ticket_class", ticket);
        return model;
    }

    private void publishEvent() {
        if (null == eventsEntity) {
            return;
        }
        service
                .publishEvent(eventsEntity.getId())
                .enqueue(new CustomCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {
                        Utility.showToast("Event now Live");
                        finish();
                    }
                });
    }
}

