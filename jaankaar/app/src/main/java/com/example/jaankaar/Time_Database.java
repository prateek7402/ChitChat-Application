package com.example.jaankaar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time_Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "ContactBook4.db";
    private final static String table_name = "Image";
    private String COL_1 = "ID";
    private String COL_2 = "TITLE";
    private String COL_3 = "IMAGE";
    private String COL_4 = "DESCRIPTION";
    private String COL_5 = "DATE";
    boolean flag = true;

    public Time_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, IMAGE TEXT, DESCRIPTION TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertData(String title, Bitmap image, String description) throws IOException {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, title);
        //  checkForPermission();
        if (flag) {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // Toast.makeText(MainActivity.this, "Into the file storage", Toast.LENGTH_SHORT).show();
//                File root = Environment.getExternalStorageDirectory();
//                File dir = new File(root.getAbsolutePath() + "/AppStorage/"+".jpg");
//                if (!dir.exists()) {
//                    if (dir.mkdir()) {
//                        //       Toast.makeText(MainActivity.this, "Into the file", Toast.LENGTH_SHORT).show();
//                    } else {
//                        //     Toast.makeText(MainActivity.this, "File not accessable", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                //  Toast.makeText(MainActivity.this, dir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                File myfile = new File(String.valueOf(dir));
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(myfile);
//                    image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                    fos.flush();
//                    //fos.write(t1.getText().toString().getBytes());
//                    //    Toast.makeText(MainActivity.this, "Data written", Toast.LENGTH_SHORT).show();
//                    fos.close();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AppStorage";
                File dir = new File(file_path);
                if (!dir.exists())
                    dir.mkdirs();

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                String fileName = "PNG_" + timeStamp + "_";
                File file = new File(dir, fileName + ".png");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                image.compress(Bitmap.CompressFormat.PNG, 85, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

                String imagePath = file_path + "/" + fileName + ".png";
                cv.put(COL_3, imagePath);
                cv.put(COL_4, description);
                Date date = new Date();
                cv.put(COL_5, String.valueOf(date));

            }
        }
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
