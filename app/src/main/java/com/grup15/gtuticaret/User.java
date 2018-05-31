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
    private double elekW;
    private double deneyW;
    private double esyaW;
    private double biletW;
    private double kitapW;

    public double getElekW() {
        return elekW;
    }

    public void setElekW(double elekW) {
        this.elekW = elekW;
    }

    public double getDeneyW() {
        return deneyW;
    }

    public void setDeneyW(double deneyW) {
        this.deneyW = deneyW;
    }

    public double getEsyaW() {
        return esyaW;
    }

    public void setEsyaW(double esyaW) {
        this.esyaW = esyaW;
    }

    public double getBiletW() {
        return biletW;
    }

    public void setBiletW(double biletW) {
        this.biletW = biletW;
    }

    public double getKitapW() {
        return kitapW;
    }

    public void setKitapW(double kitapW) {
        this.kitapW = kitapW;
    }



    public User(String email){
        this.email = email;
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
        elekW = 1000.0;
        kitapW = 1000.0;
        deneyW = 1000.0;
        biletW = 1000.0;
        esyaW = 1000.0;
    }


    public User(User user){
        name = user.name;
        surname = user.surname;
        email = user.email;
        password = user.password;
        balanceLeft = user.balanceLeft;
        balanceRight = user.balanceRight;
        salt = user.salt;
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
        return email != null ? email.hashCode() : 0;
    }

    public boolean addToCart(Product newProduct){

        if(newProduct == null || cart.contains(newProduct))
            return false;
        else{
            cart.add(newProduct);
            return true;
        }
    }


    public void comment(ArrayList<TextView> comment,EditText editComment){

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        editComment.setFocusable(false);

        System.comments.add(new Comment(editComment.getText().toString(), formattedDate,getEmail()));

        comment.get(0).setText(System.comments.peek().getCommentText());
        comment.get(1).setText(System.comments.peek().getCommentDate());
        comment.get(2).setText(System.comments.peek().getUserName());

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;
    }
}
