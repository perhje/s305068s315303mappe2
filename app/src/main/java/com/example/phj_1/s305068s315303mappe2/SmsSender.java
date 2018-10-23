package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsSender extends Activity{
    String sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefsms = PreferenceManager.getDefaultSharedPreferences(this);
        sms = prefsms.getString("smsmelding", "@string/standardsms");
    }

    public void sendSMS(String phoneNo) {
        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED&& MY_PHONE_STATE_PERMISSION ==
                PackageManager.PERMISSION_GRANTED) {
            SmsManager smsMan= SmsManager.getDefault();
            smsMan.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(this, "Har sendt sms", Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE
            }, 0);
        }
    }
}
