package com.peterli.storagetypefour1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri studentUri = Uri.parse("content://com.peter.myprovider/Student");
        Uri teacherUri = Uri.parse("content://com.peter.myprovider/Teacher");

        ContentValues values1 = new ContentValues();
        values1.put("name", "小天");
        values1.put("age", 18);

        ContentResolver resolver1 = getContentResolver();
        resolver1.insert(studentUri, values1);

        Cursor cursor1 = resolver1.query(studentUri, null, null, null, null);
        if (cursor1 != null) {
            while (cursor1.moveToNext()) {
                Log.d("AAAAAAAAAAAAAA1", cursor1.getInt(cursor1.getColumnIndex("id")) +
                        cursor1.getString(cursor1.getColumnIndex("name")) +
                        cursor1.getInt(cursor1.getColumnIndex("age")));
            }
            cursor1.close();
        }

        ContentValues values2 = new ContentValues();
        values2.put("name", "刘老师");
        values2.put("age", 38);

        ContentResolver resolver2 = getContentResolver();
        resolver2.insert(teacherUri, values2);

        Cursor cursor2 = resolver2.query(teacherUri, null, null, null, null);
        if (cursor2 != null) {
            while (cursor2.moveToNext()) {
                Log.d("AAAAAAAAAAAAAA2", cursor2.getInt(cursor2.getColumnIndex("id")) +
                        cursor2.getString(cursor2.getColumnIndex("name")) +
                        cursor2.getInt(cursor2.getColumnIndex("age")));
            }
            cursor2.close();
        }

    }
}
