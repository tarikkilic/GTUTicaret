package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/*
 * Created by AKIN Ç on 8.04.2018.
 */

public class Categories extends MenuBar {

    public static  String[] categories = {"ELEKTRONIK","DENEY MALZEMELERI","KITAPLAR","EV EŞYALARI",
            "ETKINLIK-BILET"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        ((ImageButton) findViewById(R.id.elektronik)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electronic=new Intent(Categories.this,ProductScreen.class);
                electronic.putExtra("ezkey",categories[0]);
                startActivity(electronic);
            }
        });
        ((ImageButton) findViewById(R.id.deney)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exp=new Intent(Categories.this,ProductScreen.class);
                exp.putExtra("ezkey",categories[1]);
                startActivity(exp);
            }
        });
        ((ImageButton) findViewById(R.id.kitap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent book=new Intent(Categories.this,ProductScreen.class);
                book.putExtra("ezkey",categories[2]);
                startActivity(book);
            }
        });
        ((ImageButton) findViewById(R.id.esya)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent material=new Intent(Categories.this,ProductScreen.class);
                material.putExtra("ezkey",categories[3]);
                startActivity(material);
            }
        });
        ((ImageButton) findViewById(R.id.bilet)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ticket=new Intent(Categories.this,ProductScreen.class);
                ticket.putExtra("ezkey",categories[4]);
                startActivity(ticket);
            }
        });

        super.menuBar();
    }
}