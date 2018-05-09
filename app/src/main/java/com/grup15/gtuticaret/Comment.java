package com.grup15.gtuticaret;

import java.io.Serializable;

/**
 * Created by Serkan Sorman on 09.05.2018.
 */

public class Comment implements Serializable{

    private String commentText;
    private String commentDate;
    private String userName;


    public Comment(String commentText,String commentDate,String userName){
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.userName = userName;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentText() {
        return commentText;
    }
}