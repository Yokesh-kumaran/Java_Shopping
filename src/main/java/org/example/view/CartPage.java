package org.example.view;

import org.example.models.CartProduct;
import org.example.utils.StringUtils;

import java.util.ArrayList;

import static org.example.utils.Utils.println;

public class CartPage {
    public void showEmptyCart() {
        println(StringUtils.EMPTY_MESSAGE);
    }

    public void printCart(ArrayList<CartProduct> cartProducts) {
        println(StringUtils.CART_WELCOME);
        double total = 0;
        for (CartProduct cartProduct : cartProducts) {
            total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
            println(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount());
        }
        println(StringUtils.TOTAL_PRICE + total);
    }

    public void printCheckout() {
        println("~~~~~~~~~~~~~~~~~~~~~");
        println(StringUtils.PRINT_CHECKOUT);
    }

    public void printBack() {
        println(StringUtils.BACK_OPTION);
    }
}
