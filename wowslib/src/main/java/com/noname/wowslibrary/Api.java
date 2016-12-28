package com.noname.wowslibrary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import static java.lang.Thread.sleep;

public class Api {

    private RequestQueue mQueue;
    private OnPlayerSearchListener mListener;
    private OnShipsListener nsListener;
    private OnInfoGetListener mListenerGet;
    private OnLoginListener mListenerLog;
    private OnShipStatsListenner onShipStatsListenner;
    private OnShipsListener mSListenner;
    private static String name;
    private static int count = 0;
    private String application_id = "53171588f86f7958d53b4b8cf6440241";

    public static String SEARCH_URL = "https://api.worldofwarships.ru/wows/account/list/?application_id=53171588f86f7958d53b4b8cf6440241&search=";
    public static String SEARCH_URL_INFO = "https://api.worldofwarships.ru/wows/account/info/?application_id=53171588f86f7958d53b4b8cf6440241&account_id=";
    public static final String LOGOUT_URL = "https://api.worldoftanks.ru/wot/auth/logout/?application_id=53171588f86f7958d53b4b8cf6440241&access_token=";
    public static final String SHIPS_URL = "https://api.worldofwarships.ru/wows/encyclopedia/ships/?application_id=demo&fields=name%2Cship_id";
    public static final String ACH_URL = "https://api.worldofwarships.ru/wows/account/achievements/?application_id=53171588f86f7958d53b4b8cf6440241&fields=battle&account_id=";
    public static final String SHIP_STAT = "https://api.worldofwarships.ru/wows/encyclopedia/ships/?application_id=53171588f86f7958d53b4b8cf6440241&fields=description%2Cname&ship_id=";

    public Api(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public static String getName() {
        return name;
    }

    public void exit(String access_token) {
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, LOGOUT_URL + access_token, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.getMessage());
            }
        }
        );
        mQueue.add(getRequest);
    }

    public static void setURL() {
        SEARCH_URL = "https://api.worldofwarships.ru/wows/account/list/?application_id=5317158hggggg8f86f7958d53b4b8cf6440241&search=";
    }

    public static void returnURL() {
        SEARCH_URL = "https://api.worldofwarships.ru/wows/account/list/?application_id=53171588f86f7958d53b4b8cf6440241&search=";
    }

    public void searchPlayer(final String name, final Activity act) {
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, SEARCH_URL + name, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<String> players = new ArrayList<String>();
                        ArrayList<String> ids = new ArrayList<String>();

                        try {
                            String status = response.getString("status");
                            JSONArray data = null;
                            if (!status.equals("error")) {
                                data = response.getJSONArray("data");
                                System.out.println("Response success");
                                count = 0;
                            } else {
                                System.out.println("Error response");
                                System.out.println(String.valueOf(Calendar.getInstance().getTime()));
                                count++;
                                try {
                                    sleep((int)(Math.exp(count) * 1000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(String.valueOf(count) + " try on " + String.valueOf(Calendar.getInstance().getTime()));
                                searchPlayer(name, act);
                            }
                            mListener.onPlayerSearch(data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }

        );
        getRequest.setRetryPolicy(new DefaultRetryPolicy(9000000, 20, (int) (Math.exp(1) * 1000)));
        mQueue.add(getRequest);
    }

        public static String[] getNames(JSONObject jo) {
            int length = jo.length();
            if(length == 0) {
                return null;
            } else {
                Iterator iterator = jo.keys();
                String[] names = new String[length];

                for(int i = 0; iterator.hasNext(); ++i) {
                    names[i] = (String)iterator.next();
                }

                return names;
            }
        }

    public void getShips(final Activity act) {
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, SHIPS_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String status = response.getString("status");
                            JSONObject data = null;
                            ArrayList<String []> ships =new ArrayList<>();
                            if (!status.equals("error")) {
                                data = response.getJSONObject("data");
                                String [] names = getNames(data);
                                for(int i=0; i<names.length; i++){
                                    String [] h = {names[i],data.getJSONObject(names[i]).getString("name")};
                                    //System.out.println(names[i]);
                                    ships.add(h);
                                }
                                System.out.println("Response success");
                                count = 0;
                            } else {
                                System.out.println("Error response");
                                System.out.println(String.valueOf(Calendar.getInstance().getTime()));
                                count++;
                                try {
                                    sleep((int)(Math.exp(count) * 10000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(String.valueOf(count) + " try on " + String.valueOf(Calendar.getInstance().getTime()));
                                getShips(act);
                            }
                            mSListenner.onShip(ships);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }

        );
        getRequest.setRetryPolicy(new DefaultRetryPolicy(9000000, 20, (int) (Math.exp(1) * 1000)));
        mQueue.add(getRequest);
    }

    public void getShipStat(String ID){
        final String IDs=ID;
        JsonObjectRequest getInfo = new JsonObjectRequest(Request.Method.GET, SHIP_STAT + ID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String text=null;
                        String name=null;
                        try {
                            String status = response.getString("status");
                            if (!status.equals("error")) {
                                System.out.println("Response success");
                                JSONObject infoObj = response.getJSONObject("data").getJSONObject(IDs);
                                text = infoObj.getString("description");
                                name = infoObj.getString("name");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        onShipStatsListenner.onStatShip(name,text);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error.Response", volleyError.getMessage());
            }
        });
        mQueue.add(getInfo);
    }

    public void getAcchivments(String ID, String access) {
        final String IDs = ID;
        final JsonObjectRequest getInfo = new JsonObjectRequest(Request.Method.GET, ACH_URL + ID + "&access_token=" + access, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject ach = response.getJSONObject("data").getJSONObject(IDs).getJSONObject("battle");
                            String[] names = {
                                    "RETRIBUTION",
                                    "FIGHTER",
                                    "MILLIONAIR",
                                    "ONE_SOLDIER_IN_THE_FIELD",
                                    "SEA_LEGEND",
                                    "MESSENGER",
                                    "UNSINKABLE",
                                    "SCIENCE_OF_WINNING_ARSONIST",
                                    "ATBA_CALIBER",
                                    "SCIENCE_OF_WINNING_TACTICIAN",
                                    "ENGINEER",
                                    "BATTLE_HERO",
                                    "BD2016_WRONG_SOW",
                                    "ARSONIST",
                                    "BD2016_MANNERS",
                                    "MAIN_CALIBER",
                                    "INSTANT_KILL",
                                    "JUNIOR_PLANNER",
                                    "MESSENGER_L",
                                    "SCIENCE_OF_WINNING_HARD_EDGED",
                                    "NO_DAY_WITHOUT_ADVENTURE",
                                    "BD2016_SNATCH",
                                    "MERCENARY_L",
                                    "SCIENCE_OF_WINNING_TO_THE_BOTTOM",
                                    "VETERAN",
                                    "NEVER_ENOUGH_MONEY",
                                    "NO_PRICE_FOR_HEROISM",
                                    "DREADNOUGHT",
                                    "CAPITAL",
                                    "BD2016_FESTIV_SOUP",
                                    "SCIENCE_OF_WINNING_BOMBARDIER",
                                    "CLEAR_SKY",
                                    "DOUBLE_KILL",
                                    "WARRIOR",
                                    "WORKAHOLIC",
                                    "FIRST_BLOOD",
                                    "DETONATED",
                                    "SUPPORT",
                                    "MERCENARY",
                                    "WITHERING",
                                    "CHIEF_ENGINEER",
                                    "BD2016_FIRESHOW",
                                    "NO_DAY_WITHOUT_ADVENTURE_L",
                                    "FIREPROOF",
                                    "BD2016_PARTY_CHECK_IN",
                                    "CBT_PARTICIPANT",
                                    "AMAUTEUR",
                                    "LIQUIDATOR",
                                    "SCIENCE_OF_WINNING_LUCKY"};
                            HashMap<String,Integer> list = new HashMap<>();
                            for(int i=0; i<names.length; i++){
                                int val = ach.getInt(names[i]);
                                list.put(names[i],val);
                            }
                            mListenerGet.onGetAchiv(list,names);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error.Response", volleyError.getMessage());
            }
        });
        mQueue.add(getInfo);

    }

    public StatsContainer getPersonalInfo(String ID, String access) {
        final String[] nick = {null};
        final String IDs = ID;

        JsonObjectRequest getInfo = new JsonObjectRequest(Request.Method.GET, SEARCH_URL_INFO + ID + "&access_token=" + access, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        StatsContainer container = new StatsContainer();

                        try {
                            String status = response.getString("status");
                            if (!status.equals("error")) {
                                System.out.println("Response success");
                                JSONObject infoObj = response.getJSONObject("data");
                                int credits = infoObj.getJSONObject(IDs).getJSONObject("private").getInt("credits");
                                int gold = infoObj.getJSONObject(IDs).getJSONObject("private").getInt("gold");
                                int free_exp = infoObj.getJSONObject(IDs).getJSONObject("private").getInt("free_xp");
                                int leveling_tier = infoObj.getJSONObject(IDs).getInt("leveling_tier");
                                int leveling_points = infoObj.getJSONObject(IDs).getInt("leveling_points");
                                long time = Long.valueOf(String.valueOf(infoObj.getJSONObject(IDs).get("created_at")));
                                Date created_at = new Date(time);
                                time = Long.valueOf(String.valueOf(infoObj.getJSONObject(IDs).get("last_battle_time")));
                                Date last_battle_time = new Date(time);
                                int battles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("battles");
                                int miles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("distance");
                                container.setBattles(battles);
                                container.setMiles(miles);
                                container.setLast_battle_time(last_battle_time);
                                container.setCreated_at(created_at);
                                container.setCredits(credits);
                                container.setGold(gold);
                                container.setFree_xp(free_exp);
                                container.setLeveling_tier(leveling_tier);
                                container.setLeveling_points(leveling_points);
                            }

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

    public StatsContainer getInfoPlayer(String ID) {
        final String[] nick = {null};
        final String IDs = ID;

        JsonObjectRequest getInfo = new JsonObjectRequest(Request.Method.GET, SEARCH_URL_INFO + ID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        StatsContainer container = new StatsContainer();

                        try {
                            String status = response.getString("status");
                            if (!status.equals("error")) {
                                System.out.println("Response success");
                                JSONObject infoObj = response.getJSONObject("data");

                                int battles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("battles");
                                int miles = infoObj.getJSONObject(IDs).getJSONObject("statistics").getInt("distance");
                                container.setBattles(battles);
                                container.setMiles(miles);
                            }

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

    public void setApiKey(String key) {
        application_id = key;
    }

    public String getApiKey() {
        return application_id;
    }

    public static String getShipsUrl(Api a) {
        return "https://api.worldofwarships.ru/wows/encyclopedia/ships/?application_id=" + a.getApiKey();
    }

    public void setOnPlayerChangeListener(OnPlayerSearchListener listener) {
        mListener = listener;
    }

    public void setOnInfoGetListener(OnInfoGetListener listener) {
        mListenerGet = listener;
    }


    public void setmListenerLog(OnLoginListener mListenerLog) {
        this.mListenerLog = mListenerLog;
    }



    public OnShipsListener getmSListenner() {
        return mSListenner;
    }

    public void setmSListenner(OnShipsListener mSListenner) {
        this.mSListenner = mSListenner;
    }

    public OnShipStatsListenner getOnShipStatsListenner() {
        return onShipStatsListenner;
    }

    public void setOnShipStatsListenner(OnShipStatsListenner onShipStatsListenner) {
        this.onShipStatsListenner = onShipStatsListenner;
    }
}
