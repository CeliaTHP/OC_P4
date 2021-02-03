package com.example.mareu.ui.list;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.callback.OnDeleteListener;
import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.utils.DisplayFormatter;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder.ViewHolder> {

    public List<Meeting> mMeetings;
    private final OnDeleteListener onDeleteListener;

    public MeetingAdapter(List<Meeting> meetings, OnDeleteListener onDeleteListener) {
        this.mMeetings = new ArrayList<>(meetings);
        this.onDeleteListener = onDeleteListener;
    }

    @NonNull
    @Override
    public MeetingViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder.ViewHolder holder, int position) {

        Meeting meeting = mMeetings.get(position);
        String name = meeting.getTitle();
        String startTime = DisplayFormatter.formatTimeToString(meeting.getStartTime());

        Room room = meeting.getRoom();
        List<String> attendees = meeting.getAttendees();
        int pic = room.getColor();

        String fullTitle = holder.itemView.getContext().getString(R.string.meeting_title, name, startTime, room.getName());
        holder.itemLayoutBinding.meetingTitle.setText(fullTitle);

        StringBuilder builder = new StringBuilder();
        for (String value : attendees) {
            builder.append(value);
        }
        String emails = TextUtils.join(", ", attendees);
        holder.itemLayoutBinding.meetingAttendees.setText(emails);
        holder.itemLayoutBinding.meetingImage.setImageResource(pic);

        holder.itemLayoutBinding.meetingsDeleteButton.setOnClickListener(v -> onDeleteListener.onDelete(meeting));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void updateData(List<Meeting> meetings) {
        mMeetings = new ArrayList<>(meetings);
        notifyDataSetChanged();
    }

    public void removeMeeting(Meeting meeting) {
        int index = mMeetings.indexOf(meeting);
        if (index != -1) {
            mMeetings.remove(index);
            notifyItemRemoved(index);
        }
    }
}

