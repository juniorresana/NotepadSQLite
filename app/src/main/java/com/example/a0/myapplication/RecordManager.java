package com.example.a0.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


///////////////////////////
//@deprecated class

public class RecordManager {

    public static NotepadAdapter notepadAdapter;
    public static ListView listView;
    static DBHelper dbHelper;

    public static View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_list_button_delete:
                    View view = (View) v.getParent().getParent().getParent();
                    String s = ((((TextView) view.findViewById(R.id.item_list_textview_date)).getText().toString()));
                    System.out.println("idw333 " + s);
                    deleteRecord(s);


                    break;
                case R.id.activity_main_button:
                    queryRecord();
            }
        }
    };

    static void deleteRecord(String date) {

        MainActivity.newCursorAdapter.changeCursor(new NewDB(MainActivity.mainContext).open().delete(date).getAllData());
        MainActivity.newCursorAdapter.notifyDataSetChanged();
    }

    static void queryRecord() {

        String text = MainActivity.editText.getText().toString();
        String date = new SimpleDateFormat("yy-mm-dd").format(new Date().getTime());

     MainActivity.newCursorAdapter.changeCursor(new NewDB(MainActivity.mainContext).open().add(text, date).getAllData());
     MainActivity.newCursorAdapter.notifyDataSetChanged();
     MainActivity.editText.getText().clear();

    }


}
