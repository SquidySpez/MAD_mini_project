package com.sp.madminiproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private EditText newpass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle("Change Password");
        auth = FirebaseAuth.getInstance();

        newpass = findViewById(R.id.newPassword);
        Button onChangePassButton = findViewById(R.id.changePassButton);

        onChangePassButton.setOnClickListener(v -> {
            String newPassword = newpass.getText().toString();

            if (!newPassword.isEmpty()) {
                changeUserPassword(newPassword);
            } else {
                // Handle the case where the new password is empty
                Log.e(TAG, "New password cannot be empty");
                Toast.makeText(ChangePassword.this, "New password cannot be empty", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void changeUserPassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Log.d(TAG, "User UID: " + user.getUid());

            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated successfully.");
                                Toast.makeText(ChangePassword.this, "Changes Successful", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.e(TAG, "Failed to update user password: ", task.getException());
                                Toast.makeText(ChangePassword.this, "Failed to update user password: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

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
}


