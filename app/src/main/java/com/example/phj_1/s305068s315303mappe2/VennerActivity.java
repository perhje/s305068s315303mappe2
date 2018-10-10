package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class VennerActivity extends Activity{
    EditText navni;
    EditText adressei;
    EditText telefoni;
    EditText idi;
    TextView visvenner;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantactivity);
        navni=(EditText) findViewById(R.id.navn);
        adressei=(EditText) findViewById(R.id.adresse);
        telefoni=(EditText) findViewById(R.id.telefon);
        visvenner=(TextView) findViewById(R.id.visrestaurant);
        idi= (EditText) findViewById(R.id.restaurant_id);
    }


}
