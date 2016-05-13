package com.example.demo.eventbritedemo.webservice;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.demo.eventbritedemo.ApplicationClass;
import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.UserDetailModel;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;
import com.example.demo.eventbritedemo.utility.Validation;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    private static Retrofit getRetrofitInstance(String endpoint, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
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

    /**
     * callback methods with least validation
     *
     * @param <T>
     */
    public static abstract class CustomCallback<T> implements Callback<T> {

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

    public interface ApiCallMethods {
        String OAUTH_ENDPOINT = "https://www.eventbrite.com/";
        String SERVICE_ENDPOINT = "https://www.eventbriteapi.com/";

        @FormUrlEncoded
        @POST("oauth/token")
        Call<AuthResponseModel> getAccessToken(@Field("code") String code,
                                               @Field("client_secret") String client_secret,
                                               @Field("client_id") String client_id,
                                               @Field("grant_type") String grant_type);

        @GET("v3/events/search/")
        Call<EventResponseModel> getEventListForId(@Query("user.id") String userId);

        @GET("v3/users/me/")
        Call<UserDetailModel> getUserDetails();

        @POST("v3/events/")
        Call<EventResponseModel.EventsEntity> createNewEvent(@Body JsonObject model);

        @POST("v3/events/{id}/publish/")
        Call<ResponseBody> publishEvent(@Path("id") String id);

        @POST("v3/venues/{id}/")
        Call<ResponseBody> updateVenue(@Path("id") String id, @Body JsonObject body);

        @POST("v3/venues/")
        Call<ResponseBody> createVenue(@Body JsonObject body);
    }
}