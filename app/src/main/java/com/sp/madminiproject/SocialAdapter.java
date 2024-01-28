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

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ProfileViewHolder> {

    private List<socialModel> socialList;
    private Context context;

    public SocialAdapter(List<socialModel> socialList, Context context) {
        this.socialList = socialList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.socialrow, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        socialModel social = socialList.get(position);

        // Set data to the views in the ViewHolder
        // Note: You need to handle the image byte array here
        // holder.profileImage.setImageResource(/* Set image resource */);
        holder.socialName.setText(social.social_name);
        holder.socialAge.setText(social.social_age);
        holder.socialRelationship.setText(social.social_relationship);
    }

    @Override
    public int getItemCount() {
        return socialList.size();
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
}
