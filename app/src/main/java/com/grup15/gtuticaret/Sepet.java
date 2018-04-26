package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;


public class Sepet extends AppCompatActivity {

    private ArrayList<Pair<CheckBox,Product>> pairCart; //sepetin içindeki ürünleri ve işaretli olup olmadıklarını burdan kontrol etcez
    static LinkedList<Product> cart = new LinkedList<>();

    public Sepet(){
        pairCart = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sepet);
        LinearLayout genel_Sepet = findViewById(R.id.general);

        if (cart != null) {
            LinkedList<Product> tmpCart = (LinkedList<Product>)cart.clone();  //sepetin kopyası üzerinde poll yapabilmek için
            Product tmpProduct;
            Double price = new Double(0);

            for (int i = 0; i < cart.size(); ++i) {    //sepetin size kadar
                View view = getLayoutInflater().inflate(R.layout.sepet_urun_taslak, null);
                final ImageButton ib = view.findViewById(R.id.productInCart);
                final CheckBox cb = view.findViewById(R.id.check);

                tmpProduct = tmpCart.poll();
                price += tmpProduct.getPrice();
                pairCart.add(new Pair<>(cb, tmpProduct));
                int image = getApplicationContext().getResources().getIdentifier(tmpProduct.getImageCode(),"drawable",getPackageName());
                ib.setBackgroundResource(image);//düzelcek
                genel_Sepet.addView(view);
            }

            ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+price.toString()+" TL");
            ImageButton remove = findViewById(R.id.sil);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cart = new LinkedList<>();
                    Double p = new Double(0);
                    LinearLayout ll = findViewById(R.id.general);
                    for (int i = 0; i < pairCart.size(); ++i) {
                        if(!pairCart.get(i).first.isChecked()) {
                            cart.add(pairCart.get(i).second);
                            p += pairCart.get(i).second.getPrice();
                        }
                        else {
                            ll.removeViewAt(i);
                            pairCart.remove(i);
                        }
                    }
                    ((TextView) findViewById(R.id.tutar)).setText("Toplam Tutar: "+p.toString()+" TL");
                }
            });
        }
    }
}