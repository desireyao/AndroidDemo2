package com.yaoh.AndroidDemo2.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yaoh.AndroidDemo2.utils.log.LogTool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yaoh on 2019/3/4
 */
public class DBManager {

    private static final String TAG = "DBManager";

    private AppDBHelper mHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private AtomicInteger mOpenCounter = new AtomicInteger();

    public DBManager(Context context) {
        mHelper = AppDBHelper.get(context);
        this.mContext = context;
    }


    public synchronized SQLiteDatabase getWritableDatabase() {
        int count = mOpenCounter.incrementAndGet();
        LogTool.LogE_DEBUG(TAG, "getWritableDatabase---> count: " + count);
        if (count == 1) {
            // Opening new database
            mDatabase = mHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        int count = mOpenCounter.incrementAndGet();
        LogTool.LogE_DEBUG(TAG, "getReadableDatabase---> count: " + count);
        if (count == 1) {
            // Opening new database
            mDatabase = mHelper.getReadableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        int count = mOpenCounter.decrementAndGet();
        LogTool.LogE_DEBUG(TAG, "closeDatabase---> count: " + count);
        if (count == 0) {
            // Closing database
            mDatabase.close();
        }
    }

    /**
     * 添加一条记录
     */
    public long add() {
        getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "yaoh");
        values.put("age", 28);
        values.put("address", "shanghaipudong");
        long id = mDatabase.insert("student", null, values);
//        mDatabase.close();
        LogTool.LogE_DEBUG(TAG, "add---> id: " + id + " currentThreadName: " + Thread.currentThread().getName());
        closeDatabase();
        return id;
    }

    public void query() {
        getReadableDatabase();

        //创建游标
        Cursor cursor = mDatabase.rawQuery("select * from student ", null);
        cursor.moveToFirst();
        StringBuffer stringBuffer = new StringBuffer();
        do {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            stringBuffer.append(name + "-");
        } while (cursor.moveToNext());
        LogTool.LogE_DEBUG(TAG, "query---> currentThreadName: " + Thread.currentThread().getName());
        cursor.close();
//        mDatabase.close();
        closeDatabase();
    }
}
