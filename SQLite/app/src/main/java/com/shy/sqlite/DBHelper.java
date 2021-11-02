package com.shy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
//    数据库名
    private static final String DB_NAME = "ddt.db";
//    表名
    public static final String TABLE_NAME_alarm = "alarm";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists alarm (id integer primary key AUTOINCREMENT, username text,password text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_alarm;
        db.execSQL(sql);
        onCreate(db);
    }
}
