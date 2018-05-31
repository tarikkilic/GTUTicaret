package com.grup15.gtuticaret;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Serkan Sorman on 09.04.2018.
 */

public class commentsPage extends MenuBar {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        LinearLayout mainComments = findViewById(R.id.mainComments);

        //Yorumlar poplanarak show edilmek üzere yeni bir stacke alınır.

        Stack<Comment> showedComments = new Stack<>();
        showedComments.addAll(System.comments);


        for (int i=0; i<System.comments.size(); ++i) {
            View view = getLayoutInflater().inflate(R.layout.comment_row, null);

            final TextView name = view.findViewById(R.id.userName);
            final TextView comment = view.findViewById(R.id.comment);
            final TextView date = view.findViewById(R.id.commentDate);

            Comment c = showedComments.pop();

            name.setText(c.getUserName());
            comment.setText(c.getCommentText());
            date.setText(c.getCommentDate());

            mainComments.addView(view);
        }

        super.menuBar();
    }
}
