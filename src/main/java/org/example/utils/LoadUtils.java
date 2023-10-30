package org.example.utils;

import org.example.models.Category;
import org.example.models.Product;

import java.util.ArrayList;

public class LoadUtils {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public static void onLoad() {
        Category shirtCategory = new Category(1, "Shirt");
        categories.add(shirtCategory);
        Category tshirtCategory = new Category(2, "TShirt");
        categories.add(tshirtCategory);
        Category pantCategory = new Category(3, "Pant");
        categories.add(pantCategory);
        Category shoeCategory = new Category(4, "Shoe");
        categories.add(shoeCategory);
        Category watchCategory = new Category(5, "Watch");
        categories.add(watchCategory);

        Product shirtProduct1 = new Product(101,"Calvinklein","Blue",600,4.9, shirtCategory);
        Product shirtProduct2 = new Product(102,"George","Red",700,4.8, shirtCategory);
        Product shirtProduct3 = new Product(103,"Levis","Black",500,4.7, shirtCategory);
        Product shirtProduct4 = new Product(104,"PVH","Green",400,4.6, shirtCategory);
        Product shirtProduct5 = new Product(105,"Dockers","Purple",800,4.5, shirtCategory);
        products.add(shirtProduct1);
        products.add(shirtProduct2);
        products.add(shirtProduct3);
        products.add(shirtProduct4);
        products.add(shirtProduct5);

        Product tshirtProduct1 = new Product(201,"Calvinklein","Blue",400,4.9, tshirtCategory);
        Product tshirtProduct2 = new Product(202,"Adidas","Red",500,4.8, tshirtCategory);
        Product tshirtProduct3 = new Product(203,"Puma","Black",300,4.7, tshirtCategory);
        Product tshirtProduct4 = new Product(204,"Levis","Green",200,4.6, tshirtCategory);
        Product tshirtProduct5 = new Product(205,"LosAngelos","Purple",600,4.5, tshirtCategory);
        products.add(tshirtProduct1);
        products.add(tshirtProduct2);
        products.add(tshirtProduct3);
        products.add(tshirtProduct4);
        products.add(tshirtProduct5);

        Product pantProduct1 = new Product(301,"Fila","Black",1300,4.7, pantCategory);
        Product pantProduct2 = new Product(302,"Vans","Red",1500,4.8, pantCategory);
        Product pantProduct3 = new Product(303,"Zara","Blue",1400,4.9, pantCategory);
        Product pantProduct4 = new Product(304,"Levis","Green",1200,4.6, pantCategory);
        Product pantProduct5 = new Product(305,"LosAngelos","Purple",1600,4.5, pantCategory);
        products.add(pantProduct1);
        products.add(pantProduct2);
        products.add(pantProduct3);
        products.add(pantProduct4);
        products.add(pantProduct5);

        Product shoeProduct1 = new Product(401,"Puma","8",2300,4.7, shoeCategory);
        Product shoeProduct2 = new Product(402,"Reebok","9",2500,4.8, shoeCategory);
        Product shoeProduct3 = new Product(403,"Adidas","10",2400,4.9, shoeCategory);
        Product shoeProduct4 = new Product(404,"Nike","11",2200,4.6, shoeCategory);
        Product shoeProduct5 = new Product(405,"Woodland","12",2600,4.5, shoeCategory);
        products.add(shoeProduct1);
        products.add(shoeProduct2);
        products.add(shoeProduct3);
        products.add(shoeProduct4);
        products.add(shoeProduct5);

        Product watchProduct1 = new Product(501,"Adidas","Analog",3300,4.7, watchCategory);
        Product watchProduct2 = new Product(502,"Fastrack","Analog",3500,4.8, watchCategory);
        Product watchProduct3 = new Product(503,"Rolex","Analog",3400,4.9, watchCategory);
        Product watchProduct4 = new Product(504,"Breitling","Digital",3200,4.6, watchCategory);
        Product watchProduct5 = new Product(505,"Seiko","Digital",3600,4.5, watchCategory);
        products.add(watchProduct1);
        products.add(watchProduct2);
        products.add(watchProduct3);
        products.add(watchProduct4);
        products.add(watchProduct5);
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
}
