package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Emirhan Karagözoğlu on 09.04.2018.
 */
public class Sepet extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ArrayList<Pair<CheckBox,Product>> pairCart; //sepetin içindeki ürünleri ve işaretli olup olmadıklarını burdan kontrol etcez


    public Sepet(){
        pairCart = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sepet);
        final LinearLayout genel_Sepet = findViewById(R.id.general);

        final LinkedList<Product> tmpCart = (LinkedList<Product>)User.cart.clone();  //sepetin kopyası üzerinde poll yapabilmek için
        if (User.cart != null) {
            Product tmpProduct;
            Double price = 0.0;

            for (int i = 0; i < User.cart.size(); ++i) {    //sepetin size kadar
                View view = getLayoutInflater().inflate(R.layout.sepet_urun_taslak, null);
                final ImageView iv = view.findViewById(R.id.productInCart);
                final CheckBox cb = view.findViewById(R.id.check);
                final TextView name = view.findViewById(R.id.nameOfProduct);
                final TextView description = view.findViewById(R.id.descriptionOfProduct);
                final TextView priceView = view.findViewById(R.id.priceOfProduct);
                final LinearLayout ll = view.findViewById(R.id.one);

                tmpProduct = tmpCart.poll();
                final Product tmp = tmpProduct;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                        intent1.putExtra("pro",tmp);
                        startActivity(intent1);
                        finish();
                    }
                });
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                        intent1.putExtra("pro",tmp);
                        startActivity(intent1);
                        finish();
                    }
                });
                name.setText(tmpProduct.getName());
                description.setText(tmpProduct.getFeatures());
                priceView.setText(((Double)tmpProduct.getPrice()).toString() +" TL");
                price += tmpProduct.getPrice();
                pairCart.add(new Pair<>(cb, tmpProduct));
                int image = getApplicationContext().getResources().getIdentifier(tmpProduct.getImageCode(),"drawable",getPackageName());
                iv.setBackgroundResource(image);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(220, 320);
                iv.setLayoutParams(layoutParams);
                genel_Sepet.addView(view);
            }

            ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+price.toString()+" TL");
            ImageButton remove = findViewById(R.id.sil);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User.cart = new LinkedList<>();
                    Double p = 0.0;
                    int tmpSize = pairCart.size();
                    for (int i = 0; i < tmpSize; ++i) {
                        if(!pairCart.get(i).first.isChecked()) {
                            User.cart.add(pairCart.get(i).second);
                            p += pairCart.get(i).second.getPrice();
                        }
                        else {
                            genel_Sepet.removeViewAt(i);
                            pairCart.remove(i);
                            --i;
                            --tmpSize;
                        }
                    }
                    ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+p.toString()+" TL");
                }
            });
        }
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
                        Intent menu = new Intent(Sepet.this, AnaEkran.class);
                        startActivity(menu);
                        finish();
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(Sepet.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        Intent kategori = new Intent(Sepet.this, Categories.class);
                        startActivity(kategori);
                        finish();
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(Sepet.this, Ayarlar.class);
                        startActivity(ayarlar);
                        finish();
                        break;
                    case R.id.navigation_addProduct:
                        Intent addProduct = new Intent(Sepet.this, addProduct.class);
                        startActivity(addProduct);
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
        return super.onOptionsItemSelected(item);
    }
}