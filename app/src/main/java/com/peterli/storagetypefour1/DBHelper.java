package com.peterli.storagetypefour1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String CREATE_STUDENT = "create table if not exists Student(" +
            "id integer primary key autoincrement," +
            "name varchar(11)," +
            "age integer)";
    private static final String CREATE_TEACHER = "create table if not exists Teacher(" +
            "id integer primary key autoincrement," +
            "name varchar(11)," +
            "age integer)";

    public DBHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_TEACHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
