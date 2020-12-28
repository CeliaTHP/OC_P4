package com.example.mareu.ui.list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;
import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.MeetingService.MeetingsApi;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder.ViewHolder> {

    private final List<Meeting> mMeetings;
    private final MeetingsApi meetingsApi;
    private ActivityNewMeetingLinearBinding mBinding;


    public MeetingAdapter(MeetingsApi meetingsApi, List<Meeting> meetings) {
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
        Room room = meeting.getRoom();
        int pic = room.getColor();

        String fullTitle = holder.itemView.getContext().getString(R.string.meeting_title, name, time, room.getName());
        holder.itemLayoutBinding.meetingImage.setImageResource(pic);
        holder.itemLayoutBinding.meetingTitle.setText(fullTitle);


        holder.itemLayoutBinding.meetingsDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingsApi.deleteMeeting(meeting);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mMeetings.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

}

