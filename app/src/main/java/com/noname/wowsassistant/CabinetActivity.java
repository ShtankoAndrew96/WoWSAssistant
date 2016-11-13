package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.noname.wowslibrary.Api;


public class CabinetActivity extends AppCompatActivity {
    private String nickname;
    private int account_id;
    private String access_token;

    Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        Intent intent = getIntent();
        nickname=intent.getStringExtra("nickname");
        account_id=intent.getIntExtra("account_id",0);
        access_token=intent.getStringExtra("access_token");
        mApi=new Api(getApplicationContext());

    }

    public void logout(View view) {
        Intent intent = new Intent(this,EntryActivity.class);
        mApi.exit(access_token);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
