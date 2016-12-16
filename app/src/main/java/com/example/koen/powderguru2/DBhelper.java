package com.example.koen.powderguru2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
* Koen Zijlstra, 10741615
*
* DataBase helper. Creates 1 table called dbcity with columns:
* id (integeger primary key autoincrement), city (text not null) and checked (boolean not null)
* setexamples creates 4 examples when database is created (so just once).
*/

public class DBhelper extends SQLiteOpenHelper {

    // table name and database version
    public static final String TABLE_NAME = "dbcity";
    static final int DATABASE_VERSION = 14;

    // set fields of database schema (columns)
    public static final String _ID = "id";
    public static final String city = "city";
    public static final String CHECKED = "checked";

    // constructor
    public DBhelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    // table is created, setexamples is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                city + " TEXT NOT NULL, " + CHECKED + " BOOLEAN NOT NULL);");

        // call the function setexamples, uses the sqlitedatabase db as argument
        setexamples(db);
    }

    // called in oncreate, sets four examples
    public void setexamples(SQLiteDatabase db){
        // ugly written
        ContentValues first = new ContentValues();
        first.put("city", "Gerlos, Austria (Example)");
        first.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, first);

        ContentValues cityexample = new ContentValues();
        cityexample.put("city", "Zell Am See, Austria");
        cityexample.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, cityexample);

        ContentValues third = new ContentValues();
        third.put("city", "Hold a spot to delete it");
        third.put("checked", Boolean.FALSE);
        db.insert("dbcity", null, third);

        ContentValues fourth = new ContentValues();
        fourth.put("city", "Been there? Check box to the right");
        fourth.put("checked", Boolean.TRUE);
        db.insert("dbcity", null, fourth);
    }

    // onUpgrade, drops old table and creates new table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new table
        onCreate(db);
    }
}