package com.grup15.gtuticaret;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Emirhan Karagözoğlu on 09.04.2018.
 */
public class Sepet extends MenuBar {
    private ArrayList<Pair<CheckBox,Product>> pairCart; //sepetin içindeki ürünleri ve işaretli olup olmadıklarını burdan kontrol etcez


    public Sepet(){
        pairCart = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sepet);
        final LinearLayout genel_Sepet = findViewById(R.id.general);
        findViewById(R.id.satınAl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer x = System.productList.size();
                Log.d("satinal",x.toString());
                for(Product p : User.cart){
                    String productName = p.getName();
                    String seller = p.getSeller();
                    String me = Giris.whoami;
                    String m1 = "Merhaba."+p.getId()+" numarali urununuzle ilgileniyorum."+ "Lutfen bana "+me+" bu mailden iletisime geciniz.\n" +
                            "Bu mesaj otomatik gonderilmistir";
                    Chat.Message m = new Chat.Message(m1,"",Giris.whoami,seller);
                    m.setUser();
                    m.setSend_time();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Mesajlar").child(seller).child(Giris.whoami).child(m.getSend_time()).setValue(m);

                }
            }
        });
        final LinkedList<Product> tmpCart = (LinkedList<Product>)User.cart.clone();  //sepetin kopyası üzerinde poll yapabilmek için
        if (User.cart != null) {
            Product tmpProduct;
            Double price = 0.0;

            for (int i = 0; i < User.cart.size(); ++i) {    //sepetin size kadar
                View view = getLayoutInflater().inflate(R.layout.sepet_urun_taslak, null);
                final ImageView iv = view.findViewById(R.id.productInCart);
                final CheckBox cb = view.findViewById(R.id.check);
                final TextView name = view.findViewById(R.id.nameOfProduct);
                final TextView description = view.findViewById(R.id.descriptionOfProduct);
                final TextView priceView = view.findViewById(R.id.priceOfProduct);
                final LinearLayout ll = view.findViewById(R.id.one);

                tmpProduct = tmpCart.poll();
                final Product tmp = tmpProduct;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                        intent1.putExtra("pro",tmp);
                        startActivity(intent1);
                        finish();
                    }
                });
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(),productContent.class);
                        intent1.putExtra("pro",tmp);
                        startActivity(intent1);
                        finish();
                    }
                });
                name.setText(tmpProduct.getName());
                description.setText(tmpProduct.getFeatures());
                priceView.setText(((Double)tmpProduct.getPrice()).toString() +" TL");
                price += tmpProduct.getPrice();
                pairCart.add(new Pair<>(cb, tmpProduct));
                int image = getApplicationContext().getResources().getIdentifier(tmpProduct.getImageCode(),"drawable",getPackageName());
                iv.setBackgroundResource(image);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(220, 320);
                iv.setLayoutParams(layoutParams);
                genel_Sepet.addView(view);
            }

            ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+price.toString()+" TL");
            ImageButton remove = findViewById(R.id.sil);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User.cart = new LinkedList<>();
                    Double p = 0.0;
                    int tmpSize = pairCart.size();
                    for (int i = 0; i < tmpSize; ++i) {
                        if(!pairCart.get(i).first.isChecked()) {
                            User.cart.add(pairCart.get(i).second);
                            p += pairCart.get(i).second.getPrice();
                        }
                        else {
                            genel_Sepet.removeViewAt(i);
                            pairCart.remove(i);
                            --i;
                            --tmpSize;
                        }
                    }
                    ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+p.toString()+" TL");
                }
            });
        }
        super.menuBar();
    }
}