package com.example.carrie.carrie_test1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class alarm extends AppCompatActivity {
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView alarmset;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        this.context=this;
        alarm_manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarm_timepicker=(TimePicker)findViewById(R.id.timePicker);
        alarmset=(TextView)findViewById(R.id.alarmset);
        final Calendar calendar=Calendar.getInstance();
        final Button alarmon=(Button)findViewById(R.id.alarmon);
        final Intent my_intent=new Intent(this.context,Alarm_Receiver.class);
        alarmon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());
                int hour=alarm_timepicker.getHour();
                int minute=alarm_timepicker.getMinute();

                String hour_string=String.valueOf(hour);
                String minute_string=String.valueOf(minute);

                if (hour>12){
                    hour_string=String.valueOf(hour-12);
                }
                if (minute<10){
                    minute_string="0"+String.valueOf(minute);
                }

                set_alarm_text("Alarm set to:"+hour_string+":"+minute_string);
                my_intent.putExtra("extra","alarm on");
                pending_intent= PendingIntent.getBroadcast(alarm.this,0,
                        my_intent,PendingIntent.FLAG_CANCEL_CURRENT);
                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);

            }
        });
        Button alarmoff=(Button)findViewById(R.id.alarmoff);
        alarmoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm off!");
                alarm_manager.cancel(pending_intent);
                my_intent.putExtra("extra","alarm off");

                sendBroadcast(my_intent);
            }
        });

    }
    private void set_alarm_text(String output) {
        alarmset.setText(output);
    }
    public void goback(View v){
        finish();
    }

}

//
//        import android.app.AlarmManager;
//        import android.app.PendingIntent;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
//        import android.view.View;
//        import android.widget.TimePicker;
//        import android.widget.Toast;
//        import android.widget.ToggleButton;
//
//        import java.util.Calendar;
//
//public class alarm extends AppCompatActivity
//{
//    TimePicker alarmTimePicker;
//    PendingIntent pendingIntent;
//    AlarmManager alarmManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alarm);
//        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//    }
//    public void OnToggleClicked(View view)
//    {
//        long time;
//        if (((ToggleButton) view).isChecked())
//        {
//            Toast.makeText(alarm.this, "ALARM ON", Toast.LENGTH_SHORT).show();
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
//            Intent intent = new Intent(this, Alarm_Receiver.class);
//            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
//            if(System.currentTimeMillis()>time)
//            {
//                if (calendar.AM_PM == 0)
//                    time = time + (1000*60*60*12);
//                else
//                    time = time + (1000*60*60*24);
//            }
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
//        }
//        else
//        {
//            alarmManager.cancel(pendingIntent);
//            Toast.makeText(alarm.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void goback(View v){
//        finish();
//    }

//}
