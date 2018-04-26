package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductScreen extends AppCompatActivity {

    ArrayList<Product> p = new ArrayList<>();
    ArrayList<Product> temp = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        p = (ArrayList<Product>) getIntent().getExtras().getSerializable("product");
        Log.i("DENEMEEEE",Integer.toString(p.size()));
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
            }
        });{

        }
    }

    //listeyi otomatik doldurmak için custom layout kullandim.

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            int i = 0,size = 0;
            while(i < p.size()){
                if(p.get(i).getType().equals("KITAPLAR")){
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
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView) view.findViewById(R.id.textView_description);
            TextView textView_price = (TextView) view.findViewById(R.id.textView_price);
            imageView.setImageResource(R.drawable.note1);
            textView_name.setText(temp.get(i).getName());
            textView_description.setText(temp.get(i).getFeatures());
            textView_price.setText(Double.toString(temp.get(i).getPrice()));
            return view;
        }
    }
}


