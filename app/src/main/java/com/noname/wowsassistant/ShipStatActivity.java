package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnShipStatsListenner;

import java.nio.charset.Charset;

public class ShipStatActivity extends AppCompatActivity implements OnShipStatsListenner {

    private String id;
    private Api mapi;
    private TextView name;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_stat);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mapi=new Api(this);
        mapi.getShipStat(id);
        mapi.setOnShipStatsListenner(this);

    }

    @Override
    public void onStatShip(String namee, String textt) {
        name = (TextView) findViewById(R.id.stat_ship_name);
        text = (TextView) findViewById(R.id.stat_ship_text);
        byte[] n = namee.getBytes();
        String n1 = new String(n, Charset.forName("utf-8"));
        name.setText(n1);
        n = textt.getBytes();
        n1 = new String(n, Charset.forName("utf-8"));
        text.setText(n1);
    }
}
