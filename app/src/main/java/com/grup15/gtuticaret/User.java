package com.grup15.gtuticaret;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;


public class User implements UserInterface {

    private String name;
    private Stack<Comment> comments;
    private double balance;
    static LinkedList<Product> cart = new LinkedList<>();


    public User(String name){
        this.name = name;
        this.comments = new Stack<>();

    }

    public User(String name, Stack<Comment> comments, double balance) {
        this.name = name;
        this.comments = comments;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setComments(Stack<Comment> comments) {
        this.comments = comments;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Double.compare(user.balance, balance) != 0) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return comments != null ? comments.equals(user.comments) : user.comments == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }



    public boolean addToCart(Product newProduct){

        if(newProduct == null || cart.contains(newProduct))
           return false;
        else{
            cart.add(newProduct);
            return true;
        }
    }


    public void comment(ArrayList<TextView> comment, User userComesProduct, EditText editComment){

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        editComment.setFocusable(false);
        if (!editComment.getText().toString().equals(null))
            userComesProduct.getComments().add(new Comment(editComment.getText().toString(), formattedDate,getName()));

        comment.get(0).setText(userComesProduct.getComments().peek().getCommentText());
        comment.get(1).setText(userComesProduct.getComments().peek().getCommentDate());
        comment.get(2).setText(userComesProduct.getComments().pop().getUserName());

        editComment.getText().clear();
    }
}
