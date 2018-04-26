package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Celal Can on 26.04.2018.
 */

public class AnaEkran extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anaekran);

        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

        NavigationView navigation = (NavigationView) findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        /* Zaten anamenude oldugumuz icin intente gerek yok */
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(AnaEkran.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        Intent kategori = new Intent(AnaEkran.this, Categories.class);
                        startActivity(kategori);
                        finish();
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(AnaEkran.this, Ayarlar.class);
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



