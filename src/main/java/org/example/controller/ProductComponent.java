package org.example.controller;

import org.example.controller.impl.IProductComponent;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.ProductsPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.Utils.println;

public class ProductComponent implements IProductComponent {
    private final CartComponent cartComponent;
    private final ProductsPage productsPage;
    private int categoryId = 0;
    private final HomeComponent homeComponent;

    public ProductComponent(HomeComponent homeComponent) {
        this.homeComponent = homeComponent;
        productsPage = new ProductsPage();
        cartComponent = new CartComponent(homeComponent);
    }


    @Override
    public void showProducts(int categoryId) {
        this.categoryId = categoryId;
        ArrayList<Product> products = getProducts();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory().getId() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            products = categoryProducts;
        }

        productsPage.printProducts(products);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            int validProductId = 0;

            if (choice == 99) {
                homeComponent.printMenu();
            } else {
                for (Product product : products) {
                    if (product.getId() == choice) {
                        validProductId = product.getId();
                        break;
                    }
                }

                if (validProductId != 0) {
                    cartComponent.addToCart(validProductId);
                    productsPage.productAdded();
                    showProducts(categoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        showProducts(categoryId);
    }
}
