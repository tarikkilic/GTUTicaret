package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ürünler ekranı
        /*
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,ProductScreen.class);
        startActivity(intent);*/
        //kategori ekranı

        setContentView(R.layout.categories);
        Intent intent1 = new Intent(this,Categories.class);
        startActivity(intent1);
        //kategori içerikleri
        /*
        setContentView(R.layout.category_contents);
        Intent intent2 = new Intent(this,Categories.class);
        startActivity(intent2);
        */


    }
}
