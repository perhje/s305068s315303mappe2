package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RestaurantActivity extends Activity {

    EditText navni;
    EditText adressei;
    EditText telefoni;
    EditText typei;
    EditText idi;
    TextView visrestaurant;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantactivity);
        navni=(EditText) findViewById(R.id.navn);
        adressei=(EditText) findViewById(R.id.adresse);
        telefoni=(EditText) findViewById(R.id.telefon);
        typei=(EditText) findViewById(R.id.type);
        visrestaurant=(TextView) findViewById(R.id.visrestaurant);
        idi= (EditText) findViewById(R.id.restaurant_id);
        db= new DBHandler(this);
    }

    public void leggtil(View v) {
        Restaurant restaurant= new Restaurant(navni.getText().toString(), adressei.getText().toString(), telefoni.getText().toString(), typei.getText().toString());
        db.leggTilRestaurant(restaurant);
        Log.d("Legg inn: ", "legger til kontakter");

        visrestaurant.setText("Lagt til");
    }
    public void finnAlleRestauranter(View v) {
        Intent intent=new Intent(this,ListviewRestActivity.class);
        startActivity(intent);
    }

    public void slett(View v) {
        Long restaurant_id= (Long.parseLong(idi.getText().toString()));
        db.slettRestaurant(restaurant_id);
    }


    public void oppdater(View v) {
        Restaurant restaurant= new Restaurant();
        restaurant.setNavn(navni.getText().toString());
        restaurant.setAdresse(adressei.getText().toString());
        restaurant.setTelefon(telefoni.getText().toString());
        restaurant.setType(typei.getText().toString());
        restaurant.set_ID(Long.parseLong(idi.getText().toString()));
        db.endreRestaurant(restaurant);

    }
}
