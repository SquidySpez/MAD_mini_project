package com.sp.madminiproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.sp.madminiproject.OnCompleteButtonClickListener;

public class ReminderRecyclerViewAdapter extends RecyclerView.Adapter<ReminderRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private final OnCompleteButtonClickListener onCompleteButtonClickListener;

    Context context;
    ArrayList<ReminderModel> reminderModels;
    ReminderHelper db;

    public ReminderRecyclerViewAdapter(Context context, ArrayList<ReminderModel> reminderModels, RecyclerViewInterface recyclerViewInterface, OnCompleteButtonClickListener onCompleteButtonClickListener) {
        this.context = context;
        this.reminderModels = reminderModels;
        this.recyclerViewInterface = recyclerViewInterface;
        this.db = new ReminderHelper(context);
        this.onCompleteButtonClickListener = onCompleteButtonClickListener;
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
        ReminderModel reminder = reminderModels.get(position);
        Log.d("ReminderRecyclerView", "Binding item with ID: " + reminder.getId());

        holder.tvDate.setText(reminder.getReminder_date());
        holder.tvTime.setText(reminder.getReminder_time());
        holder.tvDescription.setText(reminder.getReminder_description());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    long reminderId = reminder.getId();
                    Log.d("ReminderRecyclerView", "Clicked item with ID: " + reminderId);
                    deleteItem(reminderId, position);
                }
            }
        });

        holder.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ReminderModel reminder = reminderModels.get(position);
                    if (onCompleteButtonClickListener != null) {
                        onCompleteButtonClickListener.onCompleteButtonClick(reminder.getId());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime, tvDescription;
        Button deleteButton, completeButton;;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.reminder_date);
            tvTime = itemView.findViewById(R.id.reminder_time);
            tvDescription = itemView.findViewById(R.id.reminder_description);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            completeButton = itemView.findViewById(R.id.completeButton);

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
    }
    private void deleteItem(long reminderId, int position) {
        Log.d("ReminderRecyclerView", "Deleting item with ID: " + reminderId); // Log the ID
        db.deleteReminder(reminderId);
        reminderModels.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(long reminderId) {
        for (int i = 0; i < reminderModels.size(); i++) {
            ReminderModel model = reminderModels.get(i);
            if (model.getId() == reminderId) {
                reminderModels.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}