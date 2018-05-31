package com.grup15.gtuticaret;

import android.support.annotation.NonNull;

import com.google.firebase.database.ServerValue;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;


public class Product implements Comparable<Product>,Serializable{
    private String name;
    private String features;
    private String type;
    private double price;
    private Integer id;
    private LinkedList<String> keyWords;
    private String imageCode;
    private String seller;



    public Product(String name, String features, String type, double price, int id, String imageCode) {
        this.name = name;
        this.features = features;
        this.type = type;
        this.price = price;
        this.id = id;
        this.imageCode = imageCode;
    }



    public Product(){
        name = null;
        features = null;
        type = null;
        price = 0;
        id = 0;
        keyWords = null;
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

    public Integer getId() {
        return id;
    }


    public LinkedList<String> getKeyWords() {
        return keyWords;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (!name.equals(product.name)) return false;
        return features.equals(product.features);

    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", features='" + features + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", keyWords=" + keyWords +
                '}';
    }

    public Product(String name) {
        this.name = name;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


    @Override
    public int compareTo(@NonNull Product product) {

        return getName().compareTo(product.getName());
    }
}

class artanFiyat implements Comparator<Product>{
    @Override
    public int compare(Product one, Product two) {
        return Double.compare(one.getPrice(),two.getPrice());
    }
}


class azalanFiyat implements Comparator<Product>{
    @Override
    public int compare(Product one, Product two) {
        return Double.compare(two.getPrice(),one.getPrice());
    }
}

class artanIsim implements Comparator<Product>{
    @Override
    public int compare(Product one, Product two) {
        return  one.getName().toUpperCase().compareTo(two.getName().toUpperCase());
    }
}

class azalanIsim implements Comparator<Product>{
    @Override
    public int compare(Product one, Product two) {
        return  two.getName().toUpperCase().compareTo(one.getName().toUpperCase());
    }
}