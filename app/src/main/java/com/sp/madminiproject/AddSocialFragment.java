package com.sp.madminiproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddSocialFragment extends Fragment {

    private EditText social_name, social_age, social_relationship, medicalHistoryInput;
    private Button addButton;
    private RecyclerView recyclerView;
    private SocialRecyclerViewAdapter socialAdapter;
    private List<SocialModel> socialList;

    private SocialHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_social, container, false);

        db = new SocialHelper(getContext()); // Pass the context here

        addButton = view.findViewById(R.id.socialadd2);
        addButton.setOnClickListener(onAddClickListener);

        social_name = view.findViewById(R.id.social_name_input);
        social_age = view.findViewById(R.id.social_age_input);
        social_relationship = view.findViewById(R.id.social_relationship_input);
        medicalHistoryInput = view.findViewById(R.id.social_medicalhistory_input);

        return view;
    }

    private View.OnClickListener onAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = social_name.getText().toString();
            String age = social_age.getText().toString();
            String relationship = social_relationship.getText().toString();
            String medicalhistory = medicalHistoryInput.getText().toString();

            db.insert(name, age, relationship, medicalhistory);
            Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_LONG).show();

            SocialFragment socialFragment = new SocialFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, socialFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    };
}
