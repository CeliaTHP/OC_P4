package com.example.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.MeetingService.MeetingsApi;
import com.example.mareu.service.RoomService.DummyRoomsGenerator;

import java.util.Calendar;

public class NewMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityNewMeetingLinearBinding mBinding;
    private Meeting meeting;
    private MeetingsApi meetingsApi;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingsApi = DI.getMeetingApi();
        initView();
        setToolbar();
        initSpinner();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) initDatePicker();
        initTimePicker();
        initAddButton();

    }

    //CHANGER VIEW
    private void initView() {
        mBinding = ActivityNewMeetingLinearBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());
    }

    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.new_meeting_pres);
    }

    private void initDatePicker() {
        mBinding.newMeetingDateField.setFocusable(false);
        mBinding.newMeetingDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }

    private void initTimePicker() {
        mBinding.newMeetingTimeField.setFocusable(false);
        mBinding.newMeetingTimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TImePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });
    }

    private void initSpinner() {
        Spinner spinner = mBinding.newMeetingRoomSpinner;
        //check WARNING
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = DummyRoomsGenerator.generateRoom().get(position);
                Log.d("ROOM", room.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initAddButton() {
        mBinding.newMeetingSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String title, String date, String time, String room
                String meetingTitle = mBinding.newMeetingTitleField.getText().toString();
                String meetingDate = mBinding.newMeetingDateField.getText().toString();
                String meetingTime = mBinding.newMeetingTimeField.getText().toString();
                //TODO add attendees
                //TODO display attendees in Chip
                //TODO verify mail format before adding it
                meeting = new Meeting(meetingTitle, meetingDate, meetingTime, room);

                if (!meetingTitle.isEmpty() && !meetingDate.isEmpty() && !meetingTime.isEmpty() && room != null) {
                    meetingsApi.addMeeting(meeting);

                } else
                    Log.d("ADD MEETING", "INFOS MISSING");
                //toast réu crée
                Log.d("MEETING : ", meeting.toString());
                //toast infos manquantes
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String fullDate = getString(R.string.registered_date, dayOfMonth, month + 1, year);
        mBinding.newMeetingDateField.setText(fullDate);
        Log.d("DATE", fullDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String fullTime = getString(R.string.registered_time, hourOfDay, minute);
        mBinding.newMeetingTimeField.setText(fullTime);
        Log.d("TIME", fullTime);
    }
}