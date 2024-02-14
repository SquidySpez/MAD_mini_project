package com.sp.madminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookingHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bookinglist.db";
    private static final int SCHEMA_VERSION = 2;

    public BookingHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        createTableIfNotExists(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("BookingHelper", "onCreate called");
        db.execSQL("CREATE TABLE booking_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " booking_date TEXT, booking_time TEXT, completed INTEGER DEFAULT 0);");
        createTableIfNotExists(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, booking_date, booking_time FROM booking_table", null);
    }

    public long insert(String booking_date, String booking_time) {
        ContentValues cv = new ContentValues();
        cv.put("booking_date", booking_date);
        cv.put("booking_time", booking_time);

        SQLiteDatabase db = getWritableDatabase();

        long bookingId = db.insert("booking_table", null, cv);


        db.close();

        return bookingId;
    }

    public void deleteBooking(long insertedId) {
        Log.d("BookingHelper", "Deleting booking with ID: " + insertedId);

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int rowsDeleted = db.delete("booking_table", "_id=?", new String[]{String.valueOf(insertedId)});
            Log.d("BookingHelper", "Rows deleted: " + rowsDeleted);

            if (rowsDeleted < 1) {
                Log.e("BookingHelper", "Error deleting booking with ID: " + insertedId);
            }
        } catch (Exception e) {
            Log.e("BookingHelper", "Exception deleting booking", e);
        } finally {
            db.close();
        }
    }

    public void markBookingAsCompleted(long bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();

        createTableIfNotExists(db);

        if (isTableExists(db, "completed_booking_table")) {
            Cursor cursor = db.rawQuery("SELECT * FROM booking_table WHERE _id=?", new String[]{String.valueOf(bookingId)});

            if (cursor != null && cursor.moveToFirst()) {
                try {
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("booking_date"));
                    String time = cursor.getString(cursor.getColumnIndexOrThrow("booking_time"));

                    Log.d("BookingHelper", "Original Booking: Date=" + date + ", Time=" + time);

                    // Insert the completed booking into completed_booking_table
                    ContentValues completedValues = new ContentValues();
                    completedValues.put("booking_date", date);
                    completedValues.put("booking_time", time);

                    long completedId = db.insert("completed_booking_table", null, completedValues);
                    Log.d("BookingHelper", "Inserted completed booking with ID: " + completedId);
                } finally {
                    cursor.close();
                }

                // Delete the completed booking from booking_table
                int rowsDeleted = db.delete("booking_table", "_id=?", new String[]{String.valueOf(bookingId)});
                Log.d("BookingHelper", "Rows deleted from booking_table: " + rowsDeleted);
            } else {
                Log.e("BookingHelper", "Error retrieving booking with ID: " + bookingId);
            }
        } else {
            Log.e("BookingHelper", "Table completed_booking_table does not exist.");
        }

        db.close();
    }

    private void createTableIfNotExists(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS completed_booking_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " booking_date TEXT, booking_time TEXT);");
        Log.d("BookingHelper", "Table completed_booking_table created or already exists.");
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?", new String[]{tableName});
        if (cursor != null) {
            boolean tableExists = cursor.getCount() > 0;
            cursor.close();
            Log.d("BookingHelper", "Table " + tableName + " exists: " + tableExists);
            return tableExists;
        }
        Log.d("BookingHelper", "Table " + tableName + " does not exist.");
        return false;
    }
}
