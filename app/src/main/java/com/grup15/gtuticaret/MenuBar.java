package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class MenuBar extends AppCompatActivity {
    protected DrawerLayout mdrawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    private StackTraceElement[] stackTraceElements;

    protected void menuBar(){
        mdrawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));
        stackTraceElements = Thread.currentThread().getStackTrace();

        NavigationView navigation = findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        if (!stackTraceElements[3].getClassName().equals(AnaEkran.class.getName())) {
                            Intent menu = new Intent(getApplicationContext(), AnaEkran.class);
                            startActivity(menu);
                        } else
                            mdrawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_account:
                        if (!stackTraceElements[3].getClassName().equals(Hesabim.class.getName())) {
                            Intent hesap = new Intent(getApplicationContext(), Hesabim.class);
                            startActivity(hesap);
                        } else
                            mdrawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_categories:
                        if (!stackTraceElements[3].getClassName().equals(Categories.class.getName())) {
                            Intent kategori = new Intent(getApplicationContext(), Categories.class);
                            startActivity(kategori);
                        } else
                            mdrawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_setting:
                        if (!stackTraceElements[3].getClassName().equals(Ayarlar.class.getName())) {
                            Intent ayarlar = new Intent(getApplicationContext(), Ayarlar.class);
                            startActivity(ayarlar);
                        } else
                            mdrawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_addProduct:
                        if (!stackTraceElements[3].getClassName().equals(addProduct.class.getName())) {
                            Intent addProduct = new Intent(getApplicationContext(), addProduct.class);
                            startActivity(addProduct);

                        } else
                            mdrawerLayout.closeDrawers();
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

        if (!stackTraceElements[3].getClassName().equals(Sepet.class.getName())) {
            switch (item.getItemId()) {
                case R.id.basket:
                    Intent i = new Intent(this, Sepet.class);
                    startActivity(i);
                    finish();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}