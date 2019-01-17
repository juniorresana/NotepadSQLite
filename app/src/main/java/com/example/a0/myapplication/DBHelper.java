package com.example.a0.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String dataBase, int version){
        super(context, dataBase, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tab (txt text, date text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRecord(String text, String date){
        this.getWritableDatabase().execSQL("insert into tab (txt, date) values ("+text+", "+date+"); ");

    }
     public void  delet(String whereString){
         ((SQLiteDatabase)this.getWritableDatabase()).delete("tab", whereString, null);
     }
}
