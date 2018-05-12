package com.grup15.gtuticaret;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Serkan Sorman on 12.05.2018.
 */

public abstract class System extends AppCompatActivity{

    static ArrayList<Product> productList = new ArrayList<>();

    public static void showAllComments(Context context,User userComesProduct) {

        Intent intent = new Intent(context, commentsPage.class);
        ArrayList<Comment> arrComment = new ArrayList<>();
        arrComment.addAll(userComesProduct.getComments());
        intent.putExtra("comments", arrComment);
        context.startActivity(intent);
    }


    public void addProductToList(View view){

        Spinner spinner = findViewById(R.id.spinner1);
        EditText name = findViewById(R.id.name);
        EditText features = findViewById(R.id.description);
        EditText price = findViewById(R.id.editPrice);

        Product newProduct = new Product(name.getText().toString(),features.getText().toString(),
                String.valueOf(spinner.getSelectedItem()).toUpperCase(),Double.parseDouble(price.getText().toString()),System.productList.size()+1,"@drawable/ps4");

        System.productList.add(newProduct);
    }

    /*
     * Gerekli metodlar eklenecek veya taşınacak
     */
}
