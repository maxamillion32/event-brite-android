package com.example.demo.eventbritedemo.webservice;

import android.support.annotation.NonNull;

import com.example.demo.eventbritedemo.model.ApiErrorModel;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.utility.Validation;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;

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
            try {
                final Converter<ResponseBody, ApiErrorModel> converter =
                        WebService.getRetrofitInstance(ApiCallMethods.SERVICE_ENDPOINT)
                                .responseBodyConverter(ApiErrorModel.class, new Annotation[0]);

                final ApiErrorModel errorModel = converter.convert(response.errorBody());
                onFailure(errorModel);
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(call, null);
            }

        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Utility.showToast("ERROR!!!!!");
    }

    public abstract void onSuccess(retrofit2.Response<T> response);

    public void onFailure(ApiErrorModel error) {
        Utility.showToast(error.getError_description());
    }

    public boolean shouldShowLoader() {
        return false;
    }

    @NonNull
    public String getLoadingMessage() {
        return "Loading";
    }
}