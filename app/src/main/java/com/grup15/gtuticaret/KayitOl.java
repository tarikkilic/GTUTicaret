package com.grup15.gtuticaret;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
                        ePostaVeParola.put((((EditText)findViewById(R.id.eposta)).getText()).toString(),(((EditText)findViewById(R.id.password)).getText()).toString());

                        File file = new File(getDir("data", MODE_PRIVATE), "KayitOl.ePostaVeParola");
                        ObjectOutputStream outputStream = null;
                        try {
                            outputStream = new ObjectOutputStream(new FileOutputStream(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.writeObject(KayitOl.ePostaVeParola);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        toast();
                        Intent giris= new Intent(KayitOl.this, Giris.class);
                        startActivity(giris);
                        finish();
                    }
                });
    }
}
