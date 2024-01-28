package com.sp.madminiproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddSocialFragment extends Fragment {

    private EditText social_name, social_age, social_relationship, medicalHistoryInput;
    private Button addButton;
    private RecyclerView recyclerView;
    private SocialAdapter socialAdapter;
    private List<socialModel> socialList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_social, container, false);

        // Initialize views
        social_name = view.findViewById(R.id.social_name_input);
        social_age = view.findViewById(R.id.social_age_input);
        social_relationship = view.findViewById(R.id.social_relationship_input);
        medicalHistoryInput = view.findViewById(R.id.social_medicalhistory_input);
        addButton = view.findViewById(R.id.socialadd2);

        socialList = new ArrayList<>();

        // Find the RecyclerView in the main layout (assuming it's in the main layout)
        recyclerView = getActivity().findViewById(R.id.socialRecyclerView);

        socialAdapter = new SocialAdapter(socialList, getContext());

        // Set a click listener for the "Add" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSocial();
                replaceFragment(new ProfileFragment());
            }
        });

        return view;
    }

    private void addSocial() {
        // Get data from input fields
        String name = social_name.getText().toString();
        String age = social_age.getText().toString();
        String relationship = social_relationship.getText().toString();

        // Create a new profileModel instance
        socialModel newSocial = new socialModel(name, age, relationship);

        // Add the new profile to the list
        socialList.add(newSocial);

        // Notify the adapter that the data set has changed
        socialAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(socialAdapter);
        socialAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(socialList.size() - 1);

        // Clear input fields
        clearInputFields();
    }

    private void clearInputFields() {
        social_name.setText("");
        social_age.setText("");
        social_relationship.setText("");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
