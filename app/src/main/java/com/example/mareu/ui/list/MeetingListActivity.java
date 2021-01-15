package com.example.mareu.ui.list;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.R;
import com.example.mareu.callback.OnDeleteListener;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.service.RoomService.DummyRoomsGenerator;
import com.example.mareu.service.RoomService.RoomsApi;
import com.example.mareu.ui.new_meeting.DatePickerFragment;
import com.example.mareu.ui.new_meeting.NewMeetingActivity;
import com.example.mareu.utils.DisplayFormatter;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity implements OnDeleteListener, DatePickerDialog.OnDateSetListener {

    private ActivityMeetingListBinding mBinding;
    List<Meeting> filteredMeetings;
    private MeetingAdapter adapter;
    private MeetingsApi meetingsApi;
    private RoomsApi roomsApi;
    private Room roomChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMeetingListBinding.inflate(LayoutInflater.from(this));
        meetingsApi = DI.getMeetingApi();
        roomsApi = DI.getRoomApi();

        initView();
        initRecyclerView();
        initFabButton();
        setToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_reset:
                updateRecyclerView(meetingsApi.getMeetings());
                verifyEmptyList();
                break;
            case R.id.filter_date:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "filter_by_date");
                break;
            case R.id.filter_room:
                initRoomDialog();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        adapter.updateData(meetingsApi.getMeetings());
        verifyEmptyList();
    }

    private void initView() {
        setContentView(mBinding.getRoot());
    }

    private void initRecyclerView() {
        mBinding.meetingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Meeting> meetings = meetingsApi.getMeetings();
        adapter = new MeetingAdapter(meetings, this);
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
        if (toolbar != null)
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
        if (adapter.getItemCount() <= 0) {
            Log.d("OnDelete from Activity", "EmptyList");
            mBinding.meetingsArrow.setVisibility(View.VISIBLE);
            mBinding.noMeeting.setVisibility(View.VISIBLE);
        } else {
            mBinding.meetingsArrow.setVisibility(View.INVISIBLE);
            mBinding.noMeeting.setVisibility(View.INVISIBLE);

        }
    }

    private void initRoomDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Example");
        String[] rooms = getResources().getStringArray(R.array.spinner_rooms);
        dialog.setItems(rooms, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                roomChosen = roomsApi.getRooms().get(which);
                filteredMeetings = meetingsApi.getMeetingsByRoom(roomChosen);
                adapter.updateData(filteredMeetings);
                verifyEmptyList();
            }

        });
        dialog.show();
    }

    public void updateRecyclerView(List<Meeting> meetings) {
        adapter.updateData(meetings);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String fullDate = getString(R.string.registered_date, DisplayFormatter.checkDisplay(dayOfMonth), DisplayFormatter.checkDisplay(month + 1), year);
        updateRecyclerView(meetingsApi.getMeetingsByDate(fullDate));
        verifyEmptyList();

    }


}