package com.example.demo.eventbritedemo.webservice;

import android.support.annotation.NonNull;

import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    /**
     * basic rest adapter generator
     *
     * @param clazz
     * @param endpoint
     * @param <T>
     * @return
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endpoint) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getHttpLoggingInterceptor()).build();
        final Retrofit restAdapter = getRetrofitInstance(endpoint, client);
        return restAdapter.create(clazz);
    }

    /**
     * basic retrofit instance generator
     *
     * @param endpoint
     * @param client
     * @return
     */
    @NonNull
    public static Retrofit getRetrofitInstance(String endpoint, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    /**
     * basic retrofit instance generator
     *
     * @param endpoint
     * @return
     */
    @NonNull
    public static Retrofit getRetrofitInstance(String endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * for logging purpose
     *
     * @return
     */
    @NonNull
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /**
     * rest adapter with OAuth Header
     *
     * @param clazz
     * @param endpoint
     * @param <T>
     * @return
     */
    public static <T> T createServiceWithOauthHeader(final Class<T> clazz, final String endpoint) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(getAuthorizationHeader());
        httpClient.addInterceptor(getHttpLoggingInterceptor());
        final OkHttpClient client = httpClient.build();
        final Retrofit restAdapter = getRetrofitInstance(endpoint, client);
        return restAdapter.create(clazz);
    }

    public static <T> T createServiceWithOauthHeader(final Class<T> clazz) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(getAuthorizationHeader());
        httpClient.addInterceptor(getHttpLoggingInterceptor());
        final OkHttpClient client = httpClient.build();
        final Retrofit restAdapter = getRetrofitInstance(ApiCallMethods.SERVICE_ENDPOINT, client);
        return restAdapter.create(clazz);
    }

    /**
     * interceptor with OAuth header
     *
     * @return
     */
    @NonNull
    private static Interceptor getAuthorizationHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final Request request = original.newBuilder()
                        .header("Authorization",
                                "Bearer " + SharedPreferenceManager.getAccessToken())
                        .build();
                return chain.proceed(request);
            }
        };
    }
}