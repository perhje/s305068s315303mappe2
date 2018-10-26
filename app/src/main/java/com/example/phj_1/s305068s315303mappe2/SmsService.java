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
import android.graphics.drawable.Icon;
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
        try {
            bestillingene = db.finnAlleBestilling();
        String Deltager;
        String Tid;


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
                long antTimer=differenceInMillis/1000/60/60;
                int antallTimer= (int)antTimer;
                if(antallTimer<36 && antallTimer>12){
                    Toast.makeText(getApplicationContext(), "fant en bestilling", Toast.LENGTH_SHORT).show();
                    Deltager = bestilling.getDeltakere();
                    String[] deltakerList = Deltager.split("-");
                    List<Venner> vennene = db.finnAlleVenner();
                    for(String deltager: deltakerList){
                        for( Venner venner: vennene) {
                            String venn= venner.getNavn();
                            if (venn.equals(deltager)){
                                phoneNo=venner.getTelefon();
                                Toast.makeText(getApplicationContext(),"fant match deltager: "+venn+" "+phoneNo, Toast.LENGTH_SHORT).show();
                                sendSMS(phoneNo);
                            }
                        }
                    }
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(this)
                            .setContentTitle("MinNotifikasjon")
                            .setContentText("Tekst")
                            .setSmallIcon(R.mipmap.ic_launcher).build();
                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(0,notification);


                } }
            catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "Konverteringsfeil, feil input format på dato", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "sendt sms:"+sms, Toast.LENGTH_SHORT).show();

    }
}
