package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class Sepet extends AppCompatActivity {

    //ürünün içinde hali hazırda olucak
    int[] IMAGES = {R.drawable.urun_genel, R.drawable.urun_genel1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sepet);
        LinearLayout genel_Sepet = findViewById(R.id.general);
        for(int i = 0 ; i<IMAGES.length ; ++i) {
            View view = getLayoutInflater().inflate(R.layout.sepet_urun_taslak,null);
            ImageButton ib = view.findViewById(R.id.productInCart);

            ib.setBackgroundResource(IMAGES[i]);
            genel_Sepet.addView(view);
        }
    }
}
