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

    public ArrayList<Password> getPasswords(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Password> passwords = new ArrayList<Password>();
        Password password = null;

        Cursor passwordCursor = null;
        passwordCursor = db.rawQuery("SELECT * FROM t_password ORDER BY site ASC", null);
        if(passwordCursor.moveToFirst()){
            do{
                password = new Password();
                password.setSite(passwordCursor.getString(3));
                password.setUser(passwordCursor.getString(1));
                password.setPassword(passwordCursor.getString(2));
                password.setId(passwordCursor.getInt(0));
                passwords.add(password);
            } while (passwordCursor.moveToNext());
        }

        passwordCursor.close();
        return passwords;
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

    public void deletePassword(Integer id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("DELETE FROM t_password WHERE id = '" + id + "'");
    }

    public void editPasswords(String site, String user, String password, Integer id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("UPDATE t_password SET site = '" + site + "', user = '" + user + "', password = '" + password + "' WHERE id = '" + id + "' ");
    }

    public int[] measureStrength(){
        ArrayList<Password> passwords1 = getPasswords();
        Password[] passwords = passwords1.toArray(new Password[0]);
        int[] cuantityByStrength = new int[5];
        for(int i = 0; i<passwords.length;i++){
            int strength = passwords[i].measureStrength();
            cuantityByStrength[strength]++;
        }

        return cuantityByStrength;
    }
}
