package com.noname.wowsassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnPlayerSearchListener;

import org.json.JSONArray;

public class SearchComparePlayerActivity extends AppCompatActivity implements OnPlayerSearchListener {
    private ListView view;
    private EditText name;
    private Api mApi;
    private JSONAdapter adapter;
    private TextView tv;
    private TextView tv2;
    private int battle;
    private int miles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_compare_player);

        Intent intent = getIntent();
        battle=intent.getIntExtra("battles",0);
        miles = intent.getIntExtra("miles",0);

        name = (EditText) findViewById(R.id.name_to_search);
        view = (ListView) findViewById(R.id.players_list);

        mApi = new Api(getApplicationContext());
        mApi.setOnPlayerChangeListener(this);


        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                tv = (TextView) view.findViewById(R.id.player_id);
                tv2 = (TextView) view.findViewById(R.id.nameText);
                String id_number = tv.getText().toString();
                String name_player = tv2.getText().toString();

                Intent intent = new Intent(SearchComparePlayerActivity.this, CompareActivity.class);
                intent.putExtra("infaId", id_number);
                intent.putExtra("infaName", name_player);
                intent.putExtra("battle",battle);
                intent.putExtra("miles",miles);

                Toast toast = Toast.makeText(getApplicationContext(),
                        id_number  , Toast.LENGTH_SHORT);
                toast.show();
                startActivity(intent);




            }
        });
    }

    public void search(View view) {
        mApi.searchPlayer(name.getText().toString(),this);
    }

    @Override
    public void onPlayerSearch(JSONArray jsonArray) {
        adapter = new JSONAdapter(this, jsonArray);
        view.setAdapter(adapter);
    }
}
