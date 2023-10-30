package org.example.controller;

import org.example.controller.impl.IAdminComponent;
import org.example.models.Category;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AdminPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.FileUtil.getUsersFile;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class AdminComponent implements IAdminComponent {
    private final AuthComponent authComponent;
    private final AdminPage adminPage;

    public AdminComponent(AuthComponent authComponent) {
        this.authComponent = authComponent;
        adminPage = new AdminPage();
    }


    @Override
    public void showMenu() {
        adminPage.adminMenu();
        adminOperations();
    }

    public void adminOperations() {
        adminPage.adminList();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                ArrayList<Product> products = getProducts();
                for (Product product : products) {
                    println(product.getId() + " " + product.getTitle() + " " + product.getDescription() + " " + product.getPrice() + " " + product.getRating() + " " + product.getCategory().getCategoryName());
                }
                System.out.println();
                adminOperations();
            } else if (choice == 2) {
                addProducts();
            } else if (choice == 3) {
                ArrayList<Product> products = getProducts();
                int deleteId = adminPage.deleteProductId();
                Iterator<Product> iterator = products.iterator();
                while (iterator.hasNext()) {
                    Product product = iterator.next();
                    if (product.getId() == deleteId) {
                        iterator.remove();
                    }
                }
                println("Product has been removed!!!");
                System.out.println();
                adminOperations();
            } else if (choice == 4) {
                adminPage.getUsers();
                try {
                    Scanner scanner = new Scanner(getUsersFile());
                    while (scanner.hasNext()) {
                        String value = scanner.next().trim();
                        if (!value.startsWith("id")) {
                            String[] categoryArray = value.split(",");
                            for (int i = 0; i < categoryArray.length; i++) {
                                if (i == 0) {
                                    print(categoryArray[i] + ". ");
                                } else {
                                    print(categoryArray[i] + " ");
                                }
                            }
                            System.out.println();
                        }
                    }
                    scanner.close();
                    println("\n");
                    adminOperations();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (choice == 5) {
                File directoryPath = new File("src/main/java/org/example/asset/");
                File filesList[] = directoryPath.listFiles();
                System.out.println();
                System.out.println("List of orders");
                Scanner sc = null;
                StringBuffer sb = new StringBuffer();
                for (File file : filesList) {
                    System.out.println("File name: " + file.getName());
                    sc = new Scanner(file);
                    String input;
                    while (sc.hasNextLine()) {
                        input = sc.nextLine();
                        sb.append(input + " ");
                    }
                    System.out.println("Contents of the file: " + sb.toString());
                    System.out.println();
                    sb.setLength(0);
                }
                adminOperations();
            }
            else if (choice == 6) {
                authComponent.authMenu();
            }
            else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        adminOperations();
    }

    public void addProducts() {
        System.out.println();
        try {
            int id = adminPage.getId();
            String title = adminPage.getTitle();
            String description = adminPage.getDescription();
            double price = adminPage.getPrice();
            double rating = adminPage.getRating();
            Category category = adminPage.getCategory();
            ArrayList<Product> products = getProducts();
            Product newProduct = new Product(id, title, description, price, rating, category);
            products.add(newProduct);
            System.out.println();
            adminOperations();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
