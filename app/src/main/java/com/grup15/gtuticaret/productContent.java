package com.grup15.gtuticaret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class productContent extends AppCompatActivity {
    ArrayList<String> comments;
    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_content);

        int image = R.drawable.notebook;

        p = (Product) getIntent().getExtras().getSerializable("pro");

        String name = p.getName();

        String price = "1.464,99 TL";

        String feature = "Ekran Boyutu 15.6'\\' İnç İşlemci AMD A Series Marka Asus Maksimum Ekran Çözünürlük1366 x 768 Usb 3,0 DesteğiVar " +
                "Sistem Belleği (Gb)4 Gb Disk Kapasitesi1 Tb İşletim Sistemi Free Dos Ekran Kartı Belleği Paylaşımsız (2 Gb)";


        comments = new ArrayList<>();
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
        productPrice.setText(price);

        TextView productFeatures = findViewById(R.id.features);
        productFeatures.setText(feature);

        //yorumların ilk elemanı ekranda gösterilecek best commentdir.
        TextView bestComment = findViewById(R.id.comment);
        bestComment.setText(comments.get(0));

    }

    //Yorum yapa tıklandığında yeni yapılan yorumu ürün yorumlarının sonuna ekleme
    public void takeComment(View v){

        EditText editComment = findViewById(R.id.editComment);
        comments.add(editComment.getText().toString());
    }

    // Yorumlar yeni ekranda gösterilir.
    public void showAllComments(View v){

        Intent intent = new Intent(this,commentsPage.class);
        intent.putStringArrayListExtra("allComments",comments);
        startActivity(intent);
    }
}
