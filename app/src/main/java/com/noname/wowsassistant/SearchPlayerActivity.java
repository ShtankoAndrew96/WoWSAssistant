package com.noname.wowsassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noname.wowslibrary.Api;
import com.noname.wowslibrary.OnPlayerSearchListener;

import org.json.JSONArray;

public class SearchPlayerActivity extends Activity implements OnPlayerSearchListener{

    private EditText mEditText;
    private ListView mListView;
    private Api mApi;
    private JSONAdapter adapter;
    TextView tv;
    TextView tv2;
    public String ret_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_player);


        mEditText = (EditText) findViewById(R.id.nameEdit);
        mListView = (ListView) findViewById(R.id.nameList);

        mApi = new Api(getApplicationContext());
        mApi.setOnPlayerChangeListener(this);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                tv = (TextView) view.findViewById(R.id.player_id);
                tv2 = (TextView) view.findViewById(R.id.nameText);
                String id_number = tv.getText().toString();
                String name_player = tv2.getText().toString();

                Intent intent = new Intent(SearchPlayerActivity.this, StatsPlayerActivity.class);
                intent.putExtra("infaId", id_number);
                intent.putExtra("infaName", name_player);

                Toast toast = Toast.makeText(getApplicationContext(),
                        id_number  , Toast.LENGTH_SHORT);
                toast.show();
                startActivity(intent);




            }
        });




    }
    public void search (View view){
        mApi.searchPlayer(mEditText.getText().toString());
    }

    @Override
    public void onPlayerSearch(JSONArray jarray ) {
        adapter = new JSONAdapter(SearchPlayerActivity.this, jarray);
        mListView.setAdapter(adapter);
    }




}