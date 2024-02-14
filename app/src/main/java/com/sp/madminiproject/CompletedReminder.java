package com.sp.madminiproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompletedReminder extends Fragment implements RecyclerViewInterface {

    private ArrayList<CompletedReminderModel> completedreminderModels;
    private CompletedReminderRecyclerViewAdapter adapter;
    private CompletedReminderHelper dbHelper; // Add this line

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_completed_reminder, container, false);

        completedreminderModels = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.completedReminderRecyclerView);

        // Initialize dbHelper
        dbHelper = new CompletedReminderHelper(getContext());

        // Pass the fragment as the RecyclerViewInterface
        adapter = new CompletedReminderRecyclerViewAdapter(getContext(), completedreminderModels, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Call the updateCompletedReminders method to load data
        updateCompletedReminders();

        return view;
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }

    // Add this method to update completed reminders
    public void updateCompletedReminders() {
        // Clear existing data
        completedreminderModels.clear();

        // Reload completed reminders from the database
        Cursor cursor = dbHelper.getAllCompletedReminders();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow("reminder_date"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("reminder_time"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("reminder_description"));

                long completedId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));

                completedreminderModels.add(new CompletedReminderModel(date, time, description, completedId));
            } while (cursor.moveToNext());

            cursor.close();

            // Notify the adapter of the data change
            adapter.notifyDataSetChanged();
        }
    }
}
