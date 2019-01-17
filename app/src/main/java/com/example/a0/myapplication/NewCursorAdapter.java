package com.example.a0.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class NewCursorAdapter extends CursorAdapter {

    public NewCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(getCount()-1);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewRecord = view.findViewById(R.id.item_list_textview);
        TextView textViewDate = view.findViewById(R.id.item_list_textview_date);
        TextView button = view.findViewById(R.id.item_list_button_delete);
        button.setOnClickListener(RecordManager.onClickListener);

        String record = cursor.getString(cursor.getColumnIndex("txt"));
        String date = cursor.getString(cursor.getColumnIndex("date"));

        textViewDate.setText(date);
        textViewRecord.setText(record);
    }
}
