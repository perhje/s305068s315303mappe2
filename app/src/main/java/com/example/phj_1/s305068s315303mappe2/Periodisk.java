package com.example.phj_1.s305068s315303mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class Periodisk extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    String time;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        SharedPreferences prefcount = PreferenceManager.getDefaultSharedPreferences(this);
        time = prefcount.getString("tidspunkt", "8");
        java.util.Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, MinService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60*1000,pintent);
        return super.onStartCommand(intent,flags,startId);
    }
}
