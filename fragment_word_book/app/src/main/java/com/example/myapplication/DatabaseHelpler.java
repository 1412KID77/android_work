package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DatabaseHelpler extends SQLiteOpenHelper {

    //数据库名字
    private final static String DATABASE_NAME = "wordsdb";
    //数据库版本
    private final static int DATABASE_VERSION = 1;

    //创建表
    final String sql = "CREATE TABLE " + word.w.TABLE_NAME +  " (" +  word.w.COLUMN_NAME_WORD + " TEXT PRIMARY KEY,"  +
            word.w.COLUMN_NAME_V + " TEXT,"
            + word.w.COLUMN_NAME_NOTES + " TEXT," +
            word.w.COLUMN_NAME_EXAMPLE + " TEXT" + " )";
    //删除表
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS " + word.w.TABLE_NAME;

    public DatabaseHelpler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelpler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//当数据库升级时被调用，首先删除旧表，然后调用OnCreate()创建新表
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }
}
