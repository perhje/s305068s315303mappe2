package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SmsService extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefsms = PreferenceManager.getDefaultSharedPreferences(this);
        sms = prefsms.getString("smsmelding", "@string/standardsms");

        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();
        finnTlf();
    }

    DBHandler db;

    public void finnTlf() {
        String phoneNo ="";

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
                        for( Venner venner: vennene) {
                            if (deltager==venner.getNavn()) {
                                phoneNo=venner.getTelefon();
                                SmsSender.sendSMS(phoneNo);
                                sendSMS(phoneNo);
                            }
                        }
                    }

                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
    String sms;

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
