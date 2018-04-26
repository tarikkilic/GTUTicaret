package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductScreen extends AppCompatActivity {
    String fileProduct = "products.txt";
    String filekeyWord = "key.txt";
    ArrayList<Product> p = new ArrayList<>();
    ArrayList<Product> temp = new ArrayList<>();
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String typeC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        initProduct();
        typeC = getIntent().getStringExtra("ezkey");
        final ListView listView = (ListView) findViewById(R.id.productList);
        CustomAdapter customAdapter = new CustomAdapter();
        //listeyi customlayouttaki şekilde oluşturdum hepsini
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                intent1.putExtra("pro",temp.get(i));
                startActivity(intent1);
                finish();
            }
        });


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
                        Intent menu = new Intent(ProductScreen.this, AnaEkran.class);
                        startActivity(menu);
                        finish();
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(ProductScreen.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        Intent kategori = new Intent(ProductScreen.this, Categories.class);
                        startActivity(kategori);
                        finish();
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(ProductScreen.this, Ayarlar.class);
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

    //listeyi otomatik doldurmak için custom layout kullandim.

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            int i = 0,size = 0;
            while(i < p.size()){
                if(p.get(i).getType().equals(typeC)){
                    size++;
                    temp.add(p.get(i));
                }

                i++;
            }
            return size;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            int imageID = getApplicationContext().getResources().getIdentifier(temp.get(i).getImageCode(),"drawable",getPackageName());
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView) view.findViewById(R.id.textView_description);
            TextView textView_price = (TextView) view.findViewById(R.id.textView_price);
            imageView.setImageResource(imageID);
            textView_name.setText(temp.get(i).getName());
            textView_description.setText(temp.get(i).getFeatures());
            textView_price.setText(Double.toString(temp.get(i).getPrice()) + " TL ");
            return view;
        }
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
                p.add(prd);
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


