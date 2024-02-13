package com.sp.madminiproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeEmail extends AppCompatActivity {

    private TextView userEmailTextView;
    private EditText newEmailEditText;
    private FirebaseAuth auth;
    private Button onChangeEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        setTitle("Change Email");
        auth = FirebaseAuth.getInstance();

        userEmailTextView = findViewById(R.id.CurrentEmailView);
        newEmailEditText = findViewById(R.id.newEmail);
        onChangeEmailButton = findViewById(R.id.changeEmailButton);

        String userEmail = getUserEmailFromSharedPreferences();
        // Show current email
        userEmailTextView.setText(userEmail);

        onChangeEmailButton.setOnClickListener(v -> {
            String newEmail = newEmailEditText.getText().toString();

            if (!newEmail.isEmpty()) {
                changeUserEmail(newEmail);
            } else {
                // Handle the case where the new email is empty
                Log.e(TAG, "New email cannot be empty");
                Toast.makeText(ChangeEmail.this, "New email cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeUserEmail(String newEmail) {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Log.d(TAG, "User UID: " + user.getUid());

            user.updateEmail(newEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User email address updated successfully.");
                                Toast.makeText(ChangeEmail.this, "Changes Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e(TAG, "Failed to update user email: ", task.getException());
                                Toast.makeText(ChangeEmail.this, "Failed to update user email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                // Handle the failure, display an error message, etc.
                            }
                        }
                    });
        } else {
            Log.e(TAG, "User is null. Authentication required.");
            // Handle the case where the user is not authenticated
            // You might want to redirect the user to the login screen or prompt for re-authentication
        }
    }

    private String getUserEmailFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        return preferences.getString("USER_EMAIL", "");
    }
}
