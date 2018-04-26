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
    String fileProduct = "products.txt";
    String filekeyWord = "key.txt";
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProduct();


        //ürünler ekranı
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,ProductScreen.class);
        intent.putExtra("product",products);
        finish();
        startActivity(intent);


        /*setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,Giris.class);
        startActivity(intent);
        finish();*/

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




    public void initProduct()  {
        String line = "";
        InputStream is;
        BufferedReader br;
        try {
            is = getAssets().open(fileProduct);
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null){
                String[] word = line.split(";");
                Product prd = new Product();
                prd.setType(word[0]);
                prd.setImageCode(word[1]);
                prd.setId(Integer.parseInt(word[2]));
                prd.setName(word[3]);
                prd.setFeatures(word[4]);
                prd.setPrice(Double.parseDouble(word[5]));
                initkeyWord(prd);
                products.add(prd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initkeyWord(Product p) {
        String line = "";
        InputStream is;
        BufferedReader br;
        try {
            is = getAssets().open(filekeyWord);
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null){

                LinkedList<String> key = new LinkedList<>();
                String[] word = line.split(";");
                key.add(word[0]);
                key.add(word[1]);
                key.add(word[2]);
                p.setKeyWords(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
