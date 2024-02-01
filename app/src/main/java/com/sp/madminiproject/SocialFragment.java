package com.sp.madminiproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SocialFragment extends Fragment implements RecyclerViewInterface {

    private ArrayList<SocialModel> socialModels = new ArrayList<>();
    private SocialHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        db = new SocialHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.socialRecyclerView);

        SocialRecyclerViewAdapter adapter = new SocialRecyclerViewAdapter(getContext(), socialModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button addButton = view.findViewById(R.id.socialadd1);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddSocialFragment());
            }
        });
        setUpSocialModels();

        return view;
    }

    private void setUpSocialModels() {
        socialModels.clear();
        Cursor cursor = db.getAllData();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("social_name"));
                String age = cursor.getString(cursor.getColumnIndexOrThrow("social_age"));
                String relationship = cursor.getString(cursor.getColumnIndexOrThrow("social_relationship"));

                socialModels.add(new SocialModel(name, age, relationship));

            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(int position) {
    }
}