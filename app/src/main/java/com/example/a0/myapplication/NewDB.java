package com.example.a0.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDB {

    private final Context context;
    private NewDBHelper newDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public NewDB(Context context){
        this.context = context;

    }

    public NewDB open(){
        newDBHelper = new NewDBHelper(context);
        sqLiteDatabase = newDBHelper.getWritableDatabase();
        return this;
    }
    public NewDB close(){
        if (newDBHelper!=null)
            newDBHelper.close();
        return this;
    }
    public Cursor getAllData(){
        Cursor cursor =  sqLiteDatabase.query("tab", null,null,null,null,null,null);
        cursor.moveToPosition(cursor.getCount()-1);
        return cursor;
    }
    public NewDB  add(String txt, String date){
        ContentValues contentValues = new ContentValues();
        contentValues.put("txt", txt);
        contentValues.put("date", date);
        sqLiteDatabase.insert("tab", null, contentValues);
        return this;
    }
    public NewDB delete(String date){
        sqLiteDatabase.delete("tab", "date='"+date+"';", null);
        return this;
    }

    private class NewDBHelper extends SQLiteOpenHelper {

        public NewDBHelper(Context context){
            super(context, "newdb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table tab (_id integer primary key autoincrement, txt text, date text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public Cursor filterCursor (String s){
        return sqLiteDatabase.query("tab", null, "txt like '%"+s+"%';", null,null,null,null,null);
    }

}
