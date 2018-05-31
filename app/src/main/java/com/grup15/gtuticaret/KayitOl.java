package com.grup15.gtuticaret;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
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
import java.util.regex.Pattern;


public class KayitOl extends AppCompatActivity {

    public static Map<String, String> ePostaVeParola = new HashMap<String, String>();
    public static File file;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private void toast(){
        Toast.makeText(this, "Kayıt Başarılı. Lütfen Giriş Yapınız.", Toast.LENGTH_LONG).show();
    }
    private static int i =0;
    //Kayit ol basarisizsa
    private void toast1(){
        Toast.makeText(this, "Kayıt Başarısiz. Lütfen Tekrar Deneyiniz.", Toast.LENGTH_LONG).show();
    }
    private void toast2(){
        Toast.makeText(this, "E-posta uzantısı @gtu.edu.tr olmalıdır.", Toast.LENGTH_LONG).show();
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
                        //String name = (((EditText)findViewById(R.id.name)).getText()).toString().trim();
                        //String surname = (((EditText)findViewById(R.id.surname)).getText()).toString().trim();
                        String email = (((EditText)findViewById(R.id.eposta)).getText()).toString().trim();
                        String password = (((EditText)findViewById(R.id.password)).getText()).toString().trim();
                        if(isEmailValid(email)==false || password.length() == 0){
                            if(i==99){
                                toast2();
                            }
                            else{
                                toast1();
                            }
                            startActivity(new Intent(KayitOl.this,KayitOl.class));
                            finish();
                        }
                        else{
                            try {
                                password = Sha256hash.generate(password);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            String salt =  Sha256hash.getNextSalt();
                            password = password + salt;
                            User user = new User(email,password,salt,"0","0");
                            mDatabase.child("Users").child(String.valueOf(email.hashCode())).child(String.valueOf(email.hashCode())).setValue(user);
                            mDatabase.child("Graph").child(String.valueOf(email.hashCode())).child("biletW").setValue(user.getBiletW());
                            mDatabase.child("Graph").child(String.valueOf(email.hashCode())).child("deneyW").setValue(user.getDeneyW());
                            mDatabase.child("Graph").child(String.valueOf(email.hashCode())).child("esyaW").setValue(user.getEsyaW());
                            mDatabase.child("Graph").child(String.valueOf(email.hashCode())).child("elekW").setValue(user.getElekW());
                            mDatabase.child("Graph").child(String.valueOf(email.hashCode())).child("kitapW").setValue(user.getKitapW());
                            toast();
                            Intent giris= new Intent(KayitOl.this, Giris.class);
                            startActivity(giris);
                            finish();
                        }

                    }
                });
    }
    boolean isEmailValid(String email){
        i=0;
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(pattern.matcher(email).matches()==false){
            return false;
        }
        else{
            if(email.substring(email.indexOf('@')).equals(new String("@gtu.edu.tr"))){
                return true;
            }
            else{
                toast2();
                i=99;
                return false;
            }
        }
    }
}
