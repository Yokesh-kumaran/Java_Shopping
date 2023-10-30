package org.example.view;

import org.example.models.Category;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

import static org.example.utils.AppInput.*;
import static org.example.utils.LoadUtils.*;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class AdminPage {

    public void adminMenu() {
        println("\n****************************");
        println(StringUtils.WELCOME_ADMIN);
        println("****************************");
    }

    public void adminList() {
        println(StringUtils.ADMIN_CHOICE);
    }

    public Category getCategory() {
        ArrayList<Category> categories = getCategories();
        String categoryInput = enterString("Enter Category: ");
        Category selectedCategory = null;
        for(Category category: categories){
            if(category.getCategoryName().equals(categoryInput)){
                selectedCategory = new Category(category.getId(), category.getCategoryName());
                break;
            }
        }
        return selectedCategory;
    }

    public int getId() throws AppException {
        int idInput = enterInt("Enter Id: ");
        return idInput;
    }

    public String getTitle() {
        String titleInput = enterString("Enter Title: ");
        return titleInput;
    }

    public String getDescription() {
        String descriptionInput = enterString("Enter Description: ");
        return descriptionInput;
    }

    public double getPrice() {
        double priceInput = enterDouble("Enter Price: ");
        return priceInput;
    }

    public double getRating() {
        double ratingInput = enterDouble("Enter Rating: ");
        return ratingInput;
    }

    public int deleteProductId() throws AppException {
        int deleteProductId = enterInt("Enter the Id: ");
        ArrayList<Product> products = getProducts();
        int deleteId = 0;
        for(Product product: products){
            if(product.getId() == deleteProductId){
                deleteId = deleteProductId;
                break;
            }
        }
        if(deleteId == 0){
            println("Product not found!!! Please enter valid Id..");
            deleteProductId();
        }
        return deleteId;
    }

    public void getUsers() {
        println("\n~~~~~~~~~~~~~~~~~~~");
        println(StringUtils.USERS);
        println("~~~~~~~~~~~~~~~~~~~");
    }
}
