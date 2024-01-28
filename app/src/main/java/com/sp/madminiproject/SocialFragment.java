package com.sp.madminiproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SocialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        // Find the "Add Profile" button by ID
        Button addButton = view.findViewById(R.id.socialadd1);

        // Set a click listener for the button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddSocialFragment());
            }
        });

        return view;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        public ImageView socialImage;
        public TextView socialName;
        public TextView socialAge;
        public TextView socialRelationship;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            socialImage = itemView.findViewById(R.id.social_img);
            socialName = itemView.findViewById(R.id.social_name);
            socialAge = itemView.findViewById(R.id.social_age);
            socialRelationship = itemView.findViewById(R.id.social_relationship);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}