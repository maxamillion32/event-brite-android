package com.example.demo.eventbritedemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.adapter.EventListAdapter;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractEventListFragment extends PagerFragment implements
        Constants.ViewFlipperConstants {

    private View mView;
    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;
    private Call<EventResponseModel> apiCall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_event_list, container, false);
        initViews();
        return mView;
    }

    private void initViews() {
        viewFlipper = (ViewFlipper) mView.findViewById(R.id.viewFlipper);

        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();
        getEventList();
    }

    private void getEventList() {
        viewFlipper.setDisplayedChild(LOADING);
        if (null == apiCall) {
            final ApiCallMethods retrofitService = WebService.createServiceWithOauthHeader
                    (ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);
            apiCall = retrofitService.getOwnedEventListWithStatus(getEventType());
        }
        apiCall.cancel();
        apiCall = apiCall.clone();
        apiCall.enqueue(new CustomCallback<EventResponseModel>() {

            @Override
            public void onSuccess(Response<EventResponseModel> response) {
                displayEventList(response.body());
            }

            @Override
            public void onFailure(Call<EventResponseModel> call, Throwable t) {
                viewFlipper.setDisplayedChild(ERROR);
            }
        });
    }

    @NonNull
    protected abstract String getEventType();

    private void displayEventList(EventResponseModel body) {
        viewFlipper.setDisplayedChild(SUCCESS);
        recyclerView.setAdapter(new EventListAdapter(getActivity(), body.getEvents()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != apiCall) {
            apiCall.cancel();
        }
    }

    @Override
    public void onPageSelected(PagerAdapter adapter, int position) {
        getEventList();
    }
}
