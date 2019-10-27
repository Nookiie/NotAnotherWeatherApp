package com.example.notanotherweatherapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notanotherweatherapp.Common.Common;
import com.example.notanotherweatherapp.Model.OpenWeatherMap;

public class DataContext {
    private String city;
    private double temp;
    private String time;
    private int humidity;
    private String description;
    private String lastUpdate;
    private SQLiteDatabase sqliteDatabase;
    DBHelper dbHelper;

    public DataContext(Context context){

    }

    public DataContext(OpenWeatherMap map){
        city = map.getCity();
        temp = map.getMain().getTemp();
        time = Common.getDateNow();
        humidity = map.getMain().getHumidity();
        description = map.getWeather().get(0).getDescription();
    }

    public long insertMap(OpenWeatherMap map){
        assignFromMap(map);

        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getCity(), city);
        contentValues.put(dbHelper.getLastUpdate(), lastUpdate);
        contentValues.put(dbHelper.getTemp(), temp);
        contentValues.put(dbHelper.getTime(), time);
        contentValues.put(dbHelper.getDescription(), description);

        long id = dbb.insert(dbHelper.getTableName(), null, contentValues);
        return id;
    }

    public String getData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.getUID(), dbHelper.getCity(), dbHelper.getLastUpdate(), dbHelper.getTemp(), dbHelper.getDescription(), dbHelper.getTime()};
        Cursor cursor = db.query(dbHelper.getTableName(), columns, null, null,null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(dbHelper.getUID()));
            city = cursor.getString(cursor.getColumnIndex(dbHelper.getCity()));
            buffer.append(cid + " " + city + " \n");
        }
        return buffer.toString();
    }

    private void assignFromMap(OpenWeatherMap map){
        city = map.getCity();
        temp = map.getMain().getTemp();
        time = Common.getDateNow();
        humidity = map.getMain().getHumidity();
        description = map.getWeather().get(0).getDescription();
    }

}
