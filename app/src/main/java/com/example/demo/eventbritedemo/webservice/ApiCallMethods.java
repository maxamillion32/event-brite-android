package com.example.demo.eventbritedemo.webservice;

import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.UserDetailModel;
import com.example.demo.eventbritedemo.model.VenueModel;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("v3/users/me/owned_events/")
    Call<EventResponseModel> getOwnedEventList();

    @GET("v3/users/me/owned_events/")
    Call<EventResponseModel> getOwnedEventListWithStatus(@Query("status") String status);

    @GET("v3/users/me/")
    Call<UserDetailModel> getUserDetails();

    @POST("v3/events/?expand=ticket_classes")
    Call<EventResponseModel.EventsEntity> createNewEvent(@Body JsonObject model);

    @POST("v3/events/{id}/publish/")
    Call<ResponseBody> publishEvent(@Path("id") String id);

    @POST("v3/venues/{id}/")
    Call<ResponseBody> updateVenue(@Path("id") String id, @Body JsonObject body);

    @POST("v3/venues/")
    Call<VenueModel> createVenue(@Body JsonObject body);

    @POST("v3/events/{id}/ticket_classes/")
    Call<ResponseBody> createTicket(@Path("id") String id, @Body JsonObject body);

    @GET("v3/events/search/")
    Call<EventResponseModel> searchEventWith(@Query("q") String stringVal);

    @GET("v3/events/{id}/?expand=ticket_classes")
    Call<EventResponseModel.EventsEntity> getEventWithId(@Path("id") String eventId);

    @GET("v3/media/upload/")
    Call<JsonObject> getImageUpload(@Query("type") String imageType);

    @Multipart
    @POST("")
    Call<ResponseBody> uploadImage(@Part("description") RequestBody description,
                                   @Part MultipartBody.Part file);
}
