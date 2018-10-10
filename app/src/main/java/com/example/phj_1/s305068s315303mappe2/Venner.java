package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.os.Bundle;

public class Venner{

    String Navn;
    String Telefon;
    long _ID;

    public Venner(){

    }
    public Venner(String navn, String telefon){
        this.Navn=navn;
        this.Telefon = telefon;

    }

    public Venner(long _ID, String navn, String telefon) {
        this._ID=_ID;
        this.Navn=navn;
        this.Telefon = telefon;

    }

    public String getNavn() {
        return Navn;
    }

    public String getTelefon() {
        return Telefon;
    }

    public long get_ID() {
        return _ID;
    }



    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public void setTelefon(String telefon) {

        Telefon = telefon;
    }

    public void setNavn(String navn) {

        this.Navn = navn;
    }

}
