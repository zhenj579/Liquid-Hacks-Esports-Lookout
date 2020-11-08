package com.example.liquidlookout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends BaseAdapter {
    Context context;
    String[] data;

    public MyCustomAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int pos) {
        return data[pos];
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.upcoming_match_layout, parent, false);
        TextView text = (TextView) convertView.findViewById(R.id.teamMatchText);
        text.setText(data[pos]);
        return convertView;
    }
}
