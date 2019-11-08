package com.example.notanotherweatherapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notanotherweatherapp.Helper.Message;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "WeatherDB";    // Database Name
    private static final String TABLE_NAME = "History";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version

    private static final String UID = "id";     // Column 1 (Primary Key)
    private static final String City = "City";    //Column 2
    private static final String LastUpdate = "LastUpdate";    // Column 3
    private static final String Temp = "Temp";    // Column 4
    private static final String Description = "Description";    // Column 4
    private static final String Time = "Time";    // Column 5
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public int getDATABASE_Version() {
        return DATABASE_Version;
    }

    public String getUID() {
        return UID;
    }

    public String getCity() {
        return City;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public String getTemp() {
        return Temp;
    }

    public String getTime() {
        return Time;
    }

    public String getDescription(){
        return Description;
    }

    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+City+" VARCHAR(255) ,"+ LastUpdate+" VARCHAR(225));";

    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Message.message(context,"" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}