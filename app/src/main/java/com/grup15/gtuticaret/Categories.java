package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by AKIN Ç on 8.04.2018.
 */

public class Categories extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    //control bar or navigation bar
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);


        ((ImageButton) findViewById(R.id.elektronik)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electronic=new Intent(Categories.this,ProductScreen.class);
                electronic.putExtra("ezkey","ELEKTRONIK");
                startActivity(electronic);
            }
        });
        ((ImageButton) findViewById(R.id.deney)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exp=new Intent(Categories.this,ProductScreen.class);
                exp.putExtra("ezkey","DENEY MALZEMELERI");
                startActivity(exp);
            }
        });
        ((ImageButton) findViewById(R.id.kitap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent book=new Intent(Categories.this,ProductScreen.class);
                book.putExtra("ezkey","KITAPLAR");
                startActivity(book);
            }
        });
        ((ImageButton) findViewById(R.id.esya)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent material=new Intent(Categories.this,ProductScreen.class);
                material.putExtra("ezkey","EV ESYALARI");
                startActivity(material);
            }
        });
        ((ImageButton) findViewById(R.id.bilet)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ticket=new Intent(Categories.this,ProductScreen.class);
                ticket.putExtra("ezkey","ETKINLIK-BILET");
                startActivity(ticket);
            }
        });


        //provides to the drawerlayout in activity_main.xml
        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        //takes four parametr
        //first is the this main activity
        //second is drawerlayout
        //third and four parametrs takes string. Strings are in string.xml
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);

        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //provides home button is enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

        NavigationView navigation = (NavigationView) findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        Intent menu = new Intent(Categories.this, AnaEkran.class);
                        startActivity(menu);
                        finish();
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(Categories.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        /*Kategori Sınıfında oldugumuz için intente gerek yok. */
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(Categories.this, Ayarlar.class);
                        startActivity(ayarlar);
                        finish();
                        break;
                }
                return false;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basketmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch(item.getItemId()) {
            case R.id.basket:
                Intent i = new Intent(this, Sepet.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}