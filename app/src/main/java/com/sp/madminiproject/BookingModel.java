package com.sp.madminiproject;

public class BookingModel {
    String booking_date;
    String booking_time;

    long _id;

    // Updated constructor to include the id parameter
    public BookingModel(String booking_date, String booking_time, long _id) {
        this.booking_date = booking_date;
        this.booking_time = booking_time;
        this._id = _id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public long getId() {
        return _id;
    }
}
