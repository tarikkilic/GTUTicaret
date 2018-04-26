package com.grup15.gtuticaret;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class productContent extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Stack<String> comments;
    TextView lastComment;
    Product newProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_content);

        newProduct = (Product) getIntent().getExtras().getSerializable("pro");
        int image = getApplicationContext().getResources().getIdentifier(newProduct.getImageCode(),"drawable",getPackageName());

        String name = newProduct.getName();
        Double price = newProduct.getPrice();
        String feature = newProduct.getFeatures();


        //Her ürüne geçici yorumlar yerleştirildi.
        comments = new Stack<>();
        comments.add("GTU Alışveriş sağolsun sizin gibi dürüst satıcıların olduğunu görebildik. Dünya böyle satıcılar uğruna dönüyor");
        comments.add("Harika sorunsuz bir ürün");
        comments.add("Teşekkürler");
        comments.add("Şiddetle tavsiye ediyorum");
        comments.add("Fiyat performans ürünü :)");
        comments.add("Zengin değilseniz tavsiye etmiyorum");
        comments.add("Güvenilir satıcı, kesinlikle tavsiye ederim.");


        ImageView productImage = findViewById(R.id.productImage);
        productImage.setImageResource(image);

        TextView productName = findViewById(R.id.productName);
        productName.setText(name);

        TextView productPrice = findViewById(R.id.productCost);
        productPrice.setText(price.toString() + "TL");

        TextView productFeatures = findViewById(R.id.features);
        productFeatures.setText(feature);

        //yorumların ilk elemanı ekranda gösterilecek yorum son yapılan yorumdur.
        lastComment = findViewById(R.id.comment);
        lastComment.setText(comments.peek());

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                finish();
            }

        });


        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

        NavigationView navigation = (NavigationView) findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        Intent menu = new Intent(productContent.this, AnaEkran.class);
                        startActivity(menu);
                        finish();
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(productContent.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        Intent kategori = new Intent(productContent.this, Categories.class);
                        startActivity(kategori);
                        finish();
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(productContent.this, Ayarlar.class);
                        startActivity(ayarlar);
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basketmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch(item.getItemId()) {
            case R.id.basket:
                Intent i = new Intent(this, Sepet.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Yorum yapa tıklandığında yeni yapılan yorumu ürün yorumlarının sonuna ekleme
    public void takeComment(View v){

        EditText editComment = findViewById(R.id.editComment);
        editComment.setFocusable(false);
        if(!editComment.getText().toString().equals(null))
            comments.add(editComment.getText().toString());
        editComment.getText().clear();
        lastComment.setText(comments.pop());

    }

    // Yorumlar yeni ekranda gösterilir.
    public void showAllComments(View v){
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(comments);
        Intent intent = new Intent(this,commentsPage.class);
        intent.putStringArrayListExtra("allComments",temp);
        startActivity(intent);
    }

    public void addToBasket(View v){
       Toast.makeText(productContent.this,"Ürün sepete eklendi.",
                Toast.LENGTH_SHORT).show();
        Sepet.cart.add(newProduct);
    }

}
