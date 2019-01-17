package com.example.a0.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    static Context mainContext;
    static ListView listView;
    static EditText editText;
    android.support.v7.widget.SearchView searchView;
    Button button;
    public static NewCursorAdapter newCursorAdapter;
    NewDB newDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.activity_main_listview);

        newDB = new NewDB(this).open();

        newCursorAdapter = new NewCursorAdapter(this, newDB.getAllData(), 1);

        listView.setAdapter(newCursorAdapter);

        editText = findViewById(R.id.activity_main_edittext);
        button = findViewById(R.id.activity_main_button);
        button.setOnClickListener(RecordManager.onClickListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setTextFilterEnabled(true);
         searchView = findViewById(R.id.toolbar_searchview);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length()==0){
                    newCursorAdapter.changeCursor(newDB.open().getAllData());
                } else {
                    newCursorAdapter.changeCursor(newDB.open().filterCursor(s));
                    newCursorAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });

    }

    //back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isIconified()){
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }
}
