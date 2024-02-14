package com.sp.madminiproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompletedReminderRecyclerViewAdapter extends RecyclerView.Adapter<CompletedReminderRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CompletedReminderModel> completedreminderModels;
    private RecyclerViewInterface recyclerViewInterface;  // Add this line

    // Updated constructor to include recyclerViewInterface parameter
    public CompletedReminderRecyclerViewAdapter(Context context, ArrayList<CompletedReminderModel> completedreminderModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.completedreminderModels = completedreminderModels;
        this.recyclerViewInterface = recyclerViewInterface;  // Add this line
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reminderrow, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedReminderRecyclerViewAdapter.MyViewHolder holder, int position) {
        CompletedReminderModel reminder = completedreminderModels.get(position);

        holder.tvDate.setText(reminder.getReminder_date());
        holder.tvTime.setText(reminder.getReminder_time());
        holder.tvDescription.setText(reminder.getReminder_description());

        Log.d("CompletedReminderAdapter", "Binding item with ID: " + reminder.getId()
                + ", Date: " + reminder.getReminder_date()
                + ", Time: " + reminder.getReminder_time()
                + ", Description: " + reminder.getReminder_description());
    }

    @Override
    public int getItemCount() {
        return completedreminderModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime, tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.reminder_date);
            tvTime = itemView.findViewById(R.id.reminder_time);
            tvDescription = itemView.findViewById(R.id.reminder_description);

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
