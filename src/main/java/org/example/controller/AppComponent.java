package org.example.controller;

import org.example.controller.impl.IAppComponent;
import org.example.view.WelcomePage;

public class AppComponent implements IAppComponent {

    private final WelcomePage welcomePage;
    private final AuthComponent authComponent;

    public AppComponent() {
        welcomePage = new WelcomePage();
        authComponent = new AuthComponent();
    }

    @Override
    public void init() {
        welcomePage.welcome();
        authComponent.authMenu();
    }
}
