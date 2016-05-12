package com.example.demo.eventbritedemo.webservice;

import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.UserDetailModel;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    public static <T> T createServiceWithOauthHeader(final Class<T> clazz, final String endpoint) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                final Request original = chain.request();
                final Request request = original.newBuilder()
                        .header("Authorization",
                                "Bearer " + SharedPreferenceManager.getAccessToken())
                        .build();
                return chain.proceed(request);
            }
        });
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        final OkHttpClient client = httpClient.build();
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        final T service = restAdapter.create(clazz);
        return service;
    }

    public interface ApiCallMethods {
        String OAUTH_ENDPOINT = "https://www.eventbrite.com";
        String SERVICE_ENDPOINT = "https://www.eventbriteapi.com";

        @FormUrlEncoded
        @POST("/oauth/token")
        Call<AuthResponseModel> getAccessToken(@Field("code") String code,
                                               @Field("client_secret") String client_secret,
                                               @Field("client_id") String client_id,
                                               @Field("grant_type") String grant_type);

        @GET("/v3/events/search")
        Call<EventResponseModel> getEventListForId(@Query("user.id") String userId);

        @GET("/v3/users/me")
        Call<UserDetailModel> getUserDetails();
    }

}