package com.example.phj_1.s305068s315303mappe2;

public class Restaurant{

    String Navn;
    String Adresse;
    String Telefon;
    String Type;
    long _ID;

    public Restaurant(long restaurant_id){
        this._ID= restaurant_id;

    }
    public Restaurant(String navn, String telefon, String type, String adresse){
        this.Navn=navn;
        this.Adresse=adresse;
        this.Telefon = telefon;
        this.Type=type;
    }

    public Restaurant(long _ID, String navn, String telefon, String type, String adresse) {
        this._ID=_ID;
        this.Navn=navn;
        this.Adresse=adresse;
        this.Telefon = telefon;
        this.Type=type;
    }

    public String getNavn() {
        return Navn;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getType() {
        return Type;
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
    public void setAdresse(String adresse) {

        this.Adresse = adresse;
    }
    public void setType(String type) {

        this.Type = type;
    }
}


