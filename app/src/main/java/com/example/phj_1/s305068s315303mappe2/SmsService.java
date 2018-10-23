package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SmsService extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    DBHandler db;
    public int finnTlf() {
        int phoneNo=0;

        List<Bestilling> bestillingene = db.finnAlleBestilling();
        String Deltager;
        String Tid;
        String restaurantNavn;


        for (Bestilling bestilling: bestillingene) {
            Tid =bestilling.getTid();
            try {
                Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(Tid);
                Calendar calendar= Calendar.getInstance();
                Date today = calendar.getTime();
                DateFormat dtf= new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String ny= dtf.format(today);
                Date date2=new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(ny);
                long differenceInMillis = date1.getTime() - date2.getTime();
                long antallTimer=differenceInMillis/1000/60/60;

                if(antallTimer<36 && antallTimer>12){
                    restaurantNavn= bestilling.getRestaurant();
                    Deltager = bestilling.getDeltakere();
                    String[] deltakerList = Deltager.split("-");

                    for(String deltager: deltakerList){
                        List<Venner> vennene = db.finnAlleVenner();
                         


                    }

                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }




        return phoneNo;
    }

}
