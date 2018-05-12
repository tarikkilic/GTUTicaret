package com.grup15.gtuticaret;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by Emirhan Karagözoğlu on 11.05.2018.
 */
public class addProduct extends MenuBar {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        super.menuBar();
    }


    public void addProductToList(View view){

        Spinner spinner = findViewById(R.id.spinner1);
        EditText name = findViewById(R.id.name);
        EditText features = findViewById(R.id.description);
        EditText price = findViewById(R.id.editPrice);

        String nname = name.getText().toString();
        String nfeatures = features.getText().toString();
        String category =  String.valueOf(spinner.getSelectedItem()).toUpperCase();
        String nprice = price.getText().toString();
        Product newProduct = new Product();

        if(nname.length() == 0 || nfeatures.length() == 0 || category.length() == 0 || nprice.length() == 0)
            alert("Eksik ürün bilgisi girdiniz");
        else {
            newProduct = new Product(nname, nfeatures, category, Double.parseDouble(nprice), 5, "@drawable/ps4");
            if(System.productList.contains(newProduct))
                alert("Ürünün zaten satışa konuldu.");
            else if(newProduct.getType().equals("KATEGORILER"))
                alert("Lütfen bir kategori seçiniz.");
            else{
                System.productList.add(newProduct);
                Toast.makeText(getApplicationContext(),"Ürünün satışa konuldu.",
                        Toast.LENGTH_SHORT).show();
            }
        }




    }


    //Resim yükleme eklenecek
    public void addImage(View view){
        /**
         *
         */
    }

    private void alert(String message){

        new AlertDialog.Builder(this)
                .setTitle("UYARI!")
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


}
