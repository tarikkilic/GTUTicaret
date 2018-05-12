package com.grup15.gtuticaret;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Serkan Sorman on 09.04.2018.
 */

public class commentsPage extends MenuBar {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        LinearLayout mainComments = findViewById(R.id.mainComments);

        //Yorumlar intentle yeni activitiye aktarılır
       ArrayList<Comment> comments=  (ArrayList<Comment>) getIntent().getExtras().getSerializable("comments");
       Collections.reverse(comments);

        for (int i = 0; i < comments.size(); ++i) {
            View view = getLayoutInflater().inflate(R.layout.comment_row, null);

            final TextView name = view.findViewById(R.id.userName);
            final TextView comment = view.findViewById(R.id.comment);
            final TextView date = view.findViewById(R.id.commentDate);

            name.setText(comments.get(i).getUserName());
            comment.setText(comments.get(i).getCommentText());
            date.setText(comments.get(i).getCommentDate());

            mainComments.addView(view);
        }

        super.menuBar();
    }
}
