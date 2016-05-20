package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.TicketModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity implements
        Constants.ViewFlipperConstants {

    private String eventId;
    private EventResponseModel.EventsEntity eventEntity;
    private TextView eventName;
    private TextView eventDate;
    private Button btnAddTicket;
    private Button btnRegister;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        eventId = getIntent().getStringExtra(Constants.IntentKeys.EVENT_ID);
        if (TextUtils.isEmpty(eventId)) {
            Utility.showToast("ERROR!!!");
            finish();
        }

        setContentView(R.layout.activity_event_detail);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        eventName = (TextView) findViewById(R.id.eventName);
        eventDate = (TextView) findViewById(R.id.eventDate);
        btnAddTicket = (Button) findViewById(R.id.btnAddTicket);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnAddTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(EventDetailActivity.this,
                        CreateEventTicketActivity.class);
                intent.putExtra(Constants.IntentKeys.EVENT, eventEntity);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(eventEntity.getUrl()));
                startActivity(intent);
            }
        });

        getEventDetailsFor(eventId);
    }

    private void getEventDetailsFor(String eventId) {
        final ApiCallMethods service = WebService.createServiceWithOauthHeader
                (ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);

        viewFlipper.setDisplayedChild(LOADING);

        service
                .getEventWithId(eventId)
                .enqueue(new CustomCallback<EventResponseModel.EventsEntity>() {
                    @Override
                    public void onSuccess(Response<EventResponseModel.EventsEntity> response) {
                        eventEntity = response.body();
                        displayEventDetails();
                    }

                    @Override
                    public void onFailure(Call<EventResponseModel.EventsEntity> call, Throwable t) {
                        super.onFailure(call, t);
                        viewFlipper.setDisplayedChild(ERROR);
                    }
                });
    }

    private void displayEventDetails() {
        viewFlipper.setDisplayedChild(SUCCESS);
        eventName.setText(eventEntity.getName().getHtml());
        eventDate.setText(eventEntity.getStart() + " to " +
                eventEntity.getEnd());
        final List<TicketModel> ticket_classes = eventEntity.getTicket_classes();
        if (null == ticket_classes || ticket_classes.isEmpty()) {
            btnRegister.setVisibility(View.GONE);
            btnAddTicket.setVisibility(View.VISIBLE);
        } else {
            btnRegister.setVisibility(View.VISIBLE);
            btnAddTicket.setVisibility(View.GONE);
        }
    }
}
