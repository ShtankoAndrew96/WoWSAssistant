package com.noname.wowslibrary;

import java.util.HashMap;

public interface OnInfoGetListener {
    void onGetInfo(StatsContainer container);
    void onGetAchiv(HashMap<String,Integer> list, String[] names);
}
