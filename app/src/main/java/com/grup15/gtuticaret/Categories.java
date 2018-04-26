package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by AKIN Ç on 8.04.2018.
 */

public class Categories extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    //control bar or navigation bar
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);


        ((ImageButton) findViewById(R.id.elektronik)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electronic=new Intent(Categories.this,ProductScreen.class);
                electronic.putExtra("elektronik","elektronik");
                startActivity(electronic);
            }
        });
        ((ImageButton) findViewById(R.id.deney)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exp=new Intent(Categories.this,ProductScreen.class);
                exp.putExtra("deney","deney");
                startActivity(exp);
            }
        });
        ((ImageButton) findViewById(R.id.kitap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent book=new Intent(Categories.this,ProductScreen.class);
                book.putExtra("kitap","kitap");
                startActivity(book);
            }
        });
        ((ImageButton) findViewById(R.id.esya)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent material=new Intent(Categories.this,ProductScreen.class);
                material.putExtra("esya","esya");
                startActivity(material);
            }
        });
        //provides to the drawerlayout in activity_main.xml
        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        //takes four parametr
        //first is the this main activity
        //second is drawerlayout
        //third and four parametrs takes string. Strings are in string.xml
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);

        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //provides home button is enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basketmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
