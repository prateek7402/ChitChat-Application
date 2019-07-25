package com.example.jaankaar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Call_Log_Database extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "NewContact1.db";
    private final static String table_name = "contactLogStore";
    private String COL_1 = "ID";
    private String COL_2 = "NAME";
    private String COL_3 = "NUMBER";
    private String COL_4 = "IMAGE";
    private String COL_5 = "LOG";
    boolean flag = true;

    public Call_Log_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, NUMBER TEXT, IMAGE TEXT, LOG TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertData(String name, String number, String image) throws IOException {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_4, image);
        cv.put(COL_3, number);
        Date currentTime = Calendar.getInstance().getTime();
        cv.put(COL_5,currentTime.toString());
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
