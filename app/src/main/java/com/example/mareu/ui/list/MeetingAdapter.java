package com.example.mareu.ui.list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityListBinding;
import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingsApi;
import com.example.mareu.service.MeetingsApi;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder.ViewHolder> {

    private final List<Meeting> mMeetings;
    Context context;
    MeetingsApi meetingsApi;

    public MeetingAdapter(Context context,List<Meeting> meetings) {
        this.context = context;
        mMeetings = meetings;
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
        String time = meeting.getTime();
        String room = meeting.getRoom();
        String full_title = holder.itemView.getContext().getString(R.string.meeting_title, name, time, room);
        holder.itemLayoutBinding.meetingTitle.setText(full_title);

        holder.itemLayoutBinding.meetingsDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  meetingsApi.deleteMeeting(meeting); //null pointer
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

}

