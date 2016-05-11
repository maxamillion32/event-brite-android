package com.example.demo.eventbritedemo.webservice;

import com.example.demo.eventbritedemo.model.AuthResponseModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class WebService {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endpoint) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        final T service = restAdapter.create(clazz);
        return service;
    }

    public interface AccessTokenService {
        String SERVICE_ENDPOINT = "https://www.eventbrite.com";

        @FormUrlEncoded
        @POST("/oauth/token")
        Call<AuthResponseModel> getAccessToken(@Field("code") String code,
                                               @Field("client_secret") String client_secret,
                                               @Field("client_id") String client_id,
                                               @Field("grant_type") String grant_type);
    }

}