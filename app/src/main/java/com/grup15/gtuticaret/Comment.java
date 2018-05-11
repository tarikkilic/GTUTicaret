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

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentText(String commentText) {

        this.commentText = commentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null)
            return false;
        if (commentDate != null ? !commentDate.equals(comment.commentDate) : comment.commentDate != null)
            return false;
        return userName != null ? userName.equals(comment.userName) : comment.userName == null;
    }

    @Override
    public int hashCode() {
        int result = commentText != null ? commentText.hashCode() : 0;
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }


}