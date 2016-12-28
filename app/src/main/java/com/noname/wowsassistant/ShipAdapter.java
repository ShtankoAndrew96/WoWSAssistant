package com.noname.wowsassistant;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Instant on 28.12.2016.
 */

public class ShipAdapter  extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private final ArrayList<String []> ships;
    public ShipAdapter (Activity activity, ArrayList<String []> ships) {
        //assert activity != null;
        //assert jsonArray != null;

        this.ships = ships;
        this.activity = activity;
    }


    @Override public int getCount() {
        if(null==ships)
            return 0;
        else
            return ships.size();
    }

    @Override public String[] getItem(int position) {
        if(null==ships) return null;
        else
            return ships.get(position);
    }

    @Override public long getItemId(int position) {
        String[] obj = getItem(position);

        return Long.valueOf(obj[0]);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.ship_col, null);

        TextView idtext =(TextView)convertView.findViewById(R.id.ship_name_view);

        String [] data = getItem(position);
        if(null!=data ){
            byte [] fd= data[1].getBytes();
            String nick = new String(fd, Charset.forName("utf-8"));
            idtext.setText(nick);
        }

        return convertView;
    }
}
