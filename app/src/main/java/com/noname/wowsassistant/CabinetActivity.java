package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnInfoGetListener;
import com.noname.wowslibrary.StatsContainer;

import java.util.HashMap;


public class CabinetActivity extends AppCompatActivity implements OnInfoGetListener {
    private String nickname;
    private int account_id;
    private String access_token;
    private StatsContainer container;

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
        mApi.getPersonalInfo(String.valueOf(account_id),access_token);
        mApi.setOnInfoGetListener(this);
    }

    public void logout(View view) {
        Intent intent = new Intent(this,EntryActivity.class);
        mApi.exit(access_token);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onGetInfo(StatsContainer container) {
        TextView name = (TextView) findViewById(R.id.name_id);
        name.setText(nickname);
        TextView free_xp = (TextView) findViewById(R.id.free_xp_id);
        free_xp.setText(String.valueOf(container.getFree_xp()));
        TextView credits = (TextView) findViewById(R.id.credits_id);
        credits.setText(String.valueOf(container.getCredits()));
        TextView gold = (TextView) findViewById(R.id.gold_id);
        gold.setText(String.valueOf(container.getGold()));
        TextView created_at = (TextView) findViewById(R.id.tier_id);
        created_at.setText(String.valueOf(container.getLeveling_tier()));
        TextView last_battle_time = (TextView) findViewById(R.id.points_id);
        last_battle_time.setText(String.valueOf(container.getLeveling_points()));
        this.container=container;
    }

    @Override
    public void onGetAchiv(HashMap<String, Integer> list, String[] names) {
        AchAdapter adapter = new AchAdapter(this,names,list);
        ListView lv = (ListView) findViewById(R.id.ach_list);
        lv.setAdapter(adapter);
    }

    public void showAch(View view) {
        mApi.getAcchivments(String.valueOf(account_id), access_token);
    }

    public void searchPlayer(View view) {
        int battles = container.getBattles();
        int miles = container.getMiles();
        Intent intent = new Intent(this,SearchComparePlayerActivity.class);
        intent.putExtra("battles",battles);
        intent.putExtra("miles",miles);
        startActivity(intent);
    }
}
