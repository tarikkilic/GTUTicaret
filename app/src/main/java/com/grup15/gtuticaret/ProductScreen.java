package com.grup15.gtuticaret;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ProductScreen extends AppCompatActivity {


    /*
    deneme amaçlı ürün bilgileri
     */
    int[] IMAGES = {R.drawable.canon, R.drawable.iphone, R.drawable.jbl, R.drawable.monster,
    R.drawable.msi,R.drawable.samsung,R.drawable.selfi, R.drawable.sennheiser};

    String[] NAMES = {"CANON","IPHONE","JBL","MONSTER","MSI","SAMSUNG","SELFI","SENNHEISER"};

    String[] PRICE = {"100.90 TL","5000 TL","400 TL","6000 TL","8000 TL","4000 TL","60","180"};


    String[] DESCRIPTION = {"1 km kadar ceker","böyle iphone bulamassiniz","bass tiz mükemmel",
    "fiyat performans ürünü","zenginler için ideal","androidin reizi","hadi bi selfi çekinelim",
    "fena olmayan bi kulaklık"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);
        ListView listView = (ListView) findViewById(R.id.productList);
        CustomAdapter customAdapter = new CustomAdapter();
        //listeyi customlayouttaki şekilde oluşturdum hepsini
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });{

        }

    }

    //listeyi otomatik doldurmak için custom layout kullandim.

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
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
            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_description.setText(DESCRIPTION[i]);
            textView_price.setText(PRICE[i]);
            return view;
        }
    }
}


