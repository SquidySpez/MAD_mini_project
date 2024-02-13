package com.sp.madminiproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        // Find the button by its ID
        Button bookButton = findViewById(R.id.bookbutton);

        // Set an OnClickListener for the button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On button click, start the new activity (replace YourOtherActivity.class with the actual class name)
                Intent intent = new Intent(BookingAppointment.this, Payment.class);
                startActivity(intent);
            }
        });
    }
}