package com.example.mareu.ui.new_meeting;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityNewMeetingLinearBinding;

import java.util.Calendar;

public class NewMeetingActivity extends AppCompatActivity {

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
        initDatePicker();
        initSpinner();
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
        mBinding.newMeetingDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //datepicker
                } else {
                    String meetingDate = mBinding.newMeetingDateField.getText().toString();
                    Log.d("DATE", meetingDate);
                }
            }

        });

    }

    private void initSpinner(){
        spinner = mBinding.newMeetingRoomSpinner;
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_rooms, android.R.layout.simple_spinner_item);
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
}