package com.example.cm_passwords.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "cmPassword.db";
    private static final String TABLE_MAIN_PASSWORD = "t_mainPassword";
    private static final String TABLE_PASSWORD = "t_password";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PASSWORD + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "site TEXT NOT NULL)" );
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAIN_PASSWORD + "(" +
                "password TEXT PRIMARY KEY)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PASSWORD);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAIN_PASSWORD);
        onCreate(sqLiteDatabase);
    }
}
