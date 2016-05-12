package com.example.demo.eventbritedemo.utility;

import retrofit2.Response;

/**
 * Created by webonise on 12/05/16.
 */
public class Validation {

    public static boolean isValidResponse(Response response) {
        return null != response && response.isSuccessful() && null != response.body();
    }

}
