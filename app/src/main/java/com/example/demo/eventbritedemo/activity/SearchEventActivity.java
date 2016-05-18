package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Response;

public class SearchEventActivity extends AppCompatActivity {
    private WebService.ApiCallMethods service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final AppCompatEditText edtSearch = (AppCompatEditText) findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchEventWithName(s.toString());
            }
        });
    }

    private void searchEventWithName(String name) {
        if (null == service) {
            service = WebService.createServiceWithOauthHeader(
                    WebService.ApiCallMethods.class, WebService.ApiCallMethods.SERVICE_ENDPOINT);
        }
        service
                .searchEventWith(name)
                .enqueue(new WebService.CustomCallback<EventResponseModel>() {
                    @Override
                    public void success(Response<EventResponseModel> response) {

                    }
                });
    }
}
