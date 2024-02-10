package com.sp.madminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReminderHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reminderlist.db";
    private static final int SCHEMA_VERSION = 2;

    public ReminderHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("ReminderHelper", "onCreate called");
        db.execSQL("CREATE TABLE reminder_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " reminder_date TEXT, reminder_time TEXT, reminder_description TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM reminder_table", null);
    }

    public void insert(String reminder_date, String reminder_time, String reminder_description) {
        ContentValues cv = new ContentValues();
        cv.put("reminder_date", reminder_date);
        cv.put("reminder_time", reminder_time);
        cv.put("reminder_description", reminder_description);

        getWritableDatabase().insert("reminder_table", null, cv);
    }

    public void deleteReminder(long reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("reminder_table", "_id=?", new String[]{String.valueOf(reminderId)});
        db.close();
    }
}
