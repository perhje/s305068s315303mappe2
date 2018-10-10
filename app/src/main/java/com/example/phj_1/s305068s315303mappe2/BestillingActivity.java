package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;



public class BestillingActivity extends Activity {
    EditText tid;
    EditText restaurantnavn;
    EditText deltager;
    EditText bestillings_id;
    TextView visbestilling;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestillingactivity);
        tid=(EditText) findViewById(R.id.tid);
        restaurantnavn=(EditText) findViewById(R.id.restaurantnavn);
        deltager=(TextView) findViewById(R.id.deltager);
        bestillings_id= (EditText) findViewById(R.id.bestillings_id);
        db = new DBHandler(this);
    }



    public void leggtil(View v) {
        Venner venner= new Venner(navni.getText().toString(), telefoni.getText().toString());
        db.leggTilVenner(venner);
        Log.d("Legg inn: ", "legger til venner");

        visvenner.setText("teekst");
    }
    public void finnAlleVenner(View v) {
        String tekst = "";
        List<Venner> vennene = db.finnAlleVenner();
        for (Venner venner: vennene) {
            tekst = tekst + "Id: " + venner.get_ID() + ",Navn: " +venner.getNavn() + " ,Telefon: " +venner.getTelefon();
            Log.d("Navn: ", tekst);
        }
        visvenner.setText(tekst);
    }

    public void slett(View v) {
        Long venner_id= (Long.parseLong(idi.getText().toString()));
        db.slettVenner(venner_id);
    }


    public void oppdater(View v) {
        Venner venner= new Venner();
        venner.setNavn(navni.getText().toString());
        venner.setTelefon(telefoni.getText().toString());
        venner.set_ID(Long.parseLong(idi.getText().toString()));
        db.endreVenner(venner);

    }
}
