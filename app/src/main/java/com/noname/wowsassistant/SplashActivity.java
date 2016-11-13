package com.noname.wowsassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class SplashActivity extends Activity implements Runnable {

    private static final int DELAY = 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, DELAY);


    }


    @Override
    public void run() {
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
        finish();

    }
}
