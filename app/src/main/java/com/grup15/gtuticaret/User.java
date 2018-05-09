package com.grup15.gtuticaret;

import java.io.Serializable;
import java.util.Stack;



public class User implements Serializable {

    private String name;
    private Stack<Comment> comments;


    public User(String name){
        this.name = name;
        this.comments = new Stack<>();
    }


    public User(String name,Stack<Comment> comments){
        this.name = name;
        this.comments = (Stack<Comment>) comments.clone();
    }

    public String getName() {
        return name;
    }

    public Stack<Comment> getComments() {
        return comments;
    }
}
