package com.sp.madminiproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements RecyclerViewInterface {
    private ArrayList<profileModel> placeModels = new ArrayList<>();
    private PlaceHelper db;
}