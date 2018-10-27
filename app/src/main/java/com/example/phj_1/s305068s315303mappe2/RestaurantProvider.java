package com.example.phj_1.s305068s315303mappe2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class RestaurantProvider extends ContentProvider{
    public static final String _ID = DBHandler.RESTAURANT_ID;
    public static final String TABELL = DBHandler.TABLE_RESTAURANT;
    public static final String PROVIDER = "com.example.contentproviderrestaurant";
    public static final int RESTAURANT = 1;
    public static final int MRESTAURANT = 2;
    DBHandler DBhelper;
    SQLiteDatabase db;
    public static final Uri COMMENT_URI = Uri.parse("content://" + PROVIDER + "/rest");
    public static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "restaurant", MRESTAURANT);
        uriMatcher.addURI(PROVIDER, "restaurant/#", RESTAURANT);
    }
    @Override
    public boolean onCreate(){
        DBhelper = new DBHandler(getContext());
        db = DBhelper.getWritableDatabase();
        return true;
    }

    @Override
    public String getType(Uri uri){
        switch (uriMatcher.match(uri)){
            case MRESTAURANT:
                return "vnd.android.cursor.dir/vnd.example.restaurant";
            case RESTAURANT:
                return "vnd.android.cursor.item/vnd.example.restaurant";
            default:
                throw new
                        IllegalArgumentException("Ugyldig URI" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        db.insert(TABELL, null,  values);
        Cursor c = db.query(TABELL, null,null,null,null,null,null);
        c.moveToLast();
        long minid = c.getLong(0);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, minid);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Cursor cur = null;
        if(uriMatcher.match(uri) == RESTAURANT){
            cur = db.query(TABELL, projection, _ID + " =" + uri.getPathSegments().get(1), selectionArgs, null, null, sortOrder);
            return cur;
        } else{
            cur = db.query(TABELL, null, null, null, null, null, null);
            return cur;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        if (uriMatcher.match(uri) == RESTAURANT){
            db.delete(TABELL, _ID + " = " + uri.getPathSegments().get(1), selectionArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return 1;
        }
        if(uriMatcher.match(uri) == MRESTAURANT){
            db.delete(TABELL, null,null);
            getContext().getContentResolver().notifyChange(uri,null);
            return 2;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (uriMatcher.match(uri) == RESTAURANT){
            db.update(TABELL, values, _ID + " = " + uri.getPathSegments().get(1), null);
            getContext().getContentResolver().notifyChange(uri, null);
            return 1;
        }
        if (uriMatcher.match(uri) == MRESTAURANT){
            db.update(TABELL, null, null ,null);
            getContext().getContentResolver().notifyChange(uri, null);
            return 2;
        }
        return 0;
    }

}
