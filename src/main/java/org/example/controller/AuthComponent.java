package org.example.controller;

import org.example.controller.impl.IAuthComponent;
import org.example.models.Role;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AuthPage;
import org.example.view.LoginPage;
import org.example.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getUsersFile;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class AuthComponent implements IAuthComponent {
    private final HomeComponent homeComponent;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    private final AuthPage authPage;
    private final AdminComponent adminComponent;

    public AuthComponent() {
        authPage = new AuthPage();
        homeComponent = new HomeComponent(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        adminComponent = new AdminComponent(this);
    }

    @Override
    public void authMenu() {
        authPage.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 99) {
                authPage.printExit();
                System.exit(0);
            } else {
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    register();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    public void login() {
        String email, password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);

        User user = validateUser(email, password);

        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                adminComponent.showMenu();
            } else {
                setLoggedInUser(user);
                homeComponent.printMenu();
            }
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }

    private User validateUser(String email, String password) {
        try {
            Scanner scanner = new Scanner(getUsersFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                if (!value.startsWith("id")) {
                    String[] userArray = value.split(",");
                    if (userArray[2].equals(email) && userArray[3].equals(password)) {
                        User user = new User();
                        user.setId(Integer.parseInt(userArray[0]));
                        user.setName(userArray[1]);
                        user.setEmail(userArray[2]);
                        user.setPassword(userArray[3]);
                        if (user.getEmail().equals("admin@admin.com"))
                            user.setRole(Role.ADMIN);
                        else
                            user.setRole(Role.USER);
                        return user;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void register() {
        String name, email, password, confirmPassword;
        name = enterString(StringUtils.ENTER_NAME);
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);
        confirmPassword = enterString(StringUtils.ENTER_CONFIRM_PASSWORD);

        if (password.equals(confirmPassword)) {
            try {
                FileWriter csvWriter = new FileWriter(getUsersFile(), true);
                int id = (int) (Math.random() * 100);
                csvWriter.append("\n");
                csvWriter.append(id + "," + name + "," + email + "," + password);
                csvWriter.flush();
                csvWriter.close();
                registerPage.printSuccessRegister();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    private void invalidChoice(AppException e) {
        println(e.getMessage());
        authMenu();
    }
}
