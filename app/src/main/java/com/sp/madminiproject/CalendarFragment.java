package com.sp.madminiproject;

import android.content.Intent;
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

public class CalendarFragment extends Fragment implements RecyclerViewInterface {

    private ArrayList<BookingModel> bookingList = new ArrayList<>();
    private BookingHelper db;
    private BookingRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        db = new BookingHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.bookingRecyclerView);

        adapter = new BookingRecyclerViewAdapter(getContext(), bookingList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button addButton = view.findViewById(R.id.newappoint);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Determine which button was clicked
                if (v.getId() == R.id.newappoint) {
                    Intent intent = new Intent(getActivity(), AddAppointmentMap.class);

                    // Start the activity
                    startActivity(intent);
                }
            }
        });

        setUpBookingModels();

        return view;
    }

    private void setUpBookingModels() {
        bookingList.clear();
        Cursor cursor = db.getAllData();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String bookingDate = cursor.getString(cursor.getColumnIndexOrThrow("booking_date"));
                String bookingTime = cursor.getString(cursor.getColumnIndexOrThrow("booking_time"));

                long bookingId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));

                bookingList.add(new BookingModel(bookingDate, bookingTime, bookingId));

            }

            cursor.close();
        }
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}
