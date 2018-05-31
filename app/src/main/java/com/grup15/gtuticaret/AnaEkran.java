package com.grup15.gtuticaret;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grup15.gtuticaret.AVLTree.AVLTree;
import com.grup15.gtuticaret.System;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AnaEkran extends MenuBar {
    String typeC;
    //firebasedeki tum urunler arr arrayine cekiyorum
    public static ArrayList<Product> arr;
    private AVLTree<Product> productTree = new AVLTree<>();
    //firebase degiskenleri
    private DatabaseReference mFirebaseDatabase;
    private ListView listView;
    EditText arananUrun;
    Button ara;
    ViewPager mPager;

    private void toast ( int i){
        if (i == 0) {
            Toast.makeText(this, "Lütfen Aramak İstediginiz Kelimeyi Giriniz.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Arama Başarılı!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anaekran);
        super.menuBar();
        arr = new ArrayList<>();
        arananUrun = findViewById(R.id.arananUrun);
        ara = findViewById(R.id.ara);
        mPager = findViewById(R.id.viewPager1);
        findViewById(R.id.ara).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                String key = arananUrun.getText().toString();
                if(key.isEmpty()){
                    toast(0);
                }
                else {
                    toast(1);
                    Intent arama = new Intent(AnaEkran.this, Search.class);
                    arama.putExtra("arananDeger", key);
                    startActivity(arama);
                }
            }

        });
        String productType = System.recommendations.shortestPath(new User(Giris.whoami));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);



        findViewById(R.id.ara).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String key = arananUrun.getText().toString();
                if(key.isEmpty()){
                    toast(0);
                }
                else {
                    toast(1);
                    Intent arama = new Intent(AnaEkran.this, Search.class);
                    arama.putExtra("arananDeger", key);
                    startActivity(arama);
                }
            }

        });

        //kategori ekranina tiklanan kategoriyi tutuyorum.
        typeC = getIntent().getStringExtra("ezkey");
        // Urunler kismindaki referanslari aliyorum sadece
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler").child(productType);



        //firebasedeki urunleri bu metotla cekiyorum
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Random r = new Random();
                ArrayList<Product> pr = new ArrayList<>();
                int i = 0;
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                if (snapshotIterable != null) {
                    for (DataSnapshot dataSnapshot1 : snapshotIterable) {
                        Product product = dataSnapshot1.getValue(Product.class);
                        arr.add(product);
                        product.setName(product.getName().toLowerCase());
                        productTree.add(product);
                    }
                    if(arr.size()>5) {
                        while (i < 5) {
                            int j = r.nextInt(arr.size());
                            if (!pr.contains(arr.get(j))) {
                                pr.add(arr.get(j));
                                i++;
                            }
                        }
                    }
                    else {
                        int j =0;
                        while(j<arr.size())
                        if (!pr.contains(arr.get(j))) {
                            pr.add(arr.get(j));
                            j++;
                        }
                    }
                }

                    mPager = (ViewPager) findViewById(R.id.viewPager1);
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pr);
                    mPager.setAdapter(viewPagerAdapter);


                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //dolduralacak
            }
        });



    }


    public class ViewPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.buzdolabi, R.drawable.canon, R.drawable.buyutec};
        private ArrayList<Product> fArr;

        public ViewPagerAdapter(ArrayList<Product> array){
            fArr = (ArrayList<Product>) array.clone();
        }

        @Override
        public int getCount() {
            return fArr.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view  = getLayoutInflater().inflate(R.layout.custom_layout, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), productContent.class);
                    intent1.putExtra("pro", fArr.get(position));
                    startActivity(intent1);
                }
            });
            ImageView imageview = (ImageView) view.findViewById(R.id.imageView);
            Typeface tf = Typeface.createFromAsset(getAssets(), "opensanss.ttf");
            TextView name = (TextView) view.findViewById(R.id.textView_name);
            TextView desc = (TextView) view.findViewById(R.id.textView_description);
            TextView price = (TextView) view.findViewById(R.id.textView_price);
            if (fArr.get(position).getImageCode().equals("default")) {
                imageview.setImageResource(R.drawable.varsayilan);
            } else {
                Picasso.get()
                        .load(fArr.get(position).getImageCode())
                        .resize(110, 130)
                        .into(imageview);
            }

            name.setText(fArr.get(position).getName());
            desc.setText(fArr.get(position).getFeatures());
            price.setText(Double.toString(fArr.get(position).getPrice()) + " TL ");

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);
        }
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            AnaEkran.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mPager.getCurrentItem()==0){
                        mPager.setCurrentItem(1);
                    }
                    else if(mPager.getCurrentItem()==1){
                        mPager.setCurrentItem(2);
                    }
                    else if(mPager.getCurrentItem()==2){
                        mPager.setCurrentItem(3);
                    }
                    else if(mPager.getCurrentItem()==3){
                        mPager.setCurrentItem(4);
                    }
                    else{
                        mPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


    public class FireListAdapter extends BaseAdapter {
        //belirlenen kategoride kac tane urun var onu buluyor.
        private ArrayList<Product> fArr;

        public FireListAdapter(ArrayList<Product> array) {
            fArr = (ArrayList<Product>) array.clone();
        }

        @Override
        public int getCount() {
            return fArr.size();
        }

        @Override
        public Object getItem(int i) {
            return fArr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //arraydeki degerleri ekrana aktariyorum.
            view = getLayoutInflater().inflate(R.layout.custom_layout, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = view.findViewById(R.id.textView_name);
            TextView textView_description = view.findViewById(R.id.textView_description);
            TextView textView_price = view.findViewById(R.id.textView_price);
            if (fArr.get(i).getImageCode().equals("default")) {
                imageView.setImageResource(R.drawable.varsayilan);
            } else {
                Picasso.get()
                        .load(fArr.get(i).getImageCode())
                        .resize(110, 130)
                        .into(imageView);
            }

            textView_name.setText(fArr.get(i).getName());
            textView_description.setText(fArr.get(i).getFeatures());
            textView_price.setText(Double.toString(fArr.get(i).getPrice()) + " TL ");

            return view;
        }

    }


}




















