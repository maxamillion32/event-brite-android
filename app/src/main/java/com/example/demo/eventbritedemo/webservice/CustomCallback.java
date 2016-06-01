package com.example.demo.eventbritedemo.webservice;

import android.support.annotation.NonNull;

import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.utility.Validation;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * callback methods with least validation
 *
 * @param <T>
 */
public abstract class CustomCallback<T> implements Callback<T> {

    public CustomCallback() {
        if (shouldShowLoader()) {
            Utility.showToastLong(getLoadingMessage());
        }
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        if (Validation.isValidResponse(response)) {
            onSuccess(response);
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Utility.showToast("ERROR!!!!!");
    }

    public abstract void onSuccess(retrofit2.Response<T> response);

    public boolean shouldShowLoader() {
        return false;
    }

    @NonNull
    public String getLoadingMessage() {
        return "Loading";
    }
}