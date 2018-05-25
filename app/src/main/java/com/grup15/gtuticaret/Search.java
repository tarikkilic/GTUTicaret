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
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler");
    HashMap<String,Product> everything;
    private ArrayList<Product> arr;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        super.menuBar();
        // KENDİM TEMP Bİ PRODUCT OLUŞTURDUM ONU ARRAYLİST'E ATTIM. ARR = SEARCH("KEYWORD") ŞEKLİNDE ARAMAYI YAPIP SEARCHÜN TEST ET.
        // Sonraki yorum satırına kadar olan yerler test için silebilirsin
        Product a = new Product();
        a.setName("at");
        a.setFeatures("ldldpepe");
        a.setId(96910);
        a.setPrice(313);
        a.setType("ELEKTRONIK");
        a.setImageCode("default");
        Product b = new Product();
        b.setName("mouse");
        b.setFeatures("ldldpepe");
        b.setId(96910);
        b.setPrice(313);
        b.setType("ELEKTRONIK");
        b.setImageCode("https://firebasestorage.googleapis.com/v0/b/gtuticaret.appspot.com/o/images%2F0f17680d-bd00-4849-b66d-0414a7ae7596?alt=media&token=d9864b70-c3bf-4600-be0c-d76282f498fc");
        arr = new ArrayList<>();
        arr.add(a);
        arr.add(b);
        //kategori ekranina tiklanan kategoriyi tutuyorum.
        // Urunler kismindaki referanslari aliyorum sadece
        listView =  findViewById(R.id.productList);
        FireListAdapter fireListAdapter = new FireListAdapter();
        fireListAdapter.notifyDataSetChanged();
        listView.setAdapter(fireListAdapter);
        //tiklandiginde urun ekranina gider.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                intent1.putExtra("pro",arr.get(i));
                startActivity(intent1);
            }
        });
    }

    public ArrayList<Product> search(String key){
        ArrayList<Product> result = new ArrayList<>();
        everything = new HashMap<>();
        mDatabase.child("ELEKTRONIK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren() ;
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    Product product = dataSnapshot1.getValue(Product.class);
                    everything.put(product.getName(),product);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String [] arr = key.split("\\s+");
        if(everything.get(key) != null)
            result.add(everything.get(key));
        for(int i =0;i<arr.length;i++)
            if(everything.get(arr[i]) != null)
                result.add(everything.get(arr[i]));
        return result;
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
