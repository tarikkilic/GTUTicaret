package com.grup15.gtuticaret;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

import static com.grup15.gtuticaret.KayitOl.file;

/**
 * Created by Emirhan Karagözoğlu on 11.05.2018.
 */
public class addProduct extends MenuBar {
    //telefondaki sectiginiz resmin yolu
    private Uri filePath;
    //böyle bisi neden yapmis bilmiyorum.
    private final int PICK_IMAGE_REQUEST = 71;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    //firebase degiskenleri
    private FirebaseStorage storage;
    private StorageReference storageReference;
    //ürün bilgilerini tutar
    private Product newProduct;
    //button text vs.
    private Spinner spinner;
    private EditText name;
    private EditText features;
    private EditText price;
    //image url
    private String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        super.menuBar();

        //firebase in referansina ulasiyoruz.
        storage = FirebaseStorage.getInstance();
        storageReference =storage.getReference();
        //edittext leri init edilir.
        spinner = findViewById(R.id.spinner1);
        name = findViewById(R.id.name);
        features = findViewById(R.id.description);
        price = findViewById(R.id.editPrice);
    }


    public void addProductToList(View view){
        //textlere yazilan degerler tutulur.
        String nname = name.getText().toString();
        String nfeatures = features.getText().toString();
        String category =  String.valueOf(spinner.getSelectedItem()).toUpperCase();
        String nprice = price.getText().toString();
        if(nname.length() == 0 || nfeatures.length() == 0 || category.length() == 0 || nprice.length() == 0)
            alert("Eksik ürün bilgisi girdiniz");
        else {
            if(category.equals("KATEGORILER"))
                alert("Lütfen bir kategori seçiniz.");
            else{
                //product objesi init edilir.
                newProduct = new Product();
                newProduct.setName(nname);
                newProduct.setFeatures(nfeatures);
                newProduct.setType(category);
                newProduct.setPrice(Double.parseDouble(nprice));
                newProduct.setImageCode(imageUrl);
                newProduct.setId((nname+Giris.whoami).hashCode());
                mDatabase.child("Urunler").child(String.valueOf(newProduct.getId())).setValue(newProduct);
                System.productList.add(newProduct);
                Toast.makeText(getApplicationContext(),"Ürünün satışa konuldu.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    //Resim ceker
    public void addImage(View view){
        chooseImage();
    }
    //resim upload edilir
    public void upload(View view){
        uploadImage();
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
                            //resmin url'si tutulur.
                            imageUrl = taskSnapshot.getDownloadUrl().toString();
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
