package org.example.controller;

import org.example.controller.impl.ICartComponent;
import org.example.models.Cart;
import org.example.models.CartProduct;
import org.example.models.Product;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.CartPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.UserUtils.getLoggedInUser;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class CartComponent implements ICartComponent {
    private final CartPage cartPage;
    private final HomeComponent homeComponent;
    private final OrderComponent orderComponent;

    public CartComponent(HomeComponent homeComponent) {
        this.homeComponent = homeComponent;
        cartPage = new CartPage();
        orderComponent = new OrderComponent(homeComponent);
    }

    @Override
    public void addToCart(int productId) {
        User loggedInUser = getLoggedInUser();
        ArrayList<Product> products = getProducts();

        Product userProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                userProduct = product;
                break;
            }
        }

        if (loggedInUser.getUserCart() != null) {
            Cart cart = loggedInUser.getUserCart();

            boolean isFound = false;
            for (CartProduct cartProduct : cart.getCartProducts()) {
                if (cartProduct.getProduct().getId() == productId) {
                    cartProduct.setCount(cartProduct.getCount() + 1);
                    isFound = true;
                }
            }

            if (!isFound) {
                cart.getCartProducts().add(new CartProduct(userProduct, 1));
            }

            loggedInUser.setUserCart(cart);
        } else {
            Cart cart = new Cart();
            ArrayList<CartProduct> cartProducts = new ArrayList<>();
            cartProducts.add(new CartProduct(userProduct, 1));
            cart.setCartProducts(cartProducts);
            loggedInUser.setUserCart(cart);
        }
        setLoggedInUser(loggedInUser);
    }

    private void checkout() {
        orderComponent.checkout();
    }

    @Override
    public void showCart() {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser.getUserCart() == null) {
            cartPage.showEmptyCart();
            homeComponent.printMenu();
        } else {
            ArrayList<CartProduct> cartProducts = loggedInUser.getUserCart().getCartProducts();
            cartPage.printCart(cartProducts);

            cartPage.printCheckout();
            cartPage.printBack();

            try {
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 11) {
                    checkout();
                } else if (choice == 99) {
                    homeComponent.printMenu();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            } catch (AppException appException) {
                invalidChoice(appException);
            }

        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        showCart();
    }
}

