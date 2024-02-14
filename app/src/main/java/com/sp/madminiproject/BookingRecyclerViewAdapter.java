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

public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<BookingRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<BookingModel> bookingModels;
    BookingHelper db;

    public BookingRecyclerViewAdapter(Context context, ArrayList<BookingModel> bookingModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.bookingModels = bookingModels;
        this.recyclerViewInterface = recyclerViewInterface;
        this.db = new BookingHelper(context);
    }


    @NonNull
    @Override
    public BookingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bookingrow, parent, false);

        return new BookingRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingRecyclerViewAdapter.MyViewHolder holder, int position) {
        BookingModel booking = bookingModels.get(position);
        Log.d("BookingRecyclerView", "Binding item with ID: " + booking.getId());

        holder.tvDate.setText(booking.getBooking_date());
        holder.tvTime.setText(booking.getBooking_time());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    long bookingId = booking.getId();
                    Log.d("BookingRecyclerView", "Clicked item with ID: " + bookingId);
                    deleteItem(bookingId, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.booking_date);
            tvTime = itemView.findViewById(R.id.booking_time);
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
                        BookingModel booking = bookingModels.get(position);
                        deleteItem(booking.getId(), position);
                    }
                }
            });
        }
    }
    private void deleteItem(long bookingId, int position) {
        Log.d("BookingRecyclerView", "Deleting item with ID: " + bookingId); // Log the ID
        db.deleteBooking(bookingId);
        bookingModels.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(long bookingId) {
        for (int i = 0; i < bookingModels.size(); i++) {
            BookingModel model = bookingModels.get(i);
            if (model.getId() == bookingId) {
                bookingModels.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
