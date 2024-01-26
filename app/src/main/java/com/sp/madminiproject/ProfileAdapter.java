package com.sp.madminiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<profileModel> profileList;
    private Context context;

    public ProfileAdapter(List<profileModel> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilerow, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        profileModel profile = profileList.get(position);

        // Set data to the views in the ViewHolder
        // Note: You need to handle the image byte array here
        // holder.profileImage.setImageResource(/* Set image resource */);
        holder.profileName.setText(profile.profile_name);
        holder.profileAge.setText(profile.profile_age);
        holder.profileRelationship.setText(profile.profile_relationship);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView profileName;
        public TextView profileAge;
        public TextView profileRelationship;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_img);
            profileName = itemView.findViewById(R.id.profile_name);
            profileAge = itemView.findViewById(R.id.profile_age);
            profileRelationship = itemView.findViewById(R.id.profile_relationship);
        }
    }
}
