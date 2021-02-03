package com.example.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityNewMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.meetingService.MeetingsApi;
import com.example.mareu.utils.DisplayFormatter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityNewMeetingBinding mBinding;
    private Meeting meeting;
    private MeetingsApi meetingsApi;
    private Date date;
    private Date startTime;
    private Date endTime;
    private Boolean viewClick;


    private Room room;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingsApi = DI.getMeetingApi();
        initView();
        setToolbar();
        initSpinner();
        initDatePicker();
        initTimePicker();
        initAddButton();

    }

    private void initView() {
        mBinding = ActivityNewMeetingBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());
    }

    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.new_meeting_pres);
    }

    private void initDatePicker() {
        mBinding.newMeetingDateField.setFocusable(false);
        mBinding.newMeetingDateField.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });

    }

    private void initTimePicker() {
        mBinding.newMeetingStartTimeField.setFocusable(false);
        mBinding.newMeetingStartTimeField.setOnClickListener(v -> {
            viewClick = true;
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });
        mBinding.newMeetingEndTimeField.setFocusable(false);
        mBinding.newMeetingEndTimeField.setOnClickListener(v -> {
            viewClick = false;
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });
    }

    private void initSpinner() {
        Spinner spinner = mBinding.newMeetingRoomSpinner;
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = meetingsApi.getRooms().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initAddButton() {
        mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees, getAttendees().size()));

        mBinding.newMeetingAddAttendee.setOnClickListener(v -> {
            chipGroup = mBinding.chipGroup;
            initChip();
        });

        mBinding.newMeetingSend.setOnClickListener(v -> {
            String meetingTitle = mBinding.newMeetingTitleField.getText().toString();
            String meetingDate = mBinding.newMeetingDateField.getText().toString();
            List<String> attendees = getAttendees();

            if (!meetingTitle.isEmpty() && !meetingDate.isEmpty() && startTime != null && endTime != null && !getAttendees().isEmpty()) {
                meeting = new Meeting(meetingTitle, date, startTime, endTime, room, attendees);
                meetingsApi.addMeeting(meeting);
                String text = getString(R.string.valid_meeting, meeting.getTitle());
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
                finish();
            } else {
                alertEmptyFields();
                String text = getString(R.string.invalid_meeting);
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public void initChip() {
        if (!validEmail(mBinding.newMeetingNewAttendee.getText())) {
            String text = getString(R.string.invalid_email);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Chip chip = new Chip(this);
            chip.setText(mBinding.newMeetingNewAttendee.getText());
            mBinding.newMeetingNewAttendee.setText("");
            chip.setCloseIconVisible(true);
            chip.setOnCloseIconClickListener(view -> {
                chipGroup.removeView(chip);
                mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees, chipGroup.getChildCount()));

            });
            chip.setTextColor(getResources().getColor(R.color.black));
            chipGroup.addView(chip);
            mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees, chipGroup.getChildCount()));
        }
    }

    private Boolean validEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public List<String> getAttendees() {
        ArrayList<String> emails = new ArrayList<>();
        chipGroup = mBinding.chipGroup;

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            String email = ((Chip) chipGroup.getChildAt(i)).getText().toString();
            emails.add(email);
        }
        return emails;
    }

    public void alertEmptyFields() {
        if (mBinding.newMeetingTitleField.getText().toString().isEmpty())
            mBinding.newMeetingTitleField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingTitleField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (mBinding.newMeetingDateField.getText().toString().isEmpty())
            mBinding.newMeetingDateField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingDateField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (mBinding.newMeetingStartTimeField.getText().toString().isEmpty())
            mBinding.newMeetingStartTimeField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingStartTimeField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (mBinding.newMeetingEndTimeField.getText().toString().isEmpty())
            mBinding.newMeetingEndTimeField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingEndTimeField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (getAttendees().isEmpty())
            mBinding.newMeetingNewAttendee.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingNewAttendee.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = c.getTime();
        String dateString = DisplayFormatter.formatDateToString(date);
        mBinding.newMeetingDateField.setText(dateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        if (viewClick) {
            startTime = c.getTime();
            String startTimeString = DisplayFormatter.formatTimeToString(startTime);
            mBinding.newMeetingStartTimeField.setText(startTimeString);
        } else {
            endTime = c.getTime();
            String endTimeString = DisplayFormatter.formatTimeToString(endTime);
            mBinding.newMeetingEndTimeField.setText(endTimeString);
        }
    }
}