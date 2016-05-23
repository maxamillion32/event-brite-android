package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewFlipper;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.model.UserDetailModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Constants.ViewFlipperConstants {

    private static final String REDIRECT_URI = "localhost";
    private static final String APP_KEY = "IOLSNXK2AFZLT7SHSA";
    private static final String CLIENT_SECRET =
            "BCDTCLO3HBACGIAU6H57CC43PC2LJIZDMFNSHGKFRCTIJS2BGN";
    private static final String OAUTH_URL = "https://www.eventbrite.com/oauth/authorize?" +
            "response_type=code&client_id=" + APP_KEY;
    private String ACCESS_CODE;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(SharedPreferenceManager.getAccessToken())) {
            showPage(OAUTH_URL);
        } else {
            //access_token already present
            gotoEventListActivity();
        }
    }

    protected void showPage(String url) {
        final WebView webView = (WebView) findViewById(R.id.webView);
        assert null != webView;
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                viewFlipper.setDisplayedChild(SUCCESS);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(getClass().getSimpleName(), "url is @@ " + url);
                if (url.contains(REDIRECT_URI)) {
                    ACCESS_CODE = url.substring(url.lastIndexOf("=") + 1, url.length());
                    authorizeUser();
                }
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.loadUrl(url);
    }

    private void authorizeUser() {
        viewFlipper.setDisplayedChild(LOADING);
        final ApiCallMethods service = WebService.createRetrofitService
                (ApiCallMethods.class, ApiCallMethods.OAUTH_ENDPOINT);

        service
                .getAccessToken(ACCESS_CODE, CLIENT_SECRET, APP_KEY, "authorization_code")
                .enqueue(new CustomCallback<AuthResponseModel>() {
                    @Override
                    public void onSuccess(Response<AuthResponseModel> response) {
                        SharedPreferenceManager.setAccessToken(response.body().getAccess_token());
                        getUserDetails();
                    }

                    @Override
                    public void onFailure(Call<AuthResponseModel> call, Throwable t) {
                        super.onFailure(call, t);
                        viewFlipper.setDisplayedChild(ERROR);
                    }
                });
    }

    private void getUserDetails() {
        viewFlipper.setDisplayedChild(LOADING);
        final ApiCallMethods retrofitService = WebService.createServiceWithOauthHeader
                (ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);

        retrofitService
                .getUserDetails()
                .enqueue(new CustomCallback<UserDetailModel>() {

                    @Override
                    public void onSuccess(Response<UserDetailModel> response) {
                        SharedPreferenceManager.setStringValue(
                                Constants.SharedPreferencesKeys.USER_ID,
                                response.body().getId()
                        );
                        gotoEventListActivity();
                    }

                    @Override
                    public void onFailure(Call<UserDetailModel> call, Throwable t) {
                        super.onFailure(call, t);
                        viewFlipper.setDisplayedChild(ERROR);
                    }
                });
    }

    private void gotoEventListActivity() {
        startActivity(new Intent(this, EventListActivity.class));
        finish();
    }
}
