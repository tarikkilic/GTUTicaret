package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
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
        arr = new ArrayList<>();
        String typeC = "ELEKTRONIK";
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler").child(typeC);
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
        key = "klavye";
        search(key);

    }

    private void search(final String key) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren() ;
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                everything = new HashMap<>();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    Product product = dataSnapshot1.getValue(Product.class);
                    everything.put(product.getName(),product);
                }
                String [] arr1 = key.split("\\s+");
                if(everything.get(key) != null)
                    arr.add(everything.get(key));
                for(int i =0;i<arr1.length;i++)
                    if(everything.get(arr1[i]) != null)
                        arr.add(everything.get(arr1[i]));
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


    public class FireListAdapter extends BaseAdapter {
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
