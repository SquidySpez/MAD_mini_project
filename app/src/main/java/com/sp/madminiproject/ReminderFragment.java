package com.sp.madminiproject;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

public class ReminderFragment extends Fragment implements RecyclerViewInterface {

    private ArrayList<ReminderModel> reminderModels = new ArrayList<>();
    private ReminderHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);

        db = new ReminderHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.reminderRecyclerView);

        ReminderRecyclerViewAdapter adapter = new ReminderRecyclerViewAdapter(getContext(), reminderModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button addButton = view.findViewById(R.id.reminderadd1);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddReminderFragment());
            }
        });

        Button completeButton = view.findViewById(R.id.complete);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CompletedReminder());
            }
        });

        setUpReminderModels();

        return view;
    }

    private void setUpReminderModels() {
        reminderModels.clear();
        Cursor cursor = db.getAllData();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow("reminder_date"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("reminder_time"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("reminder_description"));

                reminderModels.add(new ReminderModel(date, time, description));

            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.idkbro, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}
