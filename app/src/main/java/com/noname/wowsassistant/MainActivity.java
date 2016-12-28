package com.noname.wowsassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.noname.wowslibrary.Api;

public class MainActivity extends AppCompatActivity {

    private String status;
    private String access_token;
    private String nickname;
    private int account_id;
    private Api mApi;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String[] parsed = getParseParams(intent);
        if (parsed != null) {
            status = parsed[0];
            access_token = parsed[1];
            nickname = parsed[2];
            account_id = Integer.valueOf(parsed[3]);
            TextView textView = (TextView) findViewById(R.id.nicknameText);
            textView.setText(nickname);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static String[] getParseParams(Intent intent){
        String params = intent.getStringExtra("params");
        if(params!=null) {
            String[] parse_params = new String[4];
            String[] param_array = params.split("&");
            String[][] splited_param_array = new String[param_array.length - 1][2];
            for (int i = 1; i < param_array.length; i++) {
                String[] line = param_array[i].split("=");
                splited_param_array[i - 1][0] = line[0];
                splited_param_array[i - 1][1] = line[1];
            }
            for (int j = 0; j < splited_param_array.length; j++) {
                if (splited_param_array[j][0].equals("status"))
                    parse_params[0] = splited_param_array[j][1];
                else if (splited_param_array[j][0].equals("access_token"))
                    parse_params[1] = splited_param_array[j][1];
                else if (splited_param_array[j][0].equals("nickname"))
                    parse_params[2] = splited_param_array[j][1];
                else if (splited_param_array[j][0].equals("account_id"))
                    parse_params[3] = splited_param_array[j][1];
            }
            return parse_params;
        }
        return null;
    }

    public void wievSearch(View view) {
        Intent intent = new Intent(this, SearchPlayerActivity.class);
        startActivity(intent);
    }

    public void viewEncyclopedia(View view) {
        Intent intent = new Intent(this,ShipsActivity.class);
        startActivity(intent);
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void goToCabinet(View view) {
        Intent intent;
        if(status!=null){
            intent=new Intent(this,CabinetActivity.class);
            intent.putExtra("nickname",nickname);
            intent.putExtra("account_id",account_id);
            intent.putExtra("access_token",access_token);
        }else{
            intent=new Intent(this,LoginActivity.class);
        }
        startActivity(intent);
    }
}
