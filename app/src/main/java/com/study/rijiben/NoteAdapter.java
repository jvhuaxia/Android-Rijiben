package com.study.rijiben;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ListView的适配器 继承BaseAdapter写的
 */
public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<Note> items;

    public NoteAdapter(Context context, List<Note> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Note item = items.get(arg0);
        View view = LayoutInflater.from(context).inflate(R.layout.note_item_layout, null);
        TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        TextView textViewContent = (TextView) view.findViewById(R.id.textViewContent);
        TextView textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        TextView textViewLastTime = (TextView) view.findViewById(R.id.textViewLastTime);
        TextView textViewXinqing = (TextView) view.findViewById(R.id.textViewXinqing);
        TextView textViewTianqi = (TextView) view.findViewById(R.id.textViewTianqi);
        textViewTitle.setText(item.getName());
        textViewContent.setText(item.getContent());
        textViewTime.setText(item.getTime());
        textViewLastTime.setText(item.getLastTime());
        textViewTianqi.setText(item.getTianqi());
        textViewXinqing.setText(item.getXinqing());
        return view;
    }

}
