package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void visVenner(View v){
        Intent intent=new Intent(this,VennerActivity.class);
        startActivity(intent);
    }

    public void visRestaurant(View v){
        Intent intent=new Intent(this,RestaurantActivity.class);
        startActivity(intent);
    }

    public void visBestilling(View v){
        Intent intent=new Intent(this,BestillingActivity.class);
        startActivity(intent);
    }

    public void visPreferanser(View v){
        Intent intent=new Intent(this,Preferanser.class);
        startActivity(intent);
    }

}
