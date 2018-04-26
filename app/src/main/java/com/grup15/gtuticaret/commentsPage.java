package com.grup15.gtuticaret;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Serkan Sorman on 09.04.2018.
 */

public class commentsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);

        //Yorumlar intentle yeni activitiye aktarılır
        ArrayList<String> comments = getIntent().getStringArrayListExtra("allComments");
        Collections.reverse(comments);
        ListView mainListView =  findViewById( R.id.mainListView );
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.row,comments);
        mainListView.setAdapter( listAdapter );

    }
}
