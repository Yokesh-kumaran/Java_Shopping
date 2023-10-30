package org.example.controller;

import org.example.controller.impl.IOrderComponent;
import org.example.models.CartProduct;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.OrdersPage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.UserUtils.getLoggedInUser;
import static org.example.utils.Utils.println;

public class OrderComponent implements IOrderComponent {
    private final HomeComponent homeComponent;
    private final OrdersPage ordersPage;

    public OrderComponent(HomeComponent homeComponent) {
        this.homeComponent = homeComponent;
        ordersPage = new OrdersPage();
    }

    public void checkout() {
        User loggedInUser = getLoggedInUser();

        try {
            FileWriter fileWriter = new FileWriter(getFilePath() + loggedInUser.getId() + "-" + System.currentTimeMillis() + ".txt");
            fileWriter.write("Your Order are:");
            fileWriter.write("\n");

            double total = 0;
            for (CartProduct cartProduct : loggedInUser.getUserCart().getCartProducts()) {
                total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
                fileWriter.write(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount() + " = Rs. " + cartProduct.getProduct().getPrice() * cartProduct.getCount());
                fileWriter.write("\n");
            }
            fileWriter.write("Total - Rs. " + total);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLoggedInUser().setUserCart(null);
        ordersPage.printSuccess();
        homeComponent.printMenu();
    }

    public void showOrders() {
        Map<String, String> files = listFilesForFolder(new File(getFilePath()));
        if (files.isEmpty()) {
            ordersPage.showOrdersEmpty();
            homeComponent.printMenu();
        } else {
            ordersPage.printOrder(files);
            try {
                int orderId = enterInt(StringUtils.ENTER_CHOICE);
                if (orderId == 99) {
                    homeComponent.printMenu();
                } else {
                    if (orderId > files.size()) {
                        println(StringUtils.INVALID_CHOICE);
                        showOrders();
                    } else {
                        int id = 1;
                        String path = "";
                        for (final String key : files.keySet()) {
                            if (id == orderId) {
                                path = files.get(key);
                            }
                        }
                        BufferedReader r = new BufferedReader(new FileReader(getFilePath() + path));
                        String line;
                        System.out.println();
                        while ((line = r.readLine()) != null) {
                            println(line);
                        }
                        showOrders();
                    }
                }

            } catch (AppException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Map<String, String> listFilesForFolder(final File folder) throws RuntimeException {
        Map<String, String> files = new HashMap<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Path path = new File(getFilePath() + fileEntry.getName()).toPath();
            BasicFileAttributes file_att;
            try {
                file_att = Files.readAttributes(
                        path, BasicFileAttributes.class);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy - ");

                Date d = sdf.parse(file_att.creationTime().toString());

                if (fileEntry.getName().startsWith(String.valueOf(getLoggedInUser().getId())))
                    files.put(dateFormat.format(d), fileEntry.getName());
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

        }
        return files;
    }
}
