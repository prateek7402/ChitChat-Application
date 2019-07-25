package com.example.jaankaar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Database_for_notekeeper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "NewContact2.db";
    private final static String table_name = "contactNoteKeepr";
    private String COL_1 = "ID";
    private String COL_2 = "TITLE";
    private String COL_3 = "ADDRESS";


    public Database_for_notekeeper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, ADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertData(String title, String address) throws IOException {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, title);
        cv.put(COL_3, address);
        long result = DB.insert(table_name, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = db.rawQuery("select * from " + table_name, null);
        return rs;
    }

}
