package com.example.mareu.ui.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;

import com.example.mareu.R;
import com.example.mareu.callback.OnDeleteListener;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;
import com.example.mareu.databinding.EmptyItemLayoutBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.ui.new_meeting.NewMeetingActivity;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity implements OnDeleteListener {

    private ActivityMeetingListBinding mBinding;
    MeetingAdapter adapter;
    private MeetingsApi meetingsApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMeetingListBinding.inflate(LayoutInflater.from(this));
        meetingsApi = DI.getMeetingApi();

        initView();
        initRecyclerView();
        initFabButton();
        setToolbar();


        //TODO add movable fab

    }

    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        verifyEmptyList();

    }

    private void initView() {
        setContentView(mBinding.getRoot());
    }

    private void initRecyclerView() {
        mBinding.meetingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Meeting> meetings = meetingsApi.getMeetings();
        adapter = new MeetingAdapter(meetings, (this));
        mBinding.meetingRecyclerView.setAdapter(adapter);

        //TODO onClickDelete here
    }


    private void initFabButton() {

        mBinding.listFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingListActivity.this, NewMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.list_toolbar_title);
    }

    @Override
    public void onDelete(int position) {
        List<Meeting> meetings = meetingsApi.getMeetings();
        meetingsApi.deleteMeeting(meetings.get(position));
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, meetings.size());
        verifyEmptyList();
    }


    public void verifyEmptyList() {
        List<Meeting> meetings = meetingsApi.getMeetings();
        if (meetings.size() <= 0) {
            Log.d("OnDelete from Activity", "EmptyList");
            mBinding.meetingsArrow.setVisibility(View.VISIBLE);
            mBinding.noMeeting.setVisibility(View.VISIBLE);
        } else {
            mBinding.meetingsArrow.setVisibility(View.INVISIBLE);
            mBinding.noMeeting.setVisibility(View.INVISIBLE);

        }
    }
}