package com.example.demo.eventbritedemo.utility;

import retrofit2.Response;

public class Validation {

    public static boolean isValidResponse(Response response) {
        return null != response && response.isSuccessful() && null != response.body();
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
