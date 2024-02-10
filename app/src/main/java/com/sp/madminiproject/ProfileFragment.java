package com.sp.madminiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.cardview.widget.CardView;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView nameTextView, sexTextView, ageTextView, emailTextView, medicalHistoryTextView;
    private CardView nameCardView, sexCardView, ageCardView, emailCardView, medicalHistoryCardView;
    private Button updateInfoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize your views
        nameTextView = view.findViewById(R.id.textView9);
        sexTextView = view.findViewById(R.id.textView11);
        ageTextView = view.findViewById(R.id.textView13);
        emailTextView = view.findViewById(R.id.textView15);
        medicalHistoryTextView = view.findViewById(R.id.textView17);

        nameCardView = view.findViewById(R.id.cardView5);
        sexCardView = view.findViewById(R.id.cardView4);
        ageCardView = view.findViewById(R.id.cardView6);
        emailCardView = view.findViewById(R.id.cardView7);
        medicalHistoryCardView = view.findViewById(R.id.cardView8);

        updateInfoButton = view.findViewById(R.id.button2);
        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the update info button click
            }
        });

        // You can set the data in the TextViews here or fetch it from your data source

        return view;
    }
}
