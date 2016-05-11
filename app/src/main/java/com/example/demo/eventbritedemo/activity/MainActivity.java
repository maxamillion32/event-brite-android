package com.example.demo.eventbritedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.AuthResponseModel;
import com.example.demo.eventbritedemo.utility.SharedPreferenceManager;
import com.example.demo.eventbritedemo.webservice.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        showPage(OAUTH_URL);
    }

    protected void showPage(String url) {
        final WebView webView = (WebView) findViewById(R.id.webView);
        assert null != webView;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
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
        final WebService.AccessTokenService service = WebService.createRetrofitService
                (WebService.AccessTokenService.class, WebService.AccessTokenService
                        .SERVICE_ENDPOINT);

        service.getAccessToken(ACCESS_CODE, CLIENT_SECRET, APP_KEY, "authorization_code")
                .enqueue(new Callback<AuthResponseModel>() {
                    @Override
                    public void onResponse(Call<AuthResponseModel> call,
                                           Response<AuthResponseModel> response) {
                        if (response.isSuccessful() && null != response.body()) {
                            SharedPreferenceManager.setAccessToken(response.body()
                                    .getAccess_token());
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponseModel> call, Throwable t) {
                        Log.d("TTT", t.toString());
                    }
                });
    }
}
