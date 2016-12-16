package com.example.koen.powderguru2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // table name, database name and database version
    public static final String TABLE_NAME = "dbcity";
    static final String DB_NAME = "dbtodo.db";
    static final int DATABASE_VERSION = 9;

    // set fields of database schema. table collumns
    public static final String _ID = "id";
    public static final String city = "city";
    public static final String CHECKED = "checked";

    // constructor
    public DBhelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }


    // oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                city + " TEXT NOT NULL, " + CHECKED + " BOOLEAN NOT NULL);");

        // ugly written
        ContentValues first = new ContentValues();
        first.put("city", "City, Country (Example)");
        first.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, first);

        ContentValues second = new ContentValues();
        second.put("city", "Been there? check box to the right");
        second.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, second);

        ContentValues third = new ContentValues();
        third.put("city", "Hold a spot to delete it");
        third.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, third);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new table
        onCreate(db);
    }
}