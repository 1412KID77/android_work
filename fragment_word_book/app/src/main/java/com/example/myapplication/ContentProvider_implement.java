package com.example.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProvider_implement extends ContentProvider {


//文本过滤器  表明是哪个数据表

    public  static final String AUTHORITY = "com.example.myapplication";

    private static final int TABLE_WORD = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + word.w.TABLE_NAME);

    public static UriMatcher uriMathcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{

        uriMathcher.addURI(AUTHORITY, word.w.TABLE_NAME, TABLE_WORD);
    }

    private DatabaseHelpler databaseHelpler;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    public boolean onCreate() {
        databaseHelpler = new DatabaseHelpler(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//        databaseHelpler = new DatabaseHelpler(getContext());
//        sqLiteDatabase = databaseHelpler.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query(word.w.TABLE_NAME,null,null,null,null,null,null, null);
        sqLiteDatabase = databaseHelpler.getReadableDatabase();
        Cursor cursor;

        System.out.println("------------------------------------------------------------------------------测试中");

        switch (uriMathcher.match(uri)){
            case TABLE_WORD:
                cursor = sqLiteDatabase.query(word.w.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + uriMathcher.match(uri));
        }



        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        sqLiteDatabase = databaseHelpler.getWritableDatabase();
        databaseHelpler = new DatabaseHelpler(getContext());
        switch(uriMathcher.match(uri)){
            case TABLE_WORD:
                long rowID = sqLiteDatabase.insert(word.w.TABLE_NAME, null, values);
                Uri retUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                return retUri;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        sqLiteDatabase = databaseHelpler.getWritableDatabase();
        switch (uriMathcher.match(uri)){

            case TABLE_WORD:
                count = sqLiteDatabase.delete(word.w.TABLE_NAME, selection, selectionArgs);
                return count;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        sqLiteDatabase = databaseHelpler.getWritableDatabase();
        int count;
        switch (uriMathcher.match(uri)){
            case TABLE_WORD:
                count = sqLiteDatabase.update(word.w.TABLE_NAME,values, selection, selectionArgs);
                return count;
        }

        return 0;
    }
}
