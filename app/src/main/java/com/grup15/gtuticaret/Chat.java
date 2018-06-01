package com.grup15.gtuticaret;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Chat extends MenuBar{
    //to get current time
    private Calendar time;
    private Message sms=new Message();
    //firebase den gelen tüm mesajları tutacak
    private LinkedList<Message> messages;
    private LinkedList<Object> temp;
    private HashMap hp;
    //mesaj gönderme butonu
    private FloatingActionButton send;
    //bu konusma sırasında ekranda smsleri gmstermek için
    private ArrayList<String> allsms=new ArrayList();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Mesajlar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        //bunu ekleyince exception fırlatıyor.
        //super.menuBar();
        messages=new LinkedList<>();
        temp = new LinkedList<>();
        hp = new HashMap();
        int count=0;
        //burası ilk açıldığında tüm mesajları yükler
        //normalde buraya linked listte size a kadar devam et denmeli ama sonsuz döngüye giriyor.
        mDatabase.child(String.valueOf(Giris.whoami.hashCode())).child(Inbox.whichone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap hp = (HashMap) ds.getValue();
                    Message ms = new Message((String)hp.get("message"),(String)hp.get("user"),(String)hp.get("send_time"));
                    showMessage(ms,ms.message,allsms,true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.menuBar();

    }

    private void showMessage (Message m, String text,ArrayList<String> sms){
        ListView list = (ListView) findViewById(R.id.sms_list);
        m.setMessage(text);
        //yeni mesajlar linked list e eklenir
        mDatabase.child(Giris.whoami).child(String.valueOf(messages.size())).setValue(m);
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
    private void showMessage (Message m, String text,ArrayList<String> sms,boolean t){
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


    public static class Message{

        private String message;
        //user mail adress
        private String user;
        //message time day/month/year
        private String send_time;
        private String sender;
        private String reciever;


        public Message(String m,String sendTime,String send,String rec){
            message = m;
            send_time = sendTime;
            sender = send;
            reciever = rec;

        }

        public Message(){

        }
        public Message(String m,String u,String s){
            message = m;
            user = u;
            send_time = s;
        }

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

        public void setSend_time() {
            Calendar time1;
            SimpleDateFormat sp=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            time1=Calendar.getInstance();
            this.send_time = sp.format(time1.getTime());
        }
    }
}
