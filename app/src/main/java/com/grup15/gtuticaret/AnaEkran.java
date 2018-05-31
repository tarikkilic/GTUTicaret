package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Celal Can on 26.04.2018.
 */

public class AnaEkran extends MenuBar {
    EditText arananUrun;
    Button ara;

    private void toast(int i){
        if(i==0){
            Toast.makeText(this, "Lütfen Aramak İstediginiz Kelimeyi Giriniz.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Arama Başarılı!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anaekran);
        super.menuBar();
        arananUrun = findViewById(R.id.arananUrun);
        ara = findViewById(R.id.ara);
        findViewById(R.id.ara).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                String key = arananUrun.getText().toString();
                if(key.isEmpty()){
                    toast(0);
                }
                else {
                    toast(1);
                    Intent arama = new Intent(AnaEkran.this, Search.class);
                    arama.putExtra("arananDeger", key);
                    startActivity(arama);
                }
            }

        });


        /*Bu kategori tipinde 3 tane random ürün önerilip System classındaki recommendedProductsa eklenecek
            ve Ana ekranda 3 ürün gösterilecek
         */
        String productType = System.recommendations.shortestPath(new User(Giris.whoami));
    }
}



