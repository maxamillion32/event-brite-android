package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.adapter.EventListAdapter;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchEventActivity extends AppCompatActivity {
    private ApiCallMethods service;
    private RecyclerView recyclerView;
    private EventListAdapter adapter;
    private List<EventResponseModel.EventsEntity> eventsEntityList;
    private Call<EventResponseModel> apiCall;
    private ProgressBar progressLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    private void initViews() {
        final EditText edtSearch = (EditText) findViewById(R.id.edtSearch);
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressLoader = (ProgressBar) findViewById(R.id.progressLoader);
    }

    private void searchEventWithName(String name) {
        progressLoader.setVisibility(View.VISIBLE);
        if (null == service) {
            service = WebService.createServiceWithOauthHeader(ApiCallMethods.class);
        }
        if (null != apiCall) {
            apiCall.cancel();
        }
        apiCall = service.searchEventWith(name);
        apiCall.enqueue(new CustomCallback<EventResponseModel>() {
            @Override
            public void onSuccess(Response<EventResponseModel> response) {
                displayList(response.body());
            }

            @Override
            public void onFailure(Call<EventResponseModel> call, Throwable t) {
            }
        });
    }

    private void displayList(EventResponseModel response) {
        progressLoader.setVisibility(View.INVISIBLE);
        if (null == eventsEntityList) {
            eventsEntityList = new ArrayList<>(response.getEvents());
        } else {
            eventsEntityList.clear();
            eventsEntityList.addAll(response.getEvents());
        }
        if (null == adapter) {
            adapter = new EventListAdapter(this, eventsEntityList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (null != apiCall) {
            apiCall.cancel();
        }
    }
}
