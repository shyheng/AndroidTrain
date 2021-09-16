package com.hhh.dingdingtask.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hhh.dingdingtask.entity.Alarm;
import com.hhh.dingdingtask.tools.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/5c33be6ce89d
 */
public class AlarmDao {
    private final Context context;
    private final DBHelper dbHelper;

    public AlarmDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    /**
     * @param hour   小时
     * @param minute 分钟
     */
    public void add(int hour, int minute, String worktype) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.beginTransaction();
        // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
        ContentValues contentValues = new ContentValues();
        //contentValues.put("Id", null);//主键自增 此句也可不用
        contentValues.put("hour", hour);
        contentValues.put("minute", minute);
        contentValues.put("worktype", worktype);
        db.insertOrThrow(DBHelper.TABLE_NAME_alarm, null, contentValues);
        //db.setTransactionSuccessful();
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.beginTransaction();
        // delete from Orders where Id = 7
        db.delete(DBHelper.TABLE_NAME_alarm, "id =?", new String[]{String.valueOf(id)});
        //db.setTransactionSuccessful();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.beginTransaction();
        // delete from Orders where Id = 7
        db.delete(DBHelper.TABLE_NAME_alarm, "id >=?", new String[]{String.valueOf(0)});
        //db.setTransactionSuccessful();
    }

    public void update(int id, int hour, int minute, String worktype) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.beginTransaction();
        // update Orders set OrderPrice = 800 where Id = 6
        ContentValues contentValues = new ContentValues();
        contentValues.put("hour", hour);
        contentValues.put("minute", minute);
        contentValues.put("worktype", worktype);
        db.update(DBHelper.TABLE_NAME_alarm, contentValues, "id =?", new String[]{String.valueOf(id)});
        //db.setTransactionSuccessful();
    }

    public List<Alarm> QueryAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // select * from Orders where CustomName = 'Bor'
        //Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute"}, "CustomName = ?", new String[]{"Bor"},
        // null, null, null);
        Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute", "worktype"}, null, null,
                null, null, null);
        List<Alarm> objs = new ArrayList<Alarm>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Alarm order = new Alarm(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
                objs.add(order);
            }
        }
        return objs;
    }

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    public Alarm QueryOne(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // select * from Orders where CustomName = 'Bor'
        //Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute"}, "CustomName = ?", new String[]{"Bor"},
        // null, null, null);
        Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute", "worktype"}, "id =?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor.moveToFirst()) {
            return new Alarm(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
        }
        return null;
    }

    public Alarm QueryOne(int h, int m) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // select * from Orders where CustomName = 'Bor'
        //Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute"}, "CustomName = ?", new String[]{"Bor"},
        // null, null, null);
        Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"id", "hour", "minute", "worktype"}, "hour =? and minute=?", new String[]{String.valueOf(h), String.valueOf(m)},
                null, null, null);
        if (cursor.moveToFirst()) {
            return new Alarm(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
        }
        return null;
    }

    /**
     * 获取总数
     *
     * @return
     */
    public int total() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // select count(Id) from Orders where Country = 'China'
        // Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"COUNT(id)"}, "Country = ?", new String[] {"China"},
        //        null, null, null);
        Cursor cursor = db.query(DBHelper.TABLE_NAME_alarm, new String[]{"COUNT(id)"}, null, null,
                null, null, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
}
