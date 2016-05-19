package com.example.demo.eventbritedemo.webservice;

import android.widget.Toast;

import com.example.demo.eventbritedemo.ApplicationClass;
import com.example.demo.eventbritedemo.utility.Validation;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * callback methods with least validation
 *
 * @param <T>
 */
public abstract class CustomCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        if (Validation.isValidResponse(response)) {
            success(response);
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Toast.makeText(ApplicationClass.getInstance(), "ERROR!!!!!", Toast.LENGTH_LONG).show();
    }

    public abstract void success(retrofit2.Response<T> response);
}