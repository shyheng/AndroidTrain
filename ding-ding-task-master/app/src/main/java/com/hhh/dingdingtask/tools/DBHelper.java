package com.hhh.dingdingtask.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
https://blog.csdn.net/weixin_43017921/article/details/109399096

使用//依靠DatabaseHelper带全部参数的构造函数创建数据库
DBHelper dbHelper = new DBHelper(MainActivity.this, "setting.db",null,1);
SQLiteDatabase db = dbHelper.getWritableDatabase();

 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 8;
    private static final String DB_NAME = "ddt.db";
    public static final String TABLE_NAME_alarm = "alarm";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //带有全部参数的构造函数，此构造函数是必须需要的。Eclipse和Android Studio均有自动填充功能
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(DBHelper.class.getSimpleName(), "创建SQLLite数据库");
        //String sql1 = "DROP TABLE IF EXISTS " +TABLE_NAME_alarm ;
        //db.execSQL(sql1);
        //创建数据库sql语句
        String sql = "create table if not exists alarm (id integer primary key AUTOINCREMENT, hour integer, minute integer,worktype text)";
        //执行sql语句
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(DBHelper.class.getSimpleName(), "升级SQLLite数据库");
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_alarm;
        db.execSQL(sql);
        onCreate(db);
    }
}
