package com.kaoneaw.moopiing.sharingpayment.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kaoneaw.moopiing.sharingpayment.Models.Room;

public class DatabaseRoom extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Room.db";
    private static final String TABLE_NAME = "Room";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_FOOD = "food";
    private static final String COLUMN_DRINK = "drink";
    private static final String COLUMN_DESSERT = "dessert";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table Room (id integer primary key not null, "
            + "name text not null, "
            + "food double not null, "
            + "drink double not null, "
            + "dessert double not null"
            + ");";

    public DatabaseRoom(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertRoom(Room rm){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from Room";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, rm.getName());
        values.put(COLUMN_FOOD , rm.getFood());
        values.put(COLUMN_DRINK, rm.getDrink());
        values.put(COLUMN_DESSERT, rm.getDessert());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchFood(String username) {
        db = this.getReadableDatabase();
        String query = "select name, food from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchDrink(String name) {
        db = this.getReadableDatabase();
        String query = "select name, drink from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(name)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchDessert(String name) {
        db = this.getReadableDatabase();
        String query = "select name, dessert from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(name)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchID(String name) {
        db = this.getReadableDatabase();
        String query = "select name, id from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(name)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchName(String name) {
        db = this.getReadableDatabase();
        String query = "select name, name from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(name)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void delete(String name) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(searchID(name))});
        db.close();
    }
}
