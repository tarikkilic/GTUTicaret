package com.grup15.gtuticaret;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Giris extends AppCompatActivity  {

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String password,email;

    private void girisBasarili(){
            /*******************************************************************************/
            toast(0);
            Intent kayit= new Intent(Giris.this, AnaEkran.class);
            startActivity(kayit);
            finish();
            /**********************************************************************************/

    }

    private void girisBasarisiz(){
        toast(2);

    }

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
        else if(i==3){
            Toast.makeText(this, "İnternet Bağlantınızı Kontrol Ediniz", Toast.LENGTH_LONG).show();
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
                if(isNetworkAvailable()) {
                    Intent kayitOlEkrani = new Intent(Giris.this, KayitOl.class);
                    startActivity(kayitOlEkrani);
                    finish();
                }
                else{
                    toast(3);
                }
            }

        });

        findViewById(R.id.giris).setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View view) {
                        if (isNetworkAvailable()) {

                            email = ((EditText) findViewById(R.id.epostaGiris)).getText().toString();
                            password = ((EditText) findViewById(R.id.passwordGiris)).getText().toString();

                            mDatabase.child("Users").child(String.valueOf(email.hashCode())).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        HashMap hp = (HashMap) ds.getValue();
                                        String salt = (String) hp.get("salt");
                                        try {
                                            password = Sha256hash.generate(password);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        } catch (NoSuchAlgorithmException e) {
                                            e.printStackTrace();
                                        }
                                        password = password + salt;
                                        if (password.equals(hp.get("password").toString()) && email.equals(hp.get("email").toString()))
                                            girisBasarili();
                                        else {
                                            Log.d("OKOKOKOKAOSKDPOAJPOS", "AQQWESA");
                                            toast(1);
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("OKOKOKOKAOSKDPOAJPOS", "RAMAZAN");
                                    toast(1);
                                }
                            });
                            Log.d("OKOKOKOKAOSKDPOAJPOS", "TARIK");
                            //GirisBasarisiz uyari vermiyor
                            //notmyproblem
                            girisBasarisiz();
                        }
                        else{
                            toast(3);
                        }
                    }
                }
        );
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


