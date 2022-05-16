package com.example.cm_passwords.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.cm_passwords.entities.Password;

import java.util.ArrayList;

public class DbPassword extends DbHelper{

    Context context;

    public DbPassword(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertMainPassword(String password){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        db.insert(TABLE_MAIN_PASSWORD, null, contentValues);
    }

    public boolean isMainPassword(String password) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor passwordCursor = null;

        passwordCursor = db.rawQuery("SELECT * from t_mainPassword", null);
        passwordCursor.moveToFirst();
        if (passwordCursor.getString(0).equals(password)) {
            return true;
        }
        passwordCursor.close();
        return false;
    }

    public void insertPassword(String site, String user, String password){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("site", site);
        contentValues.put("user", user);
        contentValues.put("password", password);

        db.insert(TABLE_PASSWORD, null, contentValues);
    }

    public ArrayList<Password> showPasswords(){
        return null;
    }
}
