package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Celal Can and Little Bit Monica on 10.04.2018.
 */

public class Cuzdanim extends MenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuzdanim);
        TextView kisiselbilgi = (TextView) findViewById(R.id.bakiye);
        //kisiselbilgi.setText(get_wallet().getKey() + get_wallet().getValue());
        kisiselbilgi.setText("(Eklenecek)");
    }
}