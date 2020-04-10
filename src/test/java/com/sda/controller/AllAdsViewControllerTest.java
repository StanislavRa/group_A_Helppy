package com.sda.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AllAdsViewControllerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        String connectorToAllAdsView = "/views/allAdsView.fxml";

        Parent root = FXMLLoader.load(getClass().getResource(connectorToAllAdsView));
        primaryStage.setTitle("My First App");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }
}
