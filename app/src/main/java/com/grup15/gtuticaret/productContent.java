package com.grup15.gtuticaret;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        comments.add("Gayet memnunuz fakat arastirmadan aldigimiz icin dvd sürücüsü olmadigini bilmiyorduk o biraz sasirtti. Birde isletim sistemi windows degil Endless diye bir sistem. Ama kullanimi kolay simdilik bir sorun yok memnunuz.");
        comments.add("Harika sorunsuz bir ürün");
        comments.add("güzel ürün teşekkür ederiz");
        comments.add("Evimi sattım ürünün kutusunda yaşıyorum teşekkürler");
        comments.add("Fiyat performans ürünü :)");
        comments.add("İsletim sistemi windows degil Endless diye bir sistem. Ama kullanimi kolay simdilik bir sorun yok memnunuz.");
        comments.add("simdilik bir sorun yok memnunuz.");


        ImageView productImage = findViewById(R.id.productImage);
        productImage.setImageResource(image);

        TextView productName = findViewById(R.id.productName);
        productName.setText(name);

        TextView productPrice = findViewById(R.id.productCost);
        productPrice.setText(price.toString());

        TextView productFeatures = findViewById(R.id.features);
        productFeatures.setText(feature);

        //yorumların ilk elemanı ekranda gösterilecek yorum son yapılan yorumdur.
        lastComment = findViewById(R.id.comment);
        lastComment.setText(comments.peek());

    }

    //Yorum yapa tıklandığında yeni yapılan yorumu ürün yorumlarının sonuna ekleme
    public void takeComment(View v){

        EditText editComment = findViewById(R.id.editComment);
        editComment.setFocusable(false);
        if(!editComment.getText().toString().isEmpty())
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

    public void passToBasket(View v){
        Intent intent = new Intent(this,Sepet.class);
        startActivity(intent);
    }
}
