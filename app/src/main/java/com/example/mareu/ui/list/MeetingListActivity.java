package com.example.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingsApi;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

    private Context context;
    private ActivityMeetingListBinding mBinding;
    private MeetingsApi meetingsApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingsApi = DI.getMeetingApi();
        initView();
        initRecyclerView();
        initFabButton();
    }

    private void initView() {
        mBinding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
    }

    private void initRecyclerView() {
        mBinding.meetingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Meeting> meetings = meetingsApi.getMeetings();
        MeetingAdapter adapter = new MeetingAdapter(this, meetingsApi, meetings);
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


}