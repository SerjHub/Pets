package com.example.catsanddogs;

import android.app.Application;

public class PetsApp extends Application {

    private static PetsApp app;
    private static PetsContainer container;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initModel();
    }

    private void initModel() {
        Model model = new Model();
        container = new PetsContainer(model);
    }

    public static PetsContainer getPetsContainer() {
        return container;
    }

}
