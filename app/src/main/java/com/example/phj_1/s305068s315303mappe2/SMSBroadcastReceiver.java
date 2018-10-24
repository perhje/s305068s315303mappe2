package com.example.phj_1.s305068s315303mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "I Broadcastreceiver", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, Periodisk.class);
        context.startService(i);
    }
}
