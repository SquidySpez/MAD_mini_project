package com.sp.madminiproject;

import android.content.Intent;
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

public class AddBookingFragment extends Fragment {

    private EditText appointment_date;
    private Spinner appointment_time;
    private Button addButton;

    private BookingHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_booking_appointment, container, false);

        db = new BookingHelper(getContext());

        addButton = view.findViewById(R.id.bookbutton);
        addButton.setOnClickListener(onAddClickListener);

        appointment_date = view.findViewById(R.id.appointment_date_input);
        appointment_time = view.findViewById(R.id.appointment_time_input);

        // Set up the Spinner with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.appointment_time_options, // Define this array in your resources
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointment_time.setAdapter(adapter);

        appointment_date.setOnClickListener(new View.OnClickListener() {
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
            String date = appointment_date.getText().toString();
            String time = appointment_time.getSelectedItem().toString();

            db.insert(date, time);
            Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getActivity(), Payment.class);
            startActivity(intent);

        }
    };

    public void showDatePickerDialog(View v) {
        DatePickerFragmentBooking datePickerFragment = new DatePickerFragmentBooking();
        datePickerFragment.show(getChildFragmentManager(), "datePicker");
    }
}

