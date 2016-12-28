package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnInfoGetListener;
import com.noname.wowslibrary.StatsContainer;

import java.util.HashMap;

public class CompareActivity extends AppCompatActivity implements OnInfoGetListener {

    int my_battle;
    int my_miles;
    int player_battle;
    int player_miles;
    String player_id;
    String player_name;
    Api mApi;
    TextView pl_name;
    TextView pl_battles;
    TextView pl_miles;
    TextView my_battles;
    TextView my_miless;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Intent intent = getIntent();
        player_id=intent.getStringExtra("infaId");
        player_name=intent.getStringExtra("infaName");
        my_battle=intent.getIntExtra("battle",0);
        my_miles=intent.getIntExtra("miles",0);

        mApi = new Api(this);
        mApi.getInfoPlayer(player_id);
        mApi.setOnInfoGetListener(this);
    }

    @Override
    public void onGetInfo(StatsContainer container) {
        player_battle=container.getBattles();
        player_miles=container.getMiles();
        pl_name=(TextView) findViewById(R.id.compare_player);
        pl_name.setText(player_name);
        pl_battles=(TextView) findViewById(R.id.player_battles);
        pl_miles=(TextView) findViewById(R.id.player_miles);
        pl_battles.setText(String.valueOf(player_battle));
        pl_miles.setText(String.valueOf(player_miles));
        my_battles=(TextView) findViewById(R.id.me_battles);
        my_miless=(TextView) findViewById(R.id.me_miles);
        my_battles.setText(String.valueOf(my_battle));
        my_miless.setText(String.valueOf(my_miles));
    }

    @Override
    public void onGetAchiv(HashMap<String, Integer> list, String[] names) {

    }
}
