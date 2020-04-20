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

    /**
     * 打开数据库
     *
     * @return
     */
    public synchronized SQLiteDatabase getWritableDatabase() {
        int count = mOpenCounter.incrementAndGet();
        LogTool.LogE_DEBUG(TAG, " getWritableDatabase---> count: " + count);
        if (count == 1) {
            mDatabase = mHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    /**
     * 打开数据库
     *
     * @return
     */
    public synchronized SQLiteDatabase getReadableDatabase() {
        int count = mOpenCounter.incrementAndGet();
        LogTool.LogE_DEBUG(TAG, " getReadableDatabase---> count: " + count);
        if (count == 1) {
            mDatabase = mHelper.getReadableDatabase();
        }
        return mDatabase;
    }


    /**
     * 关闭数据库
     */
    public synchronized void closeDatabase() {
        int count = mOpenCounter.decrementAndGet();
        LogTool.LogE_DEBUG(TAG, " closeDatabase---> count: " + count);
        if (count == 0) {
            mDatabase.close();
        }
    }

    /**
     * 添加一条记录
     */
    public long insert() {
        LogTool.LogD(TAG, "START add============> ");
        getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "xxx");
        values.put("age", 28);
        values.put("address", "xxxxxx");
        long id = mDatabase.insert("student", null, values);
        LogTool.LogE_DEBUG(TAG, "add---> id: " + id + " thread: " + Thread.currentThread().getName());
        closeDatabase();
        LogTool.LogD(TAG, "END add============> ");
        return id;
    }

    /**
     * 查询
     */
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
        closeDatabase();
    }

    /**
     * 删除
     */
    public void delete() {
        getWritableDatabase();

        //删除数据库里的 数据
        int result = mDatabase.delete("student", "name" + "==?", new String[]{"xxx"});
        LogTool.LogE_DEBUG(TAG, "delete---> result: " + result);
        closeDatabase();
    }
}
