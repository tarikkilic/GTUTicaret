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

    protected void menuBar(){
        mdrawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

        NavigationView navigation = findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        if (!getApplication().getClass().getName().equals(AnaEkran.class.getName())) {
                            Intent menu = new Intent(getApplicationContext(), AnaEkran.class);
                            startActivity(menu);
                            finish();
                        }
                        break;
                    case R.id.navigation_account:
                        if (!getApplication().getClass().getName().equals(Hesabim.class.getName())) {
                            Intent hesap = new Intent(getApplicationContext(), Hesabim.class);
                            startActivity(hesap);
                            finish();
                        }
                        break;
                    case R.id.navigation_categories:
                        if (!getApplication().getClass().getName().equals(Categories.class.getName())) {
                            Intent kategori = new Intent(getApplicationContext(), Categories.class);
                            startActivity(kategori);
                            finish();
                        }
                        break;
                    case R.id.navigation_setting:
                        if (!getApplication().getClass().getName().equals(Ayarlar.class.getName())) {
                            Intent ayarlar = new Intent(getApplicationContext(), Ayarlar.class);
                            startActivity(ayarlar);
                            finish();
                        }
                        break;
                    case R.id.navigation_addProduct:
                        if (!getApplication().getClass().getName().equals(addProduct.class.getName())) {
                            Intent addProduct = new Intent(getApplicationContext(), addProduct.class);
                            startActivity(addProduct);
                            finish();
                        }
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
