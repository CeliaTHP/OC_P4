package com.example.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.MeetingsApi;
import com.example.mareu.service.RoomsGenerator;
import com.example.mareu.ui.list.MeetingAdapter;
import com.example.mareu.ui.list.MeetingListActivity;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityNewMeetingLinearBinding mBinding;
    private Spinner spinner;
    private Meeting meeting;
    private MeetingsApi meetingsApi;
    Room room;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingsApi = DI.getMeetingApi();
        //CHANGER LAYOUT
        mBinding = ActivityNewMeetingLinearBinding.inflate(LayoutInflater.from(this));

        initView();
        setToolbar();
        initSpinner();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) initDatePicker();
        initTimePicker();
        initAddButton();

    }

    //CHANGER VIEW
    private void initView() {
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
        spinner = mBinding.newMeetingRoomSpinner;
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = RoomsGenerator.generateRoom().get(position);
                Log.d("ROOM", room.getmName());
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
                Log.d("NAME", meetingTitle);
                String meetingDate = mBinding.newMeetingDateField.getText().toString();
                Log.d("DATE", meetingDate);
                String meetingTime = mBinding.newMeetingTimeField.getText().toString();
                Log.d("TIME", meetingTime);
                Log.d("ROOM", room.getmName());
                meeting = new Meeting(meetingTitle, meetingDate, meetingTime, room);
                Log.d("NEW MEETING", meeting.getTitle());

                if (!meetingTitle.equals("") && !meetingDate.equals("") && !meetingTime.equals("") && room != null) {
                    meetingsApi.addMeeting(meeting);

                }
                else
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
        String fullDate = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        mBinding.newMeetingDateField.setText(fullDate);
        Log.d("DATE", fullDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String myHour = hourOfDay + "";
        String myMinute = minute + "";
        String fullTime = myHour + ":" + myMinute;
        mBinding.newMeetingTimeField.setText(fullTime);
        Log.d("TIME", fullTime);
    }
}