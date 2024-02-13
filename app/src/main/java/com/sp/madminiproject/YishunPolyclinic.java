package com.sp.madminiproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class YishunPolyclinic extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yishun_polyclinic);

        // Find the button by its ID
        button = findViewById(R.id.button);

        // Set an OnClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On button click, start the new activity (replace YourNewActivity.class with the actual class name)
                Intent intent = new Intent(YishunPolyclinic.this, BookingAppointment.class);
                startActivity(intent);
            }
        });
    }
}

