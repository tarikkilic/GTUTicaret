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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductScreen extends MenuBar {
    String typeC;
    //firebasedeki tum urunler arr arrayine cekiyorum
    private ArrayList<Product> arr;
    //firebase degiskenleri
    private DatabaseReference mFirebaseDatabase;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        super.menuBar();
        arr = new ArrayList<>();
        //kategori ekranina tiklanan kategoriyi tutuyorum.
        typeC = getIntent().getStringExtra("ezkey");
        // Urunler kismindaki referanslari aliyorum sadece
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler").child(typeC) ;
        listView =  findViewById(R.id.productList);
        //tiklandiginde urun ekranina gider.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                intent1.putExtra("pro",arr.get(i));
                startActivity(intent1);
            }
        });

        //firebasedeki urunleri bu metotla cekiyorum
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren() ;
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    Product product = dataSnapshot1.getValue(Product.class);
                    arr.add(product);
                }
                FireListAdapter fireListAdapter = new FireListAdapter();
                fireListAdapter.notifyDataSetChanged();
                listView.setAdapter(fireListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //dolduralacak
            }
        });
    }

    public class FireListAdapter extends BaseAdapter{
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
            Picasso.get()
                    .load(arr.get(i).getImageCode())
                    .resize(110,130)
                    .into(imageView);
            textView_name.setText(arr.get(i).getName());
            textView_description.setText(arr.get(i).getFeatures());
            textView_price.setText(Double.toString(arr.get(i).getPrice()) + " TL ");

            return view;
        }
    }
}


