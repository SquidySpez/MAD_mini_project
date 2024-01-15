package com.sp.madminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Button loginButton = findViewById(R.id.login_button);
        Button signUpButton = findViewById(R.id.signup_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the "Login" button is clicked, start the LoginActivity
                startActivity(new Intent(Startup.this, Login.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the "Sign Up" button is clicked, start the SignUpActivity
                startActivity(new Intent(Startup.this, Signup.class));
            }
        });
    }
}