package com.sp.madminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsFragment extends AppCompatActivity {

    private TextView ChangeEmail;
    private TextView ChangePassword;
    private TextView Logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        ChangeEmail = findViewById(R.id.changeEmail);
        ChangePassword = findViewById(R.id.changePass);

        ChangeEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsFragment.this, ChangeEmail.class));
            }
        });

        ChangePassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsFragment.this, ChangePassword.class));
            }
        });


    }
}