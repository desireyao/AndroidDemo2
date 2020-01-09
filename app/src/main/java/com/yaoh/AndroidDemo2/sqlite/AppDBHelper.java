package com.yaoh.AndroidDemo2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yaoh on 2019/3/4
 */
public class AppDBHelper extends SQLiteOpenHelper {

    private volatile static AppDBHelper mDBHeler;

    public static final String CREATE_TABLE_STUDENT = "create table student("
            + "id integer primary key autoincrement,"
            + " name text,"
            + " age integer,"
            + " address text)";


    public static AppDBHelper get(Context context) {
        if (mDBHeler == null) {
            synchronized (AppDBHelper.class) {
                if (mDBHeler == null) {
                    mDBHeler = new AppDBHelper(context);
                }
            }
        }
        return mDBHeler;
    }

    private AppDBHelper(Context context) {
        super(context, "DB_TEST.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
