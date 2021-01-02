package com.example.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityNewMeetingLinearBinding mBinding;
    private Meeting meeting;
    private MeetingsApi meetingsApi;
    private Room room;
    private ChipGroup chipGroup;


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

        mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees,getAttendees().length()));

        mBinding.newMeetingAddAttendee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup = mBinding.chipGroup;
                initChip();
            }
        });

        mBinding.newMeetingSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String title, String date, String time, String room
                String meetingTitle = mBinding.newMeetingTitleField.getText().toString();
                String meetingDate = mBinding.newMeetingDateField.getText().toString();
                String meetingTime = mBinding.newMeetingTimeField.getText().toString();
                String attendees = getAttendees();


                if (!meetingTitle.isEmpty() && !meetingDate.isEmpty() && !meetingTime.isEmpty() && !getAttendees().isEmpty()) {
                    meeting = new Meeting(meetingTitle, meetingDate, meetingTime, room, attendees);
                    Log.d("ADD MEETING", "INFOS MISSING");
                    meetingsApi.addMeeting(meeting);
                    finish();
                } else {
                    alertEmptyFields();
                    String text = getString(R.string.invalid_meeting);
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });
    }

    public void initChip() {

        if (!validEmail(mBinding.newMeetingNewAttendee.getText())) {
            //toast invalid email
            String text = getString(R.string.invalid_email);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Chip chip = new Chip(this);
            chip.setText(mBinding.newMeetingNewAttendee.getText());
            mBinding.newMeetingNewAttendee.setText("");
            chip.setCloseIconVisible(true);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    chipGroup.removeView(chip);
                    mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees,chipGroup.getChildCount()));

                }
            });
            chip.setTextColor(getResources().getColor(R.color.black));
            chipGroup.addView(chip);
            mBinding.newMeetingAttendees.setText(getString(R.string.new_meeting_attendees,chipGroup.getChildCount()));
        }
    }

    public Boolean validEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getAttendees() {
        ArrayList<String> emails = new ArrayList<>();
        chipGroup = mBinding.chipGroup;

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            String email = ((Chip) chipGroup.getChildAt(i)).getText().toString();
            emails.add(email);
        }
        return emails.toString().replace("]", "").replace("[", "");
    }

    public void alertEmptyFields() {

        if(mBinding.newMeetingTitleField.getText().toString().isEmpty())
            mBinding.newMeetingTitleField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingTitleField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if(mBinding.newMeetingDateField.getText().toString().isEmpty())
            mBinding.newMeetingDateField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingDateField.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if(mBinding.newMeetingTimeField.getText().toString().isEmpty())
            mBinding.newMeetingTimeField.setBackgroundColor(getResources().getColor(R.color.light_red));
        else
            mBinding.newMeetingTimeField.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        if(getAttendees().isEmpty())
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
        String fullDate = getString(R.string.registered_date, checkDisplay(dayOfMonth), checkDisplay(month + 1), year);
        mBinding.newMeetingDateField.setText(fullDate);
        Log.d("DATE", fullDate);
    }

    public String checkDisplay(int info) {
        String infoString = String.valueOf(info);
        if (info < 10)
            infoString = "0" + info;
        return infoString;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String fullTime = getString(R.string.registered_time, checkDisplay(hourOfDay), checkDisplay(minute));
        mBinding.newMeetingTimeField.setText(fullTime);
        Log.d("TIME", fullTime);
    }
}