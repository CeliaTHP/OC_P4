package com.example.mareu.ui.list;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingsApi;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder.ViewHolder> {

    private final List<Meeting> mMeetings;
    private Context context;
    private MeetingsApi meetingsApi;

    public MeetingAdapter(Context context,MeetingsApi meetingsApi,List<Meeting> meetings) {
        this.context = context;
        this.meetingsApi = meetingsApi;
        this.mMeetings = meetings;
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
        String fullTitle = holder.itemView.getContext().getString(R.string.meeting_title, name, time, room);
        holder.itemLayoutBinding.meetingTitle.setText(fullTitle);

        holder.itemLayoutBinding.meetingsDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingsApi.deleteMeeting(meeting); //null pointer
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

}

