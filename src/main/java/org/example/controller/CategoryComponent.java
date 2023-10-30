package org.example.controller;

import org.example.models.Category;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.CategoryPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.LoadUtils.getCategories;
import static org.example.utils.Utils.println;

public class CategoryComponent {
    private final CategoryPage categoryPage;
    private final HomeComponent homeComponent;
    private final ProductComponent productComponent;

    public CategoryComponent(HomeComponent homeComponent) {
        this.homeComponent = homeComponent;
        categoryPage = new CategoryPage();
        productComponent = new ProductComponent(homeComponent);
    }

    public void printMenu() {
        ArrayList<Category> categories = getCategories();
        categoryPage.printMenu(categories);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);

            if (choice == 99) {
                homeComponent.printMenu();
            } else {
                int validCategoryId = 0;

                for (Category category : categories) {
                    if (category.getId() == choice) {
                        validCategoryId = category.getId();
                        break;
                    }
                }

                if (validCategoryId != 0) {
                    productComponent.showProducts(validCategoryId);
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
        printMenu();
    }
}
