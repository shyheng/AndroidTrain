package com.shy.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private final Context context;
    private final DBHelper dbHelper;

    public Dao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    public void add(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        db.insertOrThrow(DBHelper.TABLE_NAME_alarm, null, contentValues);
    }

    public List<User> QueryAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id","username","password"}, null, null,
                null, null, null);
        List<User> objs = new ArrayList<User>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                User order = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                objs.add(order);
            }
        }
        return objs;
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME_alarm, "id >=?", new String[]{String.valueOf(0)});
    }

    public void update(int id,String username,String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        db.update(DBHelper.TABLE_NAME_alarm, contentValues, "id =?", new String[]{String.valueOf(id)});
    }
}
