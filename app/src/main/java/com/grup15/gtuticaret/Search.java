package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Search extends MenuBar {
    DatabaseReference mDatabase;
    HashMap<String,Product> everything;
    private ArrayList<Product> arr;
    private ListView listView;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        super.menuBar();
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sorted_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        arr = new ArrayList<>();
        everything = new HashMap<>();
        String [] typeC = {"ELEKTRONIK","DENEY MALZEMELERI","KITAPLAR","EV EŞYALARI","ETKINLIK-BILET"};
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler");
        //kategori ekranina tiklanan kategoriyi tutuyorum.
        // Urunler kismindaki referanslari aliyorum sadece
        listView =  findViewById(R.id.productList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                intent1.putExtra("pro",arr.get(i));
                startActivity(intent1);
            }
        });
        search(this.getIntent().getStringExtra("arananDeger"),typeC);

        //spinner metotlari
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //neyi sectiyse ona göre siralar
                //artanFiyat vs. Product classinin icinde bu claslar
                if(adapterView.getSelectedItem().equals("Fiyata göre artan")){
                    Collections.sort(arr,new artanFiyat());
                    listView.setAdapter(null);
                    Search.FireListAdapter fireListAdapter = new Search.FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }
                else if(adapterView.getSelectedItem().equals("Fiyata göre azalan")){
                    Collections.sort(arr,new azalanFiyat());
                    listView.setAdapter(null);
                    Search.FireListAdapter fireListAdapter = new Search.FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }

                else if(adapterView.getSelectedItem().equals("Isme gore A-Z")){
                    Collections.sort(arr,new artanIsim());
                    listView.setAdapter(null);
                    Search.FireListAdapter fireListAdapter = new Search.FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }
                else if(adapterView.getSelectedItem().equals("Isme gore Z-A")){
                    Collections.sort(arr,new azalanIsim());
                    listView.setAdapter(null);
                    Search.FireListAdapter fireListAdapter = new Search.FireListAdapter(arr);
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void search(final String key,final String [] list) {
        final String [] arr1 = key.split("\\s+");
        for(int i =0;i<list.length;i++){
            DatabaseReference fd = mDatabase.child(list[i]);
            fd.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren() ;
                    Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot dataSnapshot1 = iterator.next();
                        Product product = dataSnapshot1.getValue(Product.class);
                        String name = product.getName().toLowerCase();
                        if(name.equals(key.toLowerCase()))
                            arr.add(product);
                        else{
                            boolean flag = true;
                            for (String anArr1 : arr1) {
                                if (name.equals(anArr1.toLowerCase())){
                                    arr.add(product);
                                    flag = false;
                                }
                            }
                            if(flag){
                                String [] spaces = name.split("\\s+");
                                for(String s : spaces)
                                    if(s.toLowerCase().equals(key.toLowerCase()))
                                        arr.add(product);
                                }

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //dolduralacak
                }
            });
        }
        FireListAdapter fireListAdapter = new FireListAdapter(arr);
        fireListAdapter.notifyDataSetChanged();
        listView.setAdapter(fireListAdapter);
        arr.clear();
        everything.clear();

    }


    public class FireListAdapter extends BaseAdapter {

        private ArrayList<Product> fArr;

        public FireListAdapter(ArrayList<Product> array) {
            fArr = array;
        }

        //belirlenen kategoride kac tane urun var onu buluyor.
        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int i) {
            return arr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //arraydeki degerleri ekrana aktariyorum.
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name =  view.findViewById(R.id.textView_name);
            TextView textView_description =  view.findViewById(R.id.textView_description);
            TextView textView_price =  view.findViewById(R.id.textView_price);
            if(arr.get(i).getImageCode().equals("default")){
                imageView.setImageResource(R.drawable.varsayilan);
            }
            else{
                Picasso.get()
                        .load(arr.get(i).getImageCode())
                        .resize(110,130)
                        .into(imageView);
            }

            textView_name.setText(arr.get(i).getName());
            textView_description.setText(arr.get(i).getFeatures());
            textView_price.setText(Double.toString(arr.get(i).getPrice()) + " TL ");

            return view;
        }
    }


}
