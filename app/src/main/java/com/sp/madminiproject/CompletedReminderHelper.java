package com.sp.madminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CompletedReminderHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reminderlist.db";
    private static final int SCHEMA_VERSION = 2;

    public CompletedReminderHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        createTableIfNotExists(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("ReminderHelper", "onCreate called");
        db.execSQL("CREATE TABLE reminder_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " reminder_date TEXT, reminder_time TEXT, reminder_description TEXT, completed INTEGER DEFAULT 0);");
        createTableIfNotExists(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getAllCompletedReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, reminder_date, reminder_time, reminder_description FROM completed_reminder_table", null);
    }
    private void createTableIfNotExists(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS completed_reminder_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " reminder_date TEXT, reminder_time TEXT, reminder_description TEXT);");
    }
}