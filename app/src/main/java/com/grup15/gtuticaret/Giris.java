package com.grup15.gtuticaret;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class Giris extends AppCompatActivity  {

    Map<String,String> ePostaVeParola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giris);

        findViewById(R.id.yeniKullaniciOlustur).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent kayit= new Intent(Giris.this, KayitOl.class);
                startActivity(kayit);
            }

        });

    }


}
