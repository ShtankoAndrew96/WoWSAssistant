package com.noname.wowsassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnShipsListener;

import java.util.ArrayList;

public class ShipsActivity extends AppCompatActivity implements OnShipsListener{

    private ListView listView;
    private Api mApi;
    private ArrayList<String []> ships;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ships);
        listView = (ListView) findViewById(R.id.ship_list);
        mApi = new Api(this);
        mApi.getShips(this);
        mApi.setmSListenner(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id=ships.get(i)[0];
                Intent intent = new Intent(ShipsActivity.this, ShipStatActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onShip(ArrayList<String[]> ships) {
        ShipAdapter sa = new ShipAdapter(this, ships);
        this.ships=ships;
        listView.setAdapter(sa);
    }
}
