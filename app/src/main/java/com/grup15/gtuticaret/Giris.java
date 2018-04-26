package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class Giris extends AppCompatActivity  {

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
    protected void onCreate(Bundle savedInstanceState) {
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
                        try {
                            File file = new File(getDir("data", MODE_PRIVATE), "KayitOl.ePostaVeParola");

                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                            KayitOl.ePostaVeParola= (Map<String, String>) ois.readObject();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        if(KayitOl.ePostaVeParola.get(((EditText)findViewById(R.id.epostaGiris)).getText().toString())!=null
                                && ((EditText)findViewById(R.id.passwordGiris)).getText().toString()!=null
                                && KayitOl.ePostaVeParola.get(((EditText)findViewById(R.id.epostaGiris)).getText().toString()).
                                equals(((EditText)findViewById(R.id.passwordGiris)).getText().toString())){


                            /*******************************************************************************/
                            toast(0);
                            Intent kayit= new Intent(Giris.this, AnaEkran.class);
                            startActivity(kayit);
                            finish();
                            /**********************************************************************************/
                        }
                        else if(KayitOl.ePostaVeParola.get(((EditText)findViewById(R.id.epostaGiris)).getText().toString())==null){
                            /*******************************************************************************/
                            toast(1);
                            /*******************************************************************************/
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

