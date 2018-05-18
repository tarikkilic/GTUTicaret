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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductScreen extends MenuBar {
    String typeC;
    //firebasedeki tum urunler arr arrayine cekiyorum
    private ArrayList<Product> arr;
    //sadece istenen kategorilerdeki urunleri temp arrayine atiyorum.
    private ArrayList<Product> temp;
    //firebase degiskenleri
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        super.menuBar();
        arr = new ArrayList<>();
        temp = new ArrayList<>();
        //kategori ekranina tiklanan kategoriyi tutuyorum.
        typeC = getIntent().getStringExtra("ezkey");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // Urunler kismindaki referanslari aliyorum sadece
        mFirebaseDatabase = mFirebaseInstance.getReference().child("Urunler") ;
        listView =  findViewById(R.id.productList);

        //tiklandiginde urun ekranina gider.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                intent1.putExtra("pro",temp.get(i));
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
                    FireListAdapter fireListAdapter = new FireListAdapter();
                    fireListAdapter.notifyDataSetChanged();
                    listView.setAdapter(fireListAdapter);

                }
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
            int i = 0,size = 0;
            while(i < arr.size()){
                if((arr.get(i).getType()).equals(typeC)){
                    size++;
                    temp.add(arr.get(i));
                }
                i++;
            }
            return size;
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
                    .load(temp.get(i).getImageCode())
                    .into(imageView);
            textView_name.setText(temp.get(i).getName());
            textView_description.setText(temp.get(i).getFeatures());
            textView_price.setText(Double.toString(temp.get(i).getPrice()) + " TL ");

            return view;
        }
    }
}


