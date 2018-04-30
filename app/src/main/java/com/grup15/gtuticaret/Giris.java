package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Giris extends AppCompatActivity  {
    ArrayList<String> mails  = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    String mail,pass;

    private void toast(int i){
        if(i==0){
            Toast.makeText(this, "Giriş Başarılı.", Toast.LENGTH_LONG).show();
        }
        else if(i==1){
            Toast.makeText(this, "Bu E-posta Adresi Sisteme Kayıtlı Değil.", Toast.LENGTH_LONG).show();
        }
        else if(i==2){
            Toast.makeText(this, "Parola Hatalı!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giris);

        findViewById(R.id.kayit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent kayitOlEkrani = new Intent(Giris.this, KayitOl.class);
                startActivity(kayitOlEkrani);
                finish();


            }

        });

        findViewById(R.id.giris).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                         mail = ((EditText)findViewById(R.id.epostaGiris)).getText().toString();
                         pass = ((EditText)findViewById(R.id.passwordGiris)).getText().toString();
                        try {
                            pass = Sha256hash.generate(pass);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }

                        final boolean[] flag = {false};
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    HashMap tmp = (HashMap) ds.getValue();
                                    mails.add(tmp.get("email").toString());
                                    passwords.add(tmp.get("password").toString());
                                    if(mails.contains(mail))break;

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        if(mails.contains(mail) && passwords.contains(pass))
                            flag[0] = true;
                        if(flag[0]){


                            /*******************************************************************************/
                            toast(0);
                            Intent kayit= new Intent(Giris.this, AnaEkran.class);
                            startActivity(kayit);
                            finish();
                            /**********************************************************************************/
                        }
                        else {
                            /*******************************************************************************/
                            toast(2);
                            /*******************************************************************************/

                        }
                    }
                });
    }



}

