package com.sp.madminiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SocialRecyclerViewAdapter extends RecyclerView.Adapter<SocialRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<SocialModel> socialModels;

    public SocialRecyclerViewAdapter(Context context, ArrayList<SocialModel> socialModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.socialModels = socialModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public SocialRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.socialrow, parent, false);

        return new SocialRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(socialModels.get(position).getSocial_name());
        holder.tvAge.setText(socialModels.get(position).getSocial_age());
        holder.tvRelationship.setText(socialModels.get(position).getSocial_relationship());
    }

    @Override
    public int getItemCount() {
        return socialModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAge, tvRelationship;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvName = itemView.findViewById(R.id.social_name);
            tvAge = itemView.findViewById(R.id.social_age);
            tvRelationship = itemView.findViewById(R.id.social_relationship);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
