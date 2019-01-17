package com.example.a0.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//deprecated

public class NotepadAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    public static ArrayList <Record> list;
    private ArrayList<Record> arrayListFilter = new ArrayList<>();
    private int res;

    public static NotepadAdapter getInstance() {
        return instance;
    }

    private static NotepadAdapter instance;

    public static NotepadAdapter getInstance(Context context, int res, ArrayList<Record> list) {
        if (instance==null){
            instance = new NotepadAdapter(context, res);
        }
        return instance;
    }

    private NotepadAdapter(Context context, int res){
        this.context = context;
        this.list = Record.recordList;
        this.layoutInflater = LayoutInflater.from(context);
        this.res = res;
        this.arrayListFilter.addAll(this.list);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(this.res, parent, false);
        TextView textViewText = view.findViewById(R.id.item_list_textview);
        TextView textViewDate = view.findViewById(R.id.item_list_textview_date);
        TextView buttonDelete = view.findViewById(R.id.item_list_button_delete);

        Record record = list.get(position);

        textViewDate.setText(record.getDate());
        textViewText.setText(record.getRecord());
        buttonDelete.setOnClickListener(RecordManager.onClickListener);

        return view;

    }

    public void  filter(String s){
        s = s.toLowerCase();
        System.out.println("idw777 " + arrayListFilter.size());
        arrayListFilter.clear();
        if (s.length()==0){
            arrayListFilter.addAll(list);
        } else {
            for (Record record : arrayListFilter){
                if (record.getRecord().toLowerCase().contains(s)){
                    arrayListFilter.add(record);
                }
            }
        }
        super.notifyDataSetChanged();
    }
}
