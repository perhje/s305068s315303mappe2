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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        Intent i = new Intent(this, SmsService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),setTime(),pintent);
        return super.onStartCommand(intent,flags,startId);
    }
    public long setTime(){
        SharedPreferences prefcount = PreferenceManager.getDefaultSharedPreferences(this);
        time = prefcount.getString("tidspunkt", "8");
        long diffen=0;
        try {
            Date date1=new SimpleDateFormat("HH:mm").parse(time);
            long time1 = date1.getTime();
            System.out.println(time1);

            Calendar calendar= Calendar.getInstance();
            Date now = calendar.getTime();
            DateFormat tidspunkt= new SimpleDateFormat("HH:mm");
            String formatTidspunkt = tidspunkt.format(now);
            Date date2=new SimpleDateFormat("HH:mm").parse(formatTidspunkt);
            long time2= date2.getTime();
            System.out.println(time2);

            if(time1 > time2){
                 diffen= time1 - time2;


            }
            else if(time1 <= time2){
                time1+=(1000*60*60*24);
                 diffen= time1-time2;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diffen;
    }
}
