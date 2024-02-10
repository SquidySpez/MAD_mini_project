package com.sp.madminiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderRecyclerViewAdapter extends RecyclerView.Adapter<ReminderRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<ReminderModel> reminderModels;
    ReminderHelper db;

    public ReminderRecyclerViewAdapter(Context context, ArrayList<ReminderModel> reminderModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.reminderModels = reminderModels;
        this.recyclerViewInterface = recyclerViewInterface;
        this.db = new ReminderHelper(context);
    }

    @NonNull
    @Override
    public ReminderRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reminderrow, parent, false);

        return new ReminderRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvDate.setText(reminderModels.get(position).getReminder_date());
        holder.tvTime.setText(reminderModels.get(position).getReminder_time());
        holder.tvDescription.setText(reminderModels.get(position).getReminder_description());
    }

    @Override
    public int getItemCount() {
        return reminderModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime, tvDescription;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.reminder_date);
            tvTime = itemView.findViewById(R.id.reminder_time);
            tvDescription = itemView.findViewById(R.id.reminder_description);
            deleteButton = itemView.findViewById(R.id.deleteButton);

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

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ReminderModel reminder = reminderModels.get(position);
                        deleteItem(reminder.getId(), position);
                    }
                }
            });
        }

        private void deleteItem(long reminderId, int position) {
            db.deleteReminder(reminderId);
            reminderModels.remove(position);
            notifyItemRemoved(position);
        }
    }
}