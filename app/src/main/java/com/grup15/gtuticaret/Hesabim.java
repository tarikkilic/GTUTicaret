package com.grup15.gtuticaret;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Celal Can on 9.04.2018.
 */

public class Hesabim extends MenuBar {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hesabim);
        TextView kisiselbilgi = (TextView) findViewById(R.id.kisiselbilgiler);
        //kisiselbilgi.setText(get_name() +"\n" + get_mail());
        kisiselbilgi.setText(Giris.whoami);
        Typeface tf = Typeface.createFromAsset(getAssets(), "opensanss.ttf");
        kisiselbilgi.setTypeface(tf);
        Button geriBildirim = (Button) findViewById(R.id.geribildirim);
        geriBildirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {"gtugrup15@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GTÜ E-Alışveriş Geri Bildirim");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Geri Bildiriminiz");

                startActivity(Intent.createChooser(emailIntent, "Lütfen Mail Uygulaması Seçiniz."));

        }});

        super.menuBar();
    }
}
