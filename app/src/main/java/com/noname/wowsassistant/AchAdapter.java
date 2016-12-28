package com.noname.wowsassistant;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Instant on 27.12.2016.
 */

class AchAdapter extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private final HashMap<String,Integer> array;
    private final String[] names;

    public AchAdapter(Activity activity, String[] names, HashMap<String, Integer> array) {

        //assert activity != null;
        //assert jsonArray != null;
        this.names=names;
        this.array = array;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        if (null == array)
            return 0;
        else
            return array.size();
    }

    @Override
    public Object[] getItem(int position) {
        if (null == array) return null;
        else {
            Object[] obj = {names[position],array.get(names[position])};
            return obj;
        }
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(String.valueOf(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.ach_list, null);


        TextView text = (TextView) convertView.findViewById(R.id.ach_value);
        TextView idtext = (TextView) convertView.findViewById(R.id.ach_name);

        Object [] obj = getItem(position);
        if (null != obj) {
            String ach_id = null;
            String ach = null;
            ach_id = (String) obj[0];
            ach = String.valueOf(obj[1]);

            text.setText(ach);
            idtext.setText(ach_id);
        }

        return convertView;
    }
}
