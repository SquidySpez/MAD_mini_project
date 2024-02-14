package com.sp.madminiproject;

public class CompletedReminderModel {
    String reminder_date;
    String reminder_time;
    String reminder_description;
    long _id;

    public CompletedReminderModel(String reminder_date, String reminder_time, String reminder_description, long _id) {
        this.reminder_date = reminder_date;
        this.reminder_time = reminder_time;
        this.reminder_description = reminder_description;
        this._id = _id;
    }

    public String getReminder_date() {
        return reminder_date;
    }

    public String getReminder_time() {
        return reminder_time;
    }

    public String getReminder_description() {
        return reminder_description;
    }

    public long getId() {
        return _id;
    }
}
