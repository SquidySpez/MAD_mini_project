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

public class AddProfileFragment extends Fragment {

    private EditText profile_name, profile_age, profile_relationship, medicalHistoryInput;
    private Button addButton;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private List<profileModel> profileList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_profile, container, false);

        // Initialize views
        profile_name = view.findViewById(R.id.profile_name_input);
        profile_age = view.findViewById(R.id.profile_age_input);
        profile_relationship = view.findViewById(R.id.profile_relationship_input);
        medicalHistoryInput = view.findViewById(R.id.profile_medicalhistory_input);
        addButton = view.findViewById(R.id.profileadd2);

        profileList = new ArrayList<>();

        // Find the RecyclerView in the main layout (assuming it's in the main layout)
        recyclerView = getActivity().findViewById(R.id.profileRecyclerView);

        profileAdapter = new ProfileAdapter(profileList, getContext());

        // Set a click listener for the "Add" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProfile();
                replaceFragment(new ProfileFragment());
            }
        });

        return view;
    }

    private void addProfile() {
        // Get data from input fields
        String name = profile_name.getText().toString();
        String age = profile_age.getText().toString();
        String relationship = profile_relationship.getText().toString();

        // Create a new profileModel instance
        profileModel newProfile = new profileModel(name, age, relationship);

        // Add the new profile to the list
        profileList.add(newProfile);

        // Notify the adapter that the data set has changed
        profileAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(profileList.size() - 1);

        // Clear input fields
        clearInputFields();
    }

    private void clearInputFields() {
        profile_name.setText("");
        profile_age.setText("");
        profile_relationship.setText("");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
