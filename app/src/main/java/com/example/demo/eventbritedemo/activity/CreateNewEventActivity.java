package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.demo.eventbritedemo.utility.Validation;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNewEvent();
    }

    private void createNewEvent() {
        final WebService.ApiCallMethods service = WebService
                .createServiceWithOauthHeader(WebService.ApiCallMethods.class,
                        WebService.ApiCallMethods.SERVICE_ENDPOINT);

        service
                .createNewEvent(getEventDetails())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (Validation.isValidResponse(response)) {
                            Toast.makeText(getBaseContext(), "Event Added Successfully",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            onFailure(call, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
    }

    private JsonObject getEventDetails() {

        final JsonObject start = new JsonObject();
        start.addProperty("timezone", "Asia/Kolkata");
        start.addProperty("utc", "2016-06-21T14:30:00Z");

        final JsonObject end = new JsonObject();
        end.addProperty("timezone", "Asia/Kolkata");
        end.addProperty("utc", "2016-06-27T14:30:00Z");

        final JsonObject name = new JsonObject();
        name.addProperty("html", "Wenonise6186");

        final JsonObject event = new JsonObject();
        event.add("start", start);
        event.add("end", end);
        event.add("name", name);
        event.addProperty("currency", "USD");

        final JsonObject model = new JsonObject();
        model.add("event", event);

        return model;
    }
}
