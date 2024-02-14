package com.sp.madminiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SocialFragment2 extends Fragment {

    private TextView nameTextView, ageTextView, relationshipTextView, medicalHistoryTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social2, container, false);

        nameTextView = view.findViewById(R.id.textView9);
        ageTextView = view.findViewById(R.id.textView13);
        relationshipTextView = view.findViewById(R.id.textView15);
        medicalHistoryTextView = view.findViewById(R.id.textView17);

        // Retrieve data from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String age = bundle.getString("age");
            String relationship = bundle.getString("relationship");
            String medicalHistory = bundle.getString("medicalhistory");

            // Set the data to TextViews
            nameTextView.setText(name);
            ageTextView.setText(age);
            relationshipTextView.setText(relationship);
            medicalHistoryTextView.setText(medicalHistory);
        }

        return view;
    }
}
