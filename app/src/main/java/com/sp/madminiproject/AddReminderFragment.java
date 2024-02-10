package com.sp.madminiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddReminderFragment extends Fragment {

    private EditText reminder_date, reminder_description;
    private Spinner reminder_time;
    private Button addButton;
    private RecyclerView recyclerView;
    private ReminderRecyclerViewAdapter reminderAdapter;
    private List<ReminderModel> reminderList;

    private ReminderHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);

        db = new ReminderHelper(getContext()); // Pass the context here

        addButton = view.findViewById(R.id.reminderadd2);
        addButton.setOnClickListener(onAddClickListener);

        reminder_date = view.findViewById(R.id.reminder_date_input);
        reminder_time = view.findViewById(R.id.reminder_time_input);

        // Set up the Spinner with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.reminder_time_options, // Define this array in your resources
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminder_time.setAdapter(adapter);

        reminder_description = view.findViewById(R.id.reminder_description_input);

        // Attach OnClickListener to the reminder_date EditText
        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        return view;
    }

    private View.OnClickListener onAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String date = reminder_date.getText().toString();
            String time = reminder_time.getSelectedItem().toString();
            String description = reminder_description.getText().toString();

            db.insert(date, time, description);
            Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_LONG).show();

            ReminderFragment reminderFragment = new ReminderFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.idkbro, reminderFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    };

    public void showDatePickerDialog(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getChildFragmentManager(), "datePicker");
    }
}
