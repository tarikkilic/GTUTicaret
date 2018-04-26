package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,Giris.class);
        startActivity(intent);
        finish();

     /*   //ürünler ekranı
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,ProductScreen.class);
        finish();
        startActivity(intent);
*/
        /*kategori ekranı
        setContentView(R.layout.categories);
        Intent intent1 = new Intent(this,Categories.class);
*/
      /*
      Cüzdanım
      setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,Cuzdanim.class);
        startActivity(intent);*/

        //kategori içerikleri
        /*setContentView(R.layout.category_contents);
        Intent intent2 = new Intent(this,Categories.class);
        startActivity(intent2);*/
    }





}
