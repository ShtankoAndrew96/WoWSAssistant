package com.noname.wowslibrary;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Api implements OnInfoGetListener{

    private RequestQueue mQueue;
    private OnPlayerSearchListener mListener;
    private OnShipsListener nsListener;
    private OnInfoGetListener mListenerGet;
    private OnLoginListener mListenerLog;
    private static String name;

    public static final String SEARCH_URL = "https://api.worldofwarships.ru/wows/account/list/?application_id=53171588f86f7958d53b4b8cf6440241&search=";
    public static final String SEARCH_URL_INFO = "https://api.worldofwarships.ru/wows/account/info/?application_id=53171588f86f7958d53b4b8cf6440241&fields=statistics&account_id=";
    public static final String LOGOUT_URL = "https://api.worldoftanks.ru/wot/auth/logout/?application_id=53171588f86f7958d53b4b8cf6440241&access_token=";
    public Api(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public static String getName() {
        return name;
    }

    public void exit(String access_token){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, LOGOUT_URL + access_token, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {}
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );
        mQueue.add(getRequest);
    }

    public void searchPlayer (String name){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, SEARCH_URL + name, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<String> players = new ArrayList<String>();
                        ArrayList<String> ids = new ArrayList<String>();

                        try {
                            JSONArray data = response.getJSONArray("data");
                            mListener.onPlayerSearch(data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );

        mQueue.add(getRequest);
    }

    public StatsContainer getInfoPlayer(String ID){
        final String[] nick = {null};
        final String IDs = ID;

        JsonObjectRequest getInfo = new JsonObjectRequest(Request.Method.GET, SEARCH_URL_INFO + ID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        StatsContainer container = new StatsContainer();

                        try {
                            JSONObject infoObj = response.getJSONObject("data");

                            int battles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("battles");
                            int miles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("distance");
                            nick[0] = infoObj.getJSONObject(IDs).getJSONObject("statistics").getString("nickname");
                            container.setBattles(battles);
                            container.setMiles(miles);
                            container.setName(nick[0]);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mListenerGet.onGetInfo(container);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error.Response", volleyError.getMessage());
            }
        });
        mQueue.add(getInfo);
        return null;
    }

    public void setOnPlayerChangeListener(OnPlayerSearchListener listener){
        mListener = listener;
    }

    public void setOnInfoGetListener (OnInfoGetListener listener){
        mListenerGet = listener;
    }


    public void setmListenerLog(OnLoginListener mListenerLog) {
        this.mListenerLog = mListenerLog;
    }

    public void setShipListener(OnShipsListener nsListener) {
        this.nsListener = nsListener;
    }


    @Override
    public void onGetInfo(StatsContainer container) {

    }
}
