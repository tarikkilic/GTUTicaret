package com.grup15.gtuticaret;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by Emirhan Karagözoğlu on 11.05.2018.
 */
public class addProduct extends MenuBar {
    //telefondaki sectiginiz resmin yolu
    private Uri filePath;
    //böyle bisi neden yapmis bilmiyorum.
    private final int PICK_IMAGE_REQUEST = 71;

    //firebase degiskenleri
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        super.menuBar();
        //firebase in referansina ulasiyoruz.
        storage = FirebaseStorage.getInstance();
        storageReference =storage.getReference();
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
                //----------------> nname, nfeatures,category,nprice eklenecek.
                uploadImage();
                Toast.makeText(getApplicationContext(),"Ürünün satışa konuldu.",
                        Toast.LENGTH_SHORT).show();
            }
        }




    }


    //Resim yükleme eklenecek
    public void addImage(View view){
        chooseImage();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!= null && data.getData() != null){
            //secilen resmin dosya yolu filePath e atanir.
            filePath = data.getData();
        }
    }


    //startActivityForResult ile onAcivityResult birbirine bagli metotlar.
    private void chooseImage() {
        Intent intent = new Intent();
        //telefonun foto kismi aciliyor.
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        if(filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();
            //firebase de images klasörü olusturulur.
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            //telefonda secilen resmin dosya yolu parametre olarak konulur.
            ref.putFile(filePath)
                    //basarili olunca proggresbar kapatilir.
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            //burada url yi kayitli tutmaliyiz bir yerde.
                            //  ------------> taskSnapshot.getDownloadUrl().toString()
                            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    })
                    //basarisiz olunca hata ekrana bastirilir.
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Failed" +  e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    })
                    //islem devam ederken yüzde kaci yüklendigi gösterilir.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int)progress+"%");
                        }
                    });
        }
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
