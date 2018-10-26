package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ListviewVennActivity extends Activity{
    private List<String> listValues;

    DBHandler db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.list);
        db = new DBHandler(this);
        listValues = new ArrayList<String>();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listValues);
        listView.setAdapter(myAdapter);


        String tekst = "";

        List<Venner> vennene = db.finnAlleVenner();
        for (Venner venner : vennene) {
            tekst= venner.getNavn();
            listValues.add(tekst);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;

                String tekst3="";
                String  itemValue    = (String) listView.getItemAtPosition(position);
                List<Venner> vennene = db.finnAlleVenner();
                for (Venner venner : vennene) {
                    if(itemValue.equals(venner.getNavn())) {
                        tekst3 = venner.getNavn() + " ,Telefon: " + venner.getTelefon() + "\n";
                    }
                }

                Toast.makeText(getApplicationContext(),tekst3 , Toast.LENGTH_LONG).show();
            }
        });

    }
    public void avslutt(View v){
        finish();
    }
}
