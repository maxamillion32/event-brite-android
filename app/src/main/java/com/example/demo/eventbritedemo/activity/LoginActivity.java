package com.example.demo.eventbritedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.model.UserDetailModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String REDIRECT_URI = "localhost";
    private static final String APP_KEY = "IOLSNXK2AFZLT7SHSA";
    private static final String CLIENT_SECRET =
            "BCDTCLO3HBACGIAU6H57CC43PC2LJIZDMFNSHGKFRCTIJS2BGN";
    private static final String OAUTH_URL = "https://www.eventbrite.com/oauth/authorize?" +
            "response_type=code&client_id=" + APP_KEY;
    private String ACCESS_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        final WebService.ApiCallMethods service = WebService.createRetrofitService
                (WebService.ApiCallMethods.class, WebService.ApiCallMethods.OAUTH_ENDPOINT);

        service
                .getAccessToken(ACCESS_CODE, CLIENT_SECRET, APP_KEY, "authorization_code")
                .enqueue(new WebService.CustomCallback<AuthResponseModel>() {
                    @Override
                    public void success(Response<AuthResponseModel> response) {
                        SharedPreferenceManager.setAccessToken(response.body()
                                .getAccess_token());
                        getUserDetails();
                    }
                });
    }

    private void getUserDetails() {

        final WebService.ApiCallMethods retrofitService = WebService.createServiceWithOauthHeader
                (WebService.ApiCallMethods.class, WebService.ApiCallMethods.SERVICE_ENDPOINT);

        retrofitService
                .getUserDetails()
                .enqueue(new WebService.CustomCallback<UserDetailModel>() {

                    @Override
                    public void success(Response<UserDetailModel> response) {
                        SharedPreferenceManager.setStringValue(
                                Constants.SharedPreferencesKeys.USER_ID,
                                response.body().getId()
                        );
                        gotoEventListActivity();
                    }
                });
    }

    private void gotoEventListActivity() {
        startActivity(new Intent(this, EventListActivity.class));
        finish();
    }

}
