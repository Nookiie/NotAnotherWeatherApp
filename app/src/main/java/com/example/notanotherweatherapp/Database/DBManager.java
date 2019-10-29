package com.example.notanotherweatherapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.notanotherweatherapp.Common.Common;
import com.example.notanotherweatherapp.Model.OpenWeatherMap;

public class DBManager {
    private SQLiteDatabase sqliteDatabase;
    private Context context;
    DBHelper dbHelper;

    public DBManager open()
            throws SQLException {
        dbHelper = new DBHelper(context);
        sqliteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public DBManager(Context context){
        this.context = context;
    }

    public long insertData(OpenWeatherMap map){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getCity(), map.getName());
        contentValues.put(dbHelper.getLastUpdate(), map.getLastUpdate());
        // contentValues.put(dbHelper.getTemp(), temp);
        // contentValues.put(dbHelper.getTime(), time);
        // contentValues.put(dbHelper.getDescription(), description);

        long id = db.insert(dbHelper.getTableName(), null, contentValues);
        return id;
    }

    public int update(long id, OpenWeatherMap map){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getCity(), map.getName());
        contentValues.put(dbHelper.getLastUpdate(), map.getLastUpdate());
        int i = sqliteDatabase.update(dbHelper.getTableName(), contentValues, dbHelper.getUID() + " = " + id, null);
        return i;
    }

    public void delete(long id){
        sqliteDatabase.delete(dbHelper.getTableName(), dbHelper.getUID() + " = " + id, null);
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db.isOpen()){
            db.execSQL("DELETE FROM " + dbHelper.getTableName());
        }
    }

    public int getCount(){
        sqliteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("select * from " + dbHelper.getTableName(),null );
        return cursor.getCount();
    }

    public String fetch(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.getUID(), dbHelper.getCity(), dbHelper.getLastUpdate()};
        Cursor cursor = db.query(dbHelper.getTableName(), columns, null, null,null, null, "id desc");
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(dbHelper.getUID()));
            String city = cursor.getString(cursor.getColumnIndex(dbHelper.getCity()));
            String update = cursor.getString(cursor.getColumnIndex(dbHelper.getLastUpdate()));
            buffer.append(cid + " " + city + " " +update + " \n");
        }
        return buffer.toString();
    }

    public String fetchWithLimiter(int limit){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.getUID(), dbHelper.getCity(), dbHelper.getLastUpdate()};
        Cursor cursor = db.query(dbHelper.getTableName(), columns, null, null,null, null, "id desc");
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext() && limit > 0){
            int cid = cursor.getInt(cursor.getColumnIndex(dbHelper.getUID()));
            String city = cursor.getString(cursor.getColumnIndex(dbHelper.getCity()));
            String update = cursor.getString(cursor.getColumnIndex(dbHelper.getLastUpdate()));
            buffer.append(cid + " " + city + " " +update + " \n");
            limit--;
        }
        return buffer.toString();
    }

    public void close(){
        dbHelper.close();
    }

}
