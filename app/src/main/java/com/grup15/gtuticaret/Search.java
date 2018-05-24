package com.grup15.gtuticaret;




import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Search {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Urunler");
    HashMap<String,Product> everything;
    public ArrayList<Product> search(String key){
        ArrayList<Product> result = new ArrayList<>();
        everything = new HashMap<>();
        mDatabase.child("ELEKTRONIK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren() ;
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    Product product = dataSnapshot1.getValue(Product.class);
                    everything.put(product.getName(),product);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String [] arr = key.split("\\s+");
        if(everything.get(key) != null)
            result.add(everything.get(key));
        for(int i =0;i<arr.length;i++)
            if(everything.get(arr[i]) != null)
                result.add(everything.get(arr[i]));
        return result;
    }
}
