package com.example.phj_1.s305068s315303mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showGame(View v){
        Intent intent=new Intent(this,Venner.class);
        startActivity(intent);
    }

    public void showStatistics(View v){
        Intent intent=new Intent(this,Restaurant.class);
        startActivity(intent);
    }

    public void showPreferences(View v){
        Intent intent=new Intent(this,Bestilling.class);
        startActivity(intent);
    }

}
