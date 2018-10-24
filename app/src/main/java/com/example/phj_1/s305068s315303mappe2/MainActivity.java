package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED&& MY_PHONE_STATE_PERMISSION ==
                PackageManager.PERMISSION_GRANTED) {}else{
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE
            }, 0);
        }
       //String onoff;
        SharedPreferences prefavpa = PreferenceManager.getDefaultSharedPreferences(this);
        boolean onoff = prefavpa.getBoolean("smsonoff",true);
        if(onoff) {
            Intent intent = new Intent();
            intent.setAction("com.example.serviceeksempel.mittbroadcast");
            sendBroadcast(intent);
        }
        Toast.makeText(getApplicationContext(), "NÅ starte jeg", Toast.LENGTH_SHORT).show();
    }
    public void onRestart(){
        super.onRestart();
        recreate();
    }

    public void visVenner(View v){
        Intent intent=new Intent(this,VennerActivity.class);
        startActivity(intent);
    }

    public void visRestaurant(View v){
        Intent intent=new Intent(this,RestaurantActivity.class);
        startActivity(intent);
    }

    public void visBestilling(View v){
        Intent intent=new Intent(this,BestillingActivity.class);
        startActivity(intent);
    }

    public void visPreferanser(View v){
        Intent intent=new Intent(this,Preferanser.class);
        startActivity(intent);
    }




}
