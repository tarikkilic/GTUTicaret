package com.grup15.gtuticaret;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Serkan Sorman on 24.04.2018.
 */

public class Product {
    private String name;
    private String features;
    private double price;
    private int numOfSell;
    private int id;
    private LinkedList<String> keyWords;
    private Stack<String> comments;

    public Product(String name,String features,double price,int numOfSell,int id,
                   LinkedList<String> keyWords,Stack<String> comments ){
        this.name = name;
        this.features = features;
        this.price = price;
        this.numOfSell = numOfSell;
        this.id = id;
        this.keyWords = keyWords;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getFeatures() {
        return features;
    }

    public int getId() {
        return id;
    }

    public int getNumOfSell() {
        return numOfSell;
    }

    public LinkedList<String> getKeyWords() {
        return keyWords;
    }

    public Stack<String> getComments() {
        return comments;
    }
}
