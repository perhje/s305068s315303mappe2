package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.os.Bundle;

import java.util.Date;

public class Bestilling{
    Date Tid;
    String Restaurant;
    String Deltakere;
    long _ID;

    public Bestilling(){

    }
    public Bestilling(Date tid, String restaurant, String deltakere){
        this.Tid = tid;
        this.Restaurant = restaurant;
        this.Deltakere = deltakere;
    }

    public Bestilling(Date tid, String restaurant, String deltakere, long _ID){
        this.Tid = tid;
        this.Restaurant = restaurant;
        this.Deltakere = deltakere;
        this._ID = _ID;
    }

    public Date getTid() {
        return Tid;
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public String getDeltakere() {
        return Deltakere;
    }

    public long get_ID() {
        return _ID;
    }



    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setTid(Date tid) {

        Tid = tid;
    }

    public void setDeltakere(String deltakere) {

        Deltakere = deltakere;
    }

    public void setRestaurant(String restaurant) {

        Restaurant = restaurant;
    }
}
