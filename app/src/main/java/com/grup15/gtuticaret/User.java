package com.grup15.gtuticaret;

public class User {
    private String email,password;


    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public User(String e, String p){
        email = e;
        password = p;
    }

    public User(){

    }
}
