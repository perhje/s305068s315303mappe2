package com.example.phj_1.s305068s315303mappe2;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

public class SmsSender extends Activity{
    String sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smsactivity);
        SharedPreferences prefsms = PreferenceManager.getDefaultSharedPreferences(this);
        sms = prefsms.getString("smsmelding", "@string/standardsms");
        finnTlf();

    }

    DBHandler db;

    public void finnTlf() {
        String phoneNo ="";

        Toast.makeText(getApplicationContext(), "leter etter Tlf", Toast.LENGTH_SHORT).show();
        try {
            List<Bestilling>bestillingene = db.finnAlleBestilling();

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

                                    //SmsSender.sendSMS(phoneNo);
                                    Toast.makeText(getApplicationContext(),phoneNo, Toast.LENGTH_SHORT).show();
                                    sendSMS(phoneNo);

                                }
                            }
                        }

                    }
                }
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

    public void sendSMS(String phoneNo) {
        SmsManager smsMan= SmsManager.getDefault();
        smsMan.sendTextMessage(phoneNo, null, sms, null, null);
        Toast.makeText(this, "Har sendt sms", Toast.LENGTH_SHORT).show();

    }
}
