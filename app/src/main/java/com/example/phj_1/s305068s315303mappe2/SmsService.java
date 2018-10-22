package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.content.ContentProvider;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class SmsService {

    public void SendSMS(View v) {
        MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED&&
                MY_PHONE_STATE_PERMISSION == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsMan= SmsManager.getDefault();
            smsMan.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(this, "Har sendt sms", Toast.LENGTH_SHORT).show();
        }
        else{ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE}, 0);
        }
    }

}
