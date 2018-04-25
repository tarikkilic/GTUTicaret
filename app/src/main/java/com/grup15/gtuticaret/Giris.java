package com.grup15.gtuticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class Giris extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giris);

        findViewById(R.id.yeniKullaniciOlustur).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent kayit= new Intent(Giris.this, KayitOl.class);
                startActivity(kayit);


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
                            Intent kayit= new Intent(Giris.this, KayitOl.class);// celal ana sayfası başlayacak
                            startActivity(kayit);
                            /**********************************************************************************/
                        }
                        else if(KayitOl.ePostaVeParola.get(((EditText)findViewById(R.id.epostaGiris)).getText().toString())==null){
                            /*******************************************************************************/
                            //kullanıcı adı hatalıysa girer(celal)
                            /*******************************************************************************/

                        }
                        else {
                            /*******************************************************************************/
                            //şifre hatalı mesajı ekrana verilecek(celal)
                            /*******************************************************************************/

                        }

                    }
                });
    }



}

