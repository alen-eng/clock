package com.example.alarm;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    EditText timeHour;
    EditText timeMinute;
    Button setTime;
    Button setAlarm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout);

    timeHour=findViewById (R.id.etHour);
        timeMinute=findViewById(R.id.etMinute);
    setTime=findViewById(R.id.btnTime);
    setAlarm=findViewById(R.id.btnAlarm);

        setTime.setOnClickListener((v)-> {
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(MainActivity.this, (timePicker, i, i1) -> {
                timeHour.setText(String.format("%02d", i));
                timeMinute.setText(String.format("%02d", i1));
            },currentHour, currentMinute, false);
            timePickerDialog.show();
        } );


    setAlarm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
        if (!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty()) {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
            intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Set Alarm for morning walk");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "There is no app that support this action!", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(MainActivity.this, "Please choose a time", Toast.LENGTH_SHORT).show();
        }

    }
    });

    }

}
