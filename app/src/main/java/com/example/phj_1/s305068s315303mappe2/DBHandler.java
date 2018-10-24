package com.example.phj_1.s305068s315303mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    static String TABLE_VENNER = "Venner";
    static String VENNER_ID = "_ID";
    static String NAVN_VENNER = "Navn";
    static String TELEFON_VENNER = "Telefon";

    static String TABLE_RESTAURANT = "Restaurant";
    static String RESTAURANT_ID = "_ID";
    static String NAVN_RESTAURANT = "Navn";
    static String ADRESSE_RESTAURANT = "Adresse";
    static String TELEFON_RESTAURANT = "Telefon";
    static String TYPE_RESTAURANT = "Type";

    static String TABLE_BESTILLING = "Bestilling";
    static String BESTILLING_ID = "_ID";
    static String BESTILLING_TIDSPUNKT = "Tidspunkt";
    static String BESTILLING_VENNER = "Venner";
    static String BESTILLING_RESTAURANT = "Restaurant";

    static String DATABASE_NAVN = "Restaurantdatabase";
    static int DATABASE_VERSJON = 25;

    public DBHandler(Context context){
        super(context, DATABASE_NAVN, null, DATABASE_VERSJON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_VENNER_TABELL = "CREATE TABLE IF NOT EXISTS " + TABLE_VENNER + "(" + VENNER_ID + " INTEGER PRIMARY KEY,"
                + NAVN_VENNER + " TEXT," + TELEFON_VENNER + " TEXT" + ")";
        Log.d("SQL", LAG_VENNER_TABELL);
        db.execSQL(LAG_VENNER_TABELL);
        String LAG_RESTAURANT_TABELL = "CREATE TABLE IF NOT EXISTS " + TABLE_RESTAURANT + "(" + RESTAURANT_ID + " INTEGER PRIMARY KEY,"
                + NAVN_RESTAURANT + " TEXT," + ADRESSE_RESTAURANT + " TEXT," + TELEFON_RESTAURANT + " TEXT,"
                + TYPE_RESTAURANT + " TEXT" + ")";
        Log.d("SQL", LAG_RESTAURANT_TABELL);
        db.execSQL( LAG_RESTAURANT_TABELL);
        String LAG_BESTILLING_TABELL = "CREATE TABLE IF NOT EXISTS " + TABLE_BESTILLING + "(" + BESTILLING_ID + " INTEGER PRIMARY KEY,"
                + BESTILLING_TIDSPUNKT + " TEXT," + BESTILLING_RESTAURANT + " TEXT," + BESTILLING_VENNER + " TEXT" + ")";
        Log.d("SQL", LAG_BESTILLING_TABELL);
        db.execSQL(LAG_BESTILLING_TABELL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENNER);
        Log.d("UPDATE", "i venner");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        Log.d("UPDATE", "i restaurant");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BESTILLING);
        Log.d("UPDATE", "i bestilling");
        onCreate(db);
    }

    public void leggTilVenner(Venner venner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAVN_VENNER, venner.getNavn());
        values.put(TELEFON_VENNER, venner.getTelefon());
        db.insert(TABLE_VENNER, null,values);
        db.close();
    }

    public void leggTilRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAVN_RESTAURANT, restaurant.getNavn());
        values.put(ADRESSE_RESTAURANT, restaurant.getAdresse());
        values.put(TELEFON_RESTAURANT, restaurant.getTelefon());
        values.put(TYPE_RESTAURANT, restaurant.getType());
        db.insert(TABLE_RESTAURANT, null,values);
        db.close();
    }

    public void leggTilBestilling(Bestilling bestilling){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BESTILLING_TIDSPUNKT, bestilling.getTid());
        values.put(BESTILLING_RESTAURANT,bestilling.getRestaurant());
        values.put(BESTILLING_VENNER, bestilling.getDeltakere());
        db.insert(TABLE_BESTILLING, null,values);
        db.close();
    }

    public List<Venner> finnAlleVenner(){
        List<Venner> vennerListe = new ArrayList<Venner>();
        String selectQuery = "SELECT * FROM " + TABLE_VENNER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Venner venner = new Venner();
                venner.set_ID(cursor.getLong(0));
                venner.setNavn(cursor.getString(1));
                venner.setTelefon(cursor.getString(2));
                vennerListe.add(venner);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return  vennerListe;
    }

    public List<Restaurant> finnAlleRestauranter(){
        List<Restaurant> restaurantListe = new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getLong(0));
                restaurant.setNavn(cursor.getString(1));
                restaurant.setAdresse(cursor.getString(2));
                restaurant.setTelefon(cursor.getString(3));
                restaurant.setType(cursor.getString(4));
                restaurantListe.add(restaurant);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return  restaurantListe;
    }

    public List<Bestilling> finnAlleBestilling(){
        List<Bestilling> bestillingListe = new ArrayList<Bestilling>();
        String selectQuery = "SELECT * FROM " + TABLE_BESTILLING;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Bestilling bestilling = new Bestilling();
                bestilling.set_ID(cursor.getLong(0));
                bestilling.setTid(cursor.getString(1));
                bestilling.setRestaurant(cursor.getString(2));
                bestilling.setDeltakere(cursor.getString(3));
                bestillingListe.add(bestilling);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return bestillingListe;
    }

    public void slettVenner(Long inn_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VENNER, VENNER_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

    public void slettRestaurant(Long inn_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANT, RESTAURANT_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

    public void slettBestilling(Long inn_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BESTILLING, BESTILLING_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

    public int endreVenner(Venner venner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAVN_VENNER,venner.getNavn());
        values.put(TELEFON_VENNER, venner.getTelefon());
        int endret = db.update(TABLE_VENNER, values, VENNER_ID + "= ?",
                new String[]{String.valueOf(venner.get_ID())});
        db.close();
        return endret;
    }

    public int endreRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAVN_RESTAURANT,restaurant.getNavn());
        values.put(ADRESSE_RESTAURANT,restaurant.getNavn());
        values.put(TELEFON_RESTAURANT, restaurant.getTelefon());
        values.put(TYPE_RESTAURANT,restaurant.getNavn());
        int endret = db.update(TABLE_RESTAURANT, values, RESTAURANT_ID + "= ?",
                new String[]{String.valueOf(restaurant.get_ID())});
        db.close();
        return endret;
    }

    public int endreBestilling(Bestilling bestilling){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BESTILLING_TIDSPUNKT,bestilling.getTid());
        values.put(BESTILLING_VENNER,bestilling.getDeltakere());
        values.put(BESTILLING_RESTAURANT,bestilling.getRestaurant());
        int endret = db.update(TABLE_BESTILLING, values, BESTILLING_ID + "= ?",
                new String[]{String.valueOf(bestilling.get_ID())});
        db.close();
        return endret;
    }
}
