package com.example.mareu.ui.list;


import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.callback.OnDeleteListener;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.utils.DisplayFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder.ViewHolder> {

    private List<Meeting> mMeetings;
    private final OnDeleteListener onDeleteListener;

    public MeetingAdapter(List<Meeting> meetings, OnDeleteListener onDeleteListener) {
        this.mMeetings = meetings;
        this.onDeleteListener = onDeleteListener;
    }


    @NonNull
    @Override
    public MeetingViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder.ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder.ViewHolder holder, int position) {

        Meeting meeting = mMeetings.get(position);
        String name = meeting.getTitle();
        String time = DisplayFormatter.formatTimeToString(meeting.getStartTime());


        Room room = meeting.getRoom();
        List<String> attendees = meeting.getAttendees();
        int pic = room.getColor();

        String fullTitle = holder.itemView.getContext().getString(R.string.meeting_title, name, time, room.getName());
        holder.itemLayoutBinding.meetingTitle.setText(fullTitle);

        StringBuilder builder = new StringBuilder();
        for (String value : attendees) {
            builder.append(value);
        }
        String emails = String.join(", ", attendees);
        holder.itemLayoutBinding.meetingAttendees.setText(emails);
        holder.itemLayoutBinding.meetingImage.setImageResource(pic);

        holder.itemLayoutBinding.meetingsDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDeleteListener.onDelete(position); //donner l'objet
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void updateData(List<Meeting> meetings) {
        mMeetings = meetings;
        notifyDataSetChanged();
    }

}

