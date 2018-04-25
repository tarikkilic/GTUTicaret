package com.grup15.gtuticaret;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Serkan Sorman on 24.04.2018.
 */

public class Product implements Serializable {
    private String name;
    private String features;
    private String type;
    private double price;
    private int id;
    private LinkedList<String> keyWords;
    private Stack<String> comments;
    private String imageCode;

    public Product(){
        name = null;
        features = null;
        type = null;
        price = 0;
        id = 0;
        keyWords = null;
        comments = null;
        imageCode = null;
    }

    public String getType() {
        return type;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
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


    public LinkedList<String> getKeyWords() {
        return keyWords;
    }

    public Stack<String> getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKeyWords(LinkedList<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void setComments(Stack<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (id != product.id) return false;
        if (!name.equals(product.name)) return false;
        if (!features.equals(product.features)) return false;
        if (!keyWords.equals(product.keyWords)) return false;
        return comments.equals(product.comments);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", features='" + features + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", keyWords=" + keyWords +
                ", comments=" + comments +
                '}';
    }
}
