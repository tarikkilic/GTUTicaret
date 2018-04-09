package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Celal Can on 9.04.2018.
 */

public class Hesabim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hesabim);
        TextView kisiselbilgi = (TextView) findViewById(R.id.bakiye);
        //kisiselbilgi.setText(get_name() +"\n" + get_mail());
        kisiselbilgi.setText("Ramazan Guvenc");
    }
}
