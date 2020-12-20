package com.example.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityListBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingsApi;
import com.example.mareu.service.DummyMeetingsGenerator;
import com.example.mareu.service.MeetingsApi;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Context context;
    private ActivityListBinding mBinding;
    private MeetingAdapter adapter;
    private List<Meeting> meetings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        initRecyclerView();
    }


    private void initRecyclerView() {
        mBinding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.meetingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        meetings = DummyMeetingsGenerator.generateMeetings();
        adapter = new MeetingAdapter(this, meetings);
        mBinding.meetingRecyclerView.setAdapter(adapter);
        initFabButton();
    }

    private void initFabButton() {
        context = getApplicationContext();
        mBinding.listFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //création Meeting
                Log.d("RV", "Création de meeting");
                Intent intent = new Intent(context, NewMeetingActivity.class);
                startActivity(intent);
            }
        });
    }


}