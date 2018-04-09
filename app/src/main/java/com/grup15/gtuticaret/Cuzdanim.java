package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Celal Can and Little Bit Monica on 10.04.2018.
 */

public class Cuzdanim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuzdanim);
        TextView kisiselbilgi = (TextView) findViewById(R.id.bakiye);
        //kisiselbilgi.setText(get_wallet().getKey() + get_wallet().getValue());
        kisiselbilgi.setText("182.94");
    }
}