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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

public class AnaEkran extends MenuBar {
    String typeC;
    //firebasedeki tum urunler arr arrayine cekiyorum
    public static ArrayList<Product> arr;
    private AVLTree<Product> productTree = new AVLTree<>();
    //firebase degiskenleri
    private DatabaseReference mFirebaseDatabase;
    private ListView listView;
    EditText arananUrun;

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
        //spinner init
        arananUrun = findViewById(R.id.arananUrun);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sorted_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String productType = System.recommendations.shortestPath(new User(Giris.whoami));

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
        listView = findViewById(R.id.productList);
        //tiklandiginde urun ekranina gider.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(), productContent.class);
                intent1.putExtra("pro", arr.get(i));
                startActivity(intent1);
            }
        });


        //firebasedeki urunleri bu metotla cekiyorum
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Random r = new Random();
                ArrayList<Product> pr=new ArrayList<>();
                int i=0;
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                if(snapshotIterable != null){
                    for (DataSnapshot dataSnapshot1 : snapshotIterable) {
                        Product product = dataSnapshot1.getValue(Product.class);
                        arr.add(product);
                        product.setName(product.getName().toLowerCase());
                        productTree.add(product);
                        if (i < 5 && arr.size() > 4) {
                            int j = r.nextInt(arr.size());
                            if (!pr.contains(arr.get(j))) {
                                pr.add(arr.get(j));
                                i++;
                            }
                        }
                    }
                    FireListAdapter fireListAdapter = new FireListAdapter(pr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //dolduralacak
            }
        });


        //spinner metotlari
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //neyi sectiyse ona göre siralar
                //artanFiyat vs. Product classinin icinde bu claslar
                if (adapterView.getSelectedItem().equals("Fiyata göre artan")) {
                    Collections.sort(arr, new artanFiyat());
                    listView.setAdapter(null);
                    FireListAdapter fireListAdapter = new FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                } else if (adapterView.getSelectedItem().equals("Fiyata göre azalan")) {
                    Collections.sort(arr, new azalanFiyat());
                    listView.setAdapter(null);
                    FireListAdapter fireListAdapter = new FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                } else if (adapterView.getSelectedItem().equals("Isme gore A-Z")) {
                    Collections.sort(arr, new artanIsim());
                    listView.setAdapter(null);
                    FireListAdapter fireListAdapter = new FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                } else if (adapterView.getSelectedItem().equals("Isme gore Z-A")) {
                    Collections.sort(arr, new azalanIsim());
                    listView.setAdapter(null);
                    FireListAdapter fireListAdapter = new FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void searchProduct(View view) {

        EditText et = findViewById(R.id.KategorideArananUrun);
        String key = et.getText().toString().toLowerCase();
        if (!key.isEmpty()) {
            arr.clear();
            productTree.find(new Product(key));
            // if(product != null){
            // product.setName(product.getName().substring(0, 1).toUpperCase() + product.getName().substring(1));
            // arr.add(product);
            // }


            listView.setAdapter(null);
            FireListAdapter fireListAdapter = new FireListAdapter(arr);
            fireListAdapter.notifyDataSetChanged();
            listView.setAdapter(fireListAdapter);

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






























