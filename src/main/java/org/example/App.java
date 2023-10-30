package org.example;

import org.example.controller.AppComponent;
import org.example.utils.LoadUtils;

public class App {
    public static void main(String[] args) {
        AppComponent appComponent = new AppComponent();
        LoadUtils.onLoad();
        appComponent.init();
    }
}