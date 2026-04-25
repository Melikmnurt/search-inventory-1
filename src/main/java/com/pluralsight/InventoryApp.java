package com.pluralsight;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

public class InventoryApp {
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            //menu asking user what they want to do
            System.out.print("What do you want to do?\n" +
                    "1- List all products\n" +
                    "2- Lookup a product by its id\n" +
                    "3- Find all products within a price range\n" +
                    "4- Add a new product\n" +
                    "5- Quit the application\n" +
                    "Enter command: "
            );
            //user input for command
            int command = myScanner.nextInt();
            myScanner.nextLine();
            //switch statement for the choices
            switch (command) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    listByProductID(getInventory());
                    break;
                case 3:
                    listByPriceRange(getInventory());
                    break;
                case 4:
                    addAProduct(getInventory());
                    break;
                case 5:
                    //exits out of the program
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("You entered an invalid choice. Try again.");
            }
        }
    }

    public static ArrayList<Product> getInventory() {

        //create EMPTY ArrayList
        ArrayList<Product> inventory = new ArrayList<>();
        //try and catch statement
        try {
            //file reader and buffered reader to read inventory.csv
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader buffReader = new BufferedReader(fileReader);

            //initialize String line to assign it the lines that buff reader
            String line;
            while ((line = buffReader.readLine()) != null) {

                //parsing the information being read and splitting by |
                //3 parts, ID, product name, and price
                String[] productParts = line.split("\\|");
                int ID = Integer.parseInt(productParts[0]);
                String pName = productParts[1];
                double price = Double.parseDouble(productParts[2]);

                //creating new object to store the parts into them
                Product product = new Product(ID, pName, price);

                //adding the object product to the Array List
                inventory.add(product);
            }
            //closing the file reader
            fileReader.close();
            buffReader.close();
            //prints file could not be found if error
        } catch (Exception e) {
            System.out.println("File could not be found.");
        }
        return inventory;
    }
    //method for listing all products
    public static void listAllProducts() {

        ArrayList<Product> inventory = getInventory();
        System.out.print("\n");

        inventory.sort(Comparator.comparing(Product::getName));

        for(int i = 0; i < inventory.size(); i++){
            //storing the product information into objectP
            Product objectP = inventory.get(i);

            //using getters to get that information and printing it out
            System.out.println(objectP.getId() + "|" + objectP.getName() + "|" + objectP.getPrice());
        }
    }
    //method for finding by product id
    public static void listByProductID(ArrayList<Product> inventory) {

        System.out.print("Enter a product ID: ");
        int ID = myScanner.nextInt();

        //iterates through the array inventory and checks
        //if the ID user input matches an ID in the inventory,
        //if it does, it will print out the product, otherwise it wasn't found
        for (Product product : inventory) {
            if (product.getId() == ID) {
                System.out.println(product.getId() + "|" + product.getName() + "|" + product.getPrice());
                return;
            }
        }
        System.out.println("Product was not found. Try again.");
    }
    //method for finding product by price range
    public static void listByPriceRange(ArrayList<Product> inventory) {
        //prompts the user to enter a minimum price
        System.out.print("Enter a minimum price: ");
        double userMin = myScanner.nextDouble();

        System.out.print("Enter a maximum price: ");
        double userMax = myScanner.nextDouble();

        for (Product product : inventory) {
            if (product.getPrice() >= userMin && product.getPrice() <= userMax) {
                System.out.println(product.getId() + "|" + product.getName() + "|" + product.getPrice());
            }
        }

    }
    //method for adding a product to inventory
    public static void addAProduct(ArrayList<Product> inventory) {

        try {
            FileWriter writer = new FileWriter("src/main/resources/inventory.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(writer);
            //prompting user to add a product
            System.out.println("Let's add a product.");
            System.out.println("--------------------");
            System.out.print("Enter an ID: ");
            int pID = myScanner.nextInt();
            myScanner.nextLine();
            System.out.print("Enter a product name: ");
            String pName = myScanner.nextLine();
            System.out.print("Enter a price for the product: ");
            double pPrice = myScanner.nextDouble();

            Product addProduct = new Product(pID, pName, pPrice);
            inventory.add(addProduct);
            bufWriter.write("\n" + addProduct.getId() + "|" + addProduct.getName() + "|" + addProduct.getPrice());

            System.out.println("You've added the product: "
                    + addProduct.getId()
                    + "|" + addProduct.getName()
                    + "|" + addProduct.getPrice()
            );
            bufWriter.close();
        } catch (Exception e) {
            System.out.println("Could not write to file.");
        }
    }
}