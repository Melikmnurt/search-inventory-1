package com.pluralsight;

import java.util.ArrayList;         //I need it for Array List

public class InventoryApp {

    //This method creates and returns a list of products
    public static ArrayList<Product> getInventory(){

        //Create an empty list
        ArrayList<Product> inventory = new ArrayList<>();

        //adding products manually (hardcoded)
        inventory.add(new Product(1234, "Hammer", 19.49));
        inventory.add(new Product(2345, "Box of nails", 9.29));
        inventory.add(new Product(4567, "2x4 wood", 9.99));
        inventory.add(new Product(5678, "Screwdriver", 7.49));
        inventory.add(new Product(6789, "Drill", 49.99));

        //Return the list
        return inventory;
    }
    //Main method (am starting the program here)
    public static void main(String []args){

        //Call the method and store the list
        ArrayList<Product> inventory = getInventory();

        //Loop through the list and print each product
        for (Product p: inventory){
            System.out.println(p);
        }
    }
}
