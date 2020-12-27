package com.example.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingsApi;
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

    }

    public void onResume() {
        super.onResume();
        //update whatever your list
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