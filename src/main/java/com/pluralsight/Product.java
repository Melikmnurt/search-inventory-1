package com.pluralsight;

public class Product {           // This class represents one product

    //Variables to store product info
    private int id;
    private String name;
    private double price;

    //Constructor (this runs when we create a new product)
    public Product(int id, String name, double price) {
        this.id = id;                  //Stores the id
        this.name = name;             //Stores the name
        this.price = price;          //Stores the price
    }
    //This method helps print the product
    @Override
    public String toString(){
        return id + " | " + name + " | " + price;
    }
}
