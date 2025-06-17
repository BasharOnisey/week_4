package com.example.week4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TodoItem> list;

    public TodoAdapter(Context context, ArrayList<TodoItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Build each row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TodoItem item = list.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.getText() + (item.isUrgent() ? " (URGENT)" : ""));

        if (item.isUrgent()) {
            convertView.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        }

        return convertView;
    }
}
