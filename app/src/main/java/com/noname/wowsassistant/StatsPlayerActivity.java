package com.noname.wowsassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnInfoGetListener;
import com.noname.wowslibrary.StatsContainer;

public class StatsPlayerActivity extends Activity implements OnInfoGetListener {

    Api myApi;

    private TextView player_name_view;
    private TextView battles_view;
    private TextView miles_view;
    private String name;

    public TextView getPlayerName(){
        return player_name_view;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent i = getIntent();
        String id = i.getStringExtra("infaId");
        name = i.getStringExtra("infaName");

        Log.d("ZjzaaaHZ", id);
        Log.d("ZjzaaaHZ", name);

        player_name_view = (TextView) findViewById(R.id.name_player);
        battles_view = (TextView) findViewById(R.id.battles_view);
        miles_view = (TextView) findViewById(R.id.miles_view);

        player_name_view.setText(name);

        myApi = new Api(getApplicationContext());
        myApi.getInfoPlayer(id);
        myApi.setOnInfoGetListener(this);

    }

    @Override
    public void onGetInfo(StatsContainer container) {

        battles_view.setText(String.valueOf(container.getBattles()));
        miles_view.setText(String.valueOf(container.getMiles()));
        player_name_view.setText(String.valueOf(container.getName()));
        Log.d("infaaaaa", String.valueOf(container.getBattles()));

    }
}
