package com.example.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.ui.new_meeting.NewMeetingActivity;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

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

    }

    private void initView() {
        setContentView(mBinding.getRoot());
    }

    private void initRecyclerView() {
        mBinding.meetingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Meeting> meetings = meetingsApi.getMeetings();
        adapter = new MeetingAdapter(meetingsApi, meetings);
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



}