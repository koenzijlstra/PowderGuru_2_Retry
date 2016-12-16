package com.example.koen.powderguru2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/*
* Koen Zijlstra, 10741615
*
* In this class the dbhelper is initialized and crud methods are defined.
*/

public class DBmanager {

    private DBhelper dbHelper;
    private SQLiteDatabase database;

    // constructor
    public DBmanager(Context c){
        dbHelper = new DBhelper(c);
        // open database connection
        database = dbHelper.getWritableDatabase();
    }

    // create todo_object , insert into database. needs string (the name of a city) as argument
    public void insert (String citystring){
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", citystring);
        contentValues.put("checked", Boolean.FALSE);
        // insert into database
        database.insert("dbcity", null, contentValues);
    }

    // delete city object from database
    public void delete (int cityid){
        database.delete("dbcity", "id = "+ cityid, null);
    }

    // get all the city objects
    public List getallcities (){
        // create new arraylist
        List allcities = new ArrayList();

        // columns we want to know, used by cursor
        String[] citycolumns = new String[] {"id","city", "checked"};

        // get values of the defined columns per row, cursor starts at first city
        Cursor cursor = database.query("dbcity", citycolumns, null, null, null, null, null);
        cursor.moveToFirst();

        // get values, create new city object, add all cities to the arraylist
        while (!cursor.isAfterLast()) {
            Cityobj city = new Cityobj(cursor.getInt(0), cursor.getString(1), (cursor.getInt(2) == 1));

            // Add to the arraylist "allcities"
            allcities.add(city);

            // see if city is checked
            city.ischecked();
            // Move to the next row
            cursor.moveToNext();
        }
        return allcities;
    }
}
