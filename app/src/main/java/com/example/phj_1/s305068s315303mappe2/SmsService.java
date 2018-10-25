package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SmsService extends Service{
    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences prefsms = PreferenceManager.getDefaultSharedPreferences(this);
        sms = prefsms.getString("smsmelding", "@string/standardsms");

        Toast.makeText(getApplicationContext(), "Sjekker for ny bestilling", Toast.LENGTH_SHORT).show();
       finnTlf();
        return super.onStartCommand(intent,flags,startId);
    }

    DBHandler db= new DBHandler(this);

    public void finnTlf() {
        String phoneNo ="";
        List<Bestilling>bestillingene = null;
        Toast.makeText(getApplicationContext(), "leter etter Tlf", Toast.LENGTH_SHORT).show();
        try {
            bestillingene = db.finnAlleBestilling();
            Toast.makeText(getApplicationContext(), "fant en bestilling", Toast.LENGTH_SHORT).show();
        String Deltager;
        String Tid;
        String restaurantNavn;


        for (Bestilling bestilling: bestillingene) {
            Tid =bestilling.getTid();
            Toast.makeText(getApplicationContext(), Tid, Toast.LENGTH_SHORT).show();

            try {
                Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(Tid);
                Calendar calendar= Calendar.getInstance();
                Date today = calendar.getTime();
                DateFormat dtf= new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String ny= dtf.format(today);
                Date date2=new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(ny);
                Toast.makeText(getApplicationContext(), "parset ferdig", Toast.LENGTH_SHORT).show();

                long differenceInMillis = date1.getTime() - date2.getTime();
                long antTimer=differenceInMillis/1000/60/60;

               int antallTimer= (int)antTimer;
               Toast.makeText(getApplicationContext(),"det er greit", Toast.LENGTH_SHORT).show();

                if(antallTimer<36 && antallTimer>12){
                    restaurantNavn= bestilling.getRestaurant();
                    Deltager = bestilling.getDeltakere();
                    String[] deltakerList = Deltager.split("-");
                    String test= deltakerList[0];
                    Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
                    List<Venner> vennene = db.finnAlleVenner();
                    for(String deltager: deltakerList){
                        //Toast.makeText(getApplicationContext(),deltager, Toast.LENGTH_SHORT).show();
                        for( Venner venner: vennene) {
                            String venn= venner.getNavn();
                            Toast.makeText(getApplicationContext(),deltager+"dette er en venn "+venn, Toast.LENGTH_SHORT).show();
                            if (venn.equals(deltager)){
                                phoneNo=venner.getTelefon();
                                Toast.makeText(getApplicationContext(),phoneNo, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"fant match", Toast.LENGTH_SHORT).show();
                                sendSMS(phoneNo);
                            }
                        }
                    }

                }  }
            catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "Konverteringsfeil", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
        }
        catch (NullPointerException e){
            Toast.makeText(getApplicationContext(), "fant ingen bestilling", Toast.LENGTH_SHORT).show();
        }
    }
    String sms;

    public void sendSMS(String phoneNo) {
            SmsManager smsMan= SmsManager.getDefault();
            smsMan.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(this, "Har sendt sms", Toast.LENGTH_SHORT).show();

    }
}
