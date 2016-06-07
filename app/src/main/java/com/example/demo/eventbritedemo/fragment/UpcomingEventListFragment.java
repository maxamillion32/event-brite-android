package com.example.demo.eventbritedemo.fragment;

import android.support.annotation.NonNull;

import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.OrderModel;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UpcomingEventListFragment extends AbstractEventListFragment {
    private Call<OrderModel> ordersCall;

    @NonNull
    @Override
    protected String getEventType() {
        return "";
    }

    @Override
    protected void getEventList() {
        ordersCall = WebService.createServiceWithOauthHeader(ApiCallMethods
                .class).getOrders();

        ordersCall.enqueue(new CustomCallback<OrderModel>() {
            @Override
            public void onSuccess(Response<OrderModel> response) {
                displayOrderList(response.body().getOrders());
            }
        });
    }

    private void displayOrderList(List<OrderModel.OrdersEntity> orders) {
        final List<EventResponseModel.EventsEntity> eventsEntityList = new ArrayList<>();
        for (OrderModel.OrdersEntity order : orders) {
            eventsEntityList.add(order.getEvent());
        }
        displayEventList(eventsEntityList);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != ordersCall) {
            ordersCall.cancel();
        }
    }
}
