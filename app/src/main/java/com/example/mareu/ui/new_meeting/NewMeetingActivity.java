package com.example.mareu.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class NewMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityNewMeetingLinearBinding mBinding;
    private Calendar calendar;
    private Spinner spinner;
    private DatePicker datePicker;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    private void initAddButton() {
        mBinding.newMeetingSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String title, String date, String time, String room, List<String> attendees
                String meetingTitle = mBinding.newMeetingTitleField.getText().toString();
                Log.d("AddButton Title", meetingTitle);
               /* DatePicker meetingDate = mBinding.newMeetingDateField.getText();
                Time meetingTime = mBinding.newMeetingTimeField.getDrawingTime();
                String meetingRoom = mBinding.newMeetingRoomField.getText();*/
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
        String fullTime = myHour +":"+ myMinute;
        mBinding.newMeetingTimeField.setText(fullTime);
        Log.d("TIME", fullTime);
    }
}