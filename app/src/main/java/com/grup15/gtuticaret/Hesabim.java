package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Celal Can on 9.04.2018.
 */

public class Hesabim extends MenuBar {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hesabim);
        TextView kisiselbilgi = (TextView) findViewById(R.id.kisiselbilgiler);
        //kisiselbilgi.setText(get_name() +"\n" + get_mail());
        kisiselbilgi.setText(Giris.whoami);


        super.menuBar();
    }
}
