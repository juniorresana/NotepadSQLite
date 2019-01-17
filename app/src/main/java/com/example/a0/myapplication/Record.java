package com.example.a0.myapplication;

import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;

public class Record {

    public static ArrayList<Record> recordList;

    private String record;
    private String date;

    public Record(String record, String date){
        this.record = record;
        this.date = date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
