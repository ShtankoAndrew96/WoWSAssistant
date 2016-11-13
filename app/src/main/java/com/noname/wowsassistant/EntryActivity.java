package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnLoginListener;

import org.json.JSONException;

import java.sql.Timestamp;

public class EntryActivity extends AppCompatActivity implements OnLoginListener {

    private int access_token;
    private String nickname;
    private int account_id;
    private Timestamp expires_at;
    Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        mApi=new Api(getApplicationContext());
    }

    public void goToLoginActvity(View view) throws JSONException {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void GoWithoutClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onLogin(int access_token, String nickname, int account_id, Timestamp experies_at) {
        this.access_token=access_token;
        this.nickname=nickname;
        this.account_id=account_id;
        this.expires_at=experies_at;
    }
}
