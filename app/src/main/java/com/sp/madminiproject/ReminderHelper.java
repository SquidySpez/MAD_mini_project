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

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, reminder_date, reminder_time, reminder_description FROM reminder_table", null);
    }

    public long insert(String reminder_date, String reminder_time, String reminder_description) {
        ContentValues cv = new ContentValues();
        cv.put("reminder_date", reminder_date);
        cv.put("reminder_time", reminder_time);
        cv.put("reminder_description", reminder_description);

        SQLiteDatabase db = getWritableDatabase();

        long insertedId = db.insert("reminder_table", null, cv);

        Log.d("ReminderHelper", "Inserted item with ID: " + insertedId);

        db.close();

        return insertedId;
    }

    public void deleteReminder(long insertedId) {
        Log.d("ReminderHelper", "Deleting reminder with ID: " + insertedId);

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int rowsDeleted = db.delete("reminder_table", "_id=?", new String[]{String.valueOf(insertedId)});
            Log.d("ReminderHelper", "Rows deleted: " + rowsDeleted);

            if (rowsDeleted < 1) {
                Log.e("ReminderHelper", "Error deleting reminder with ID: " + insertedId);
            }
        } catch (Exception e) {
            Log.e("ReminderHelper", "Exception deleting reminder", e);
        } finally {
            db.close();
        }
    }

    public void markReminderAsCompleted(long reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();

        createTableIfNotExists(db);

        if (isTableExists(db, "completed_reminder_table")) {
            Cursor cursor = db.rawQuery("SELECT * FROM reminder_table WHERE _id=?", new String[]{String.valueOf(reminderId)});

            if (cursor != null && cursor.moveToFirst()) {
                try {
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("reminder_date"));
                    String time = cursor.getString(cursor.getColumnIndexOrThrow("reminder_time"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("reminder_description"));

                    Log.d("ReminderHelper", "Original Reminder: Date=" + date + ", Time=" + time + ", Description=" + description);

                    // Insert the completed reminder into completed_reminder_table
                    ContentValues completedValues = new ContentValues();
                    completedValues.put("reminder_date", date);
                    completedValues.put("reminder_time", time);
                    completedValues.put("reminder_description", description);

                    long completedId = db.insert("completed_reminder_table", null, completedValues);
                    Log.d("ReminderHelper", "Inserted completed reminder with ID: " + completedId);
                } finally {
                    cursor.close();
                }

                // Delete the completed reminder from reminder_table
                int rowsDeleted = db.delete("reminder_table", "_id=?", new String[]{String.valueOf(reminderId)});
                Log.d("ReminderHelper", "Rows deleted from reminder_table: " + rowsDeleted);
            } else {
                Log.e("ReminderHelper", "Error retrieving reminder with ID: " + reminderId);
            }
        } else {
            Log.e("ReminderHelper", "Table completed_reminder_table does not exist.");
        }

        db.close();
    }

    private void createTableIfNotExists(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS completed_reminder_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " reminder_date TEXT, reminder_time TEXT, reminder_description TEXT);");
        Log.d("ReminderHelper", "Table completed_reminder_table created or already exists.");
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?", new String[]{tableName});
        if (cursor != null) {
            boolean tableExists = cursor.getCount() > 0;
            cursor.close();
            Log.d("ReminderHelper", "Table " + tableName + " exists: " + tableExists);
            return tableExists;
        }
        Log.d("ReminderHelper", "Table " + tableName + " does not exist.");
        return false;
    }
}
