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
        restaurantnavn=(EditText) findViewById(R.id.restaurant);
        deltager=(EditText) findViewById(R.id.deltakere);
        bestillings_id= (EditText) findViewById(R.id.bestilling_id);
        visbestilling=(TextView) findViewById(R.id.visbestilling);
        db = new DBHandler(this);
    }



    public void leggtil(View v) {
        Bestilling bestilling= new Bestilling(tid.getText().toString(), restaurantnavn.getText().toString(), deltager.getText().toString());
        db.leggTilBestilling(bestilling);
        Log.d("Legg inn: ", "legger til bestilling");

        visbestilling.setText("tekst");
    }
    public void finnAlleBestilling(View v) {
        String tekst = "";
        List<Bestilling> bestillingene = db.finnAlleBestilling();
        for (Bestilling bestilling: bestillingene) {
            tekst = tekst + "Id: " + bestilling.get_ID() + ",tid: " +bestilling.getTid() + " ,restaurant: " +bestilling.getRestaurant()+ " ,deltager: " +bestilling.getDeltakere();
            Log.d("Navn: ", tekst);
        }
        visbestilling.setText(tekst);
    }

    public void slett(View v) {
        Long best_id = (Long.parseLong(bestillings_id.getText().toString()));
        db.slettBestilling(best_id);
    }


    public void oppdater(View v) {
        Bestilling bestilling= new Bestilling();
        bestilling.setTid(tid.getText().toString());
        bestilling.setRestaurant(restaurantnavn.getText().toString());
        bestilling.set_ID(Long.parseLong(bestillings_id.getText().toString()));
        db.endreBestilling(bestilling);

    }
}
