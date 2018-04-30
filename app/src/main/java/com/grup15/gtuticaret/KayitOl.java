package com.grup15.gtuticaret;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class KayitOl extends AppCompatActivity {

    public static Map<String, String> ePostaVeParola = new HashMap<String, String>();
    public static File file;

    private void toast(){
        Toast.makeText(this, "Kayıt Başarılı. Lütfen Giriş Yapınız.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kayitol);

        findViewById(R.id.geri).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent giris = new Intent(KayitOl.this, Giris.class);
                startActivity(giris);
                finish();

            }

        });

        findViewById(R.id.kayitOl).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        //ePostaVeParola.put((((EditText)findViewById(R.id.eposta)).getText()).toString(),(((EditText)findViewById(R.id.password)).getText()).toString());
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        User user = null;
                        try {
                            user = new User(((EditText) findViewById(R.id.eposta)).getText().toString().trim(), Sha256hash.generate(((EditText) findViewById(R.id.password)).getText().toString().trim()));
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        mDatabase.child("Users").child(String.valueOf((((EditText)findViewById(R.id.eposta)).getText()).toString().hashCode())).setValue(user);

                        toast();
                        Intent giris= new Intent(KayitOl.this, Giris.class);
                        startActivity(giris);
                        finish();
                    }
                });
    }
}
