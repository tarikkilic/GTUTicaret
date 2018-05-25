package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class Chat extends MenuBar{
    //to get current time
    private Calendar time;
    private Message sms=new Message();
    //firebase den gelen tüm mesajları tutacak
    private LinkedList<Message> messages;
    //mesaj gönderme butonu
    private FloatingActionButton send;
    //bu konusma sırasında ekranda smsleri gmstermek için
    private ArrayList<String> allsms=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        //bunu ekleyince exception fırlatıyor.
        //super.menuBar();
        send = findViewById(R.id.send);
        messages=new LinkedList<>();
        int count=0;
        //burası ilk açıldığında tüm mesajları yükler
        //normalde buraya linked listte size a kadar devam et denmeli ama sonsuz döngüye giriyor.
        while (count<allsms.size()) {
            showMessage(messages.get(count), messages.get(count).message,allsms);
            count++;
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gelen mesajı alır
                EditText text = (EditText) findViewById(R.id.sms);
                String txt = String.valueOf(text.getText());
                //boş mesaj göndermemesi için
                if (txt.length() > 0) {
                    sms = new Message();
                    //gönderme zamanı
                    sms.setSend_time(time);
                    //gönderen kişi bunu sepete ekledeki satın al butonuna tıkladığımızda almalıyız orayı yazmadım
                    sms.setUser();
                    //mesaj
                    showMessage(sms, txt,allsms);
                    text.setText("");
                }
            }
        });

    }

    private void showMessage (Message m, String text,ArrayList<String> sms){
        ListView list = (ListView) findViewById(R.id.sms_list);
        m.setMessage(text);
        //yeni mesajlar linked list e eklenir
        this.messages.addFirst(m);

        /*burada mesajı ekrana eklemek için ilk önce stringBuildera ekler düzenler sonra stringe
        ekleyip onu arrayliste atar.
         */
        StringBuilder s = new StringBuilder();
        s.append(m.getSend_time());
        s.append("\t"+"\t"+"\t"+"\t"+"\t"+"\t");
        s.append(m.getUser());
        s.append("\n");
        s.append(m.message);
        String ss= String.valueOf(s);
        sms.add(ss);
        String allsms[]=new String[sms.size()];
        for(int i=0;i<sms.size();i++){
            allsms[i]=sms.get(i);
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, allsms);
        list.setAdapter(adapter);

    }


    private class Message{

        private String message;
        //user mail adress
        private String user;
        //message time day/month/year
        private String send_time;

        public String getSms_recipient() {
            return sms_recipient;
        }

        public void setSms_recipient(String sms_recipient) {
            this.sms_recipient = sms_recipient;
        }

        //message recipient name.
        private String sms_recipient;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUser() {
            return user;
        }
        //sets user name with whoami
        public void setUser() {
            this.user = Giris.whoami;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(Calendar time1) {
            SimpleDateFormat sp=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            time1=Calendar.getInstance();
            this.send_time = sp.format(time1.getTime());
        }
    }
}