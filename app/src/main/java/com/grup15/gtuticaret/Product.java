package com.grup15.gtuticaret;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;


public class Product implements Serializable {
    private String name;
    private String features;
    private String type;
    private double price;
    private Integer id;
    private LinkedList<String> keyWords;
    private String imageCode;



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
}
