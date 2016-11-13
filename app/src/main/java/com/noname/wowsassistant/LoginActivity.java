package com.noname.wowsassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements Runnable {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        LoginWebViewClient loginWebViewClient = new LoginWebViewClient(this);
        webView.setWebViewClient(loginWebViewClient);
        webView.loadUrl("https://api.worldoftanks.ru/wot/auth/login/?application_id=53171588f86f7958d53b4b8cf6440241");
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&this.webView.canGoBack()){
            this.webView.goBack();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (webView.getUrl().equals("https://api.worldoftanks.ru/wot/auth/login/?application_id=53171588f86f7958d53b4b8cf6440241")){

        }
        finish();
    }

    private class LoginWebViewClient extends WebViewClient{

        private Activity activity = null;

        private LoginWebViewClient(Activity activity){
            this.activity=activity;
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains("https://api.worldoftanks.ru/wot/blank/?&status=ok")){
                Intent intent = new Intent(activity,MainActivity.class);
                try {
                    URL urll = new URL(url);
                    URI uri = urll.toURI();
                    String params=uri.getQuery();
                    intent.putExtra("params",params);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                activity.startActivity(intent);
                activity.finish();
            }
            return false;
        }


    }
}
