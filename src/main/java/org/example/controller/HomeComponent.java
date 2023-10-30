package org.example.controller;

import org.example.controller.impl.IHomeComponent;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.HomePage;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class HomeComponent implements IHomeComponent {
    private final CategoryComponent categoryComponent;
    private final HomePage homePage;
    private final AuthComponent authComponent;
    private final ProductComponent productComponent;
    private final CartComponent cartComponent;
    private final OrderComponent orderComponent;

    public HomeComponent(AuthComponent authComponent) {
        homePage = new HomePage();
        this.authComponent = authComponent;
        categoryComponent = new CategoryComponent(this);
        productComponent = new ProductComponent(this);
        cartComponent = new CartComponent(this);
        orderComponent = new OrderComponent(this);
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                categoryComponent.printMenu();
            } else if (choice == 2) {
                productComponent.showProducts(0);
            } else if (choice == 3) {
                cartComponent.showCart();
            } else if (choice == 4) {
                orderComponent.showOrders();
                printMenu();
            } else if (choice == 5) {
                setLoggedInUser(null);
                authComponent.authMenu();
            } else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
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
