package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class CreateEventTicketActivity extends AppCompatActivity {
    private EditText totalTickets;
    private EditText ticketName;
    private EventResponseModel.EventsEntity eventsEntity;
    private Button btnPublishEvent;
    private ApiCallMethods service;
    private Button btnCreateTicket;
    private Call<ResponseBody> createTicketCall;
    private Call<ResponseBody> publishEventCall;
    private EditText ticketPrice;
    private Boolean isTicketFree = Boolean.TRUE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_ticket);
        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = WebService.createServiceWithOauthHeader(ApiCallMethods.class);
    }

    private void initViews() {
        eventsEntity = getIntent().getParcelableExtra(Constants.IntentKeys.EVENT);

        ticketName = (EditText) findViewById(R.id.ticketName);
        ticketPrice = (EditText) findViewById(R.id.ticketPrice);
        totalTickets = (EditText) findViewById(R.id.totalTickets);
        btnCreateTicket = (Button) findViewById(R.id.btnCreateTicket);
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

        final RadioGroup radioGrp = (RadioGroup) findViewById(R.id.radioGrp);
        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioBtnFree:
                        ticketPrice.setVisibility(View.GONE);
                        isTicketFree = Boolean.TRUE;
                        break;
                    case R.id.radioBtnPaid:
                        isTicketFree = Boolean.FALSE;
                        ticketPrice.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void createTicket() {
        if (null == eventsEntity) {
            return;
        }
        createTicketCall = service.createTicket(eventsEntity.getId(), getTicketDetails());
        createTicketCall.enqueue(new CustomCallback<ResponseBody>() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                btnPublishEvent.setVisibility(View.VISIBLE);
                btnCreateTicket.setVisibility(View.GONE);
                Utility.showToast("Ticket created for Event. Publish it to go Live");
            }

            @Override
            public boolean showLoader() {
                return true;
            }
        });
    }

    private JsonObject getTicketDetails() {
        final JsonObject model = new JsonObject();
        final JsonObject ticket = new JsonObject();
        ticket.addProperty("name", ticketName.getText().toString());
        ticket.addProperty("free", isTicketFree);

        if (!isTicketFree) {
            ticket.addProperty("cost", "USD," + ticketPrice.getText() + "00");
        }

        ticket.addProperty("quantity_total", Integer.parseInt(totalTickets.getText().toString()));
        model.add("ticket_class", ticket);
        return model;
    }

    private void publishEvent() {
        if (null == eventsEntity) {
            return;
        }
        publishEventCall = service
                .publishEvent(eventsEntity.getId());
        publishEventCall.enqueue(new CustomCallback<ResponseBody>() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                Utility.showToast("Event is now Live");
                finish();
            }

            @Override
            public boolean showLoader() {
                return true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != createTicketCall) {
            createTicketCall.cancel();
        }

        if (null != publishEventCall) {
            publishEventCall.cancel();
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
}

