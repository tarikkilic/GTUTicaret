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

    private String name,surname,password,email,salt,balanceLeft,balanceRight;
    private Stack<Comment> comments;
    static LinkedList<Product> cart = new LinkedList<>();


    public User(String name){
        this.name = name;
        this.comments = new Stack<>();

    }




    public User(String n,String s,String e,String p,String sT,String bL,String bR){
        name = n;
        surname = s;
        email = e;
        password = p;
        balanceLeft = bL;
        balanceRight = bR;
        salt = sT;
    }

    public User(String e, String p,String s,String bL,String bR){
        email = e;
        password = p;
        salt = s;
        balanceLeft = bL;
        balanceRight = bR;
    }


    public User(String name,Stack<Comment> comments){
        this.name = name;
        this.comments = (Stack<Comment>) comments.clone();
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getSalt(){
        return salt;
    }
    public User(){

    }


    public String getName() {
        return name;
    }

    public Stack<Comment> getComments() {
        return comments;
    }



    public void setName(String name) {
        this.name = name;
    }


    public void setComments(Stack<Comment> comments) {
        this.comments = comments;
    }





    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
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


    public String getBalanceLeft() {
        return balanceLeft;
    }

    public void setBalanceLeft(String balanceLeft) {
        this.balanceLeft = balanceLeft;
    }

    public String getBalanceRight() {
        return balanceRight;
    }

    public void setBalanceRight(String balanceRight) {
        this.balanceRight = balanceRight;
    }
}
