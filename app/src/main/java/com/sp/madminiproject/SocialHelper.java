package com.sp.madminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SocialHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sociallist.db";
    private static final int SCHEMA_VERSION = 2;

    public SocialHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SocialHelper", "onCreate called");
        db.execSQL("CREATE TABLE social_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " social_name TEXT, social_age TEXT, social_relationship TEXT, medicalHistoryInput TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM social_table", null);
    }

    public void insert(String social_name, String social_age, String social_relationship, String medicalHistoryInput) {
        ContentValues cv = new ContentValues();
        cv.put("social_name", social_name);
        cv.put("social_age", social_age);
        cv.put("social_relationship", social_relationship);
        cv.put("medicalHistoryInput", medicalHistoryInput);

        getWritableDatabase().insert("social_table", null, cv);
    }
}
