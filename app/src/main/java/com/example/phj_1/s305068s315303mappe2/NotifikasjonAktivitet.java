package com.example.phj_1.s305068s315303mappe2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

public class NotifikasjonAktivitet extends Activity implements Notifikasjon.DialogClickListener {
    @Override
    public void onconfirm() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogFragment dialog = new Notifikasjon();
        dialog.show(getFragmentManager(),"Ferdig");

    }
}
